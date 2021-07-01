package uk.joshiejack.piscary.tileentity;

import joptsimple.internal.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.tile.AbstractPenguinTileEntity;
import uk.joshiejack.penguinlib.util.helpers.generic.MathsHelper;
import uk.joshiejack.piscary.client.renderer.HatcheryFishRender;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.network.SyncHatcheryPacket;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class HatcheryTileEntity extends AbstractPenguinTileEntity implements ITickableTileEntity {
    private EntityType<?> entityType = null;
    private int count = 0;
    private int ticksPassed = 0;
    private int breakChance = 125;

    public HatcheryTileEntity() {
        super(PiscaryTileEntities.HATCHERY.get());
    }

    public boolean isEmpty() {
        return entityType == null || count <= 0;
    }

    public void setEntityTypeAndCount(EntityType<?> type, int count) {
        this.entityType = type;
        this.count = count;
        this.markUpdated();
        if (!level.isClientSide)
            PenguinNetwork.sendToNearby(new SyncHatcheryPacket(worldPosition, entityType, count), this);
        else
            getRenderer().reloadFish(entityType, count, level.random);
    }

    @Override
    public void tick() {
        if (getEntityType() == null) return;
        if (level.isClientSide)
            getRenderer().updateFish();
        if (level.getGameTime() % 100 == 0 && count < 10) {
            int ticksRequired = (int) (PiscaryRegistries.getCycles(getEntityType()) * TimeUnitRegistry.get(getType().getRegistryName().toString()));
            if (ticksRequired >= 1) {
                ticksPassed += 100;
                if (ticksPassed >= ticksRequired) {
                    ticksPassed = 0; //Reset
                    count++;
                    setEntityTypeAndCount(entityType, count);
                }
            }
        }
    }

    public LivingEntity extractFish(boolean adjustCount) {
        Entity entity = entityType.spawn((ServerWorld) level, ItemStack.EMPTY, null, worldPosition, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((AbstractFishEntity) entity).setFromBucket(true);
        }

        //Helper boys
        if (adjustCount) {
            count--;
            setEntityTypeAndCount(entityType, count);
        }

        return (LivingEntity) entity;
    }

    public void spawnFish(int count) {
        if (entityType != null) {
            for (int i = 0; i < count; i++) {
                extractFish(false);
            }
        }
    }

    public void removeFish() {
        count--;
        if (level.random.nextInt(100) > breakChance) {
            BlockState state = level.getBlockState(worldPosition);
            level.setBlock(worldPosition, Blocks.WATER.defaultBlockState(), 2);
            level.levelEvent(null, 2001, worldPosition, Block.getId(state));
            spawnFish(count);
            return;
        } else breakChance -= 25;

        breakChance = MathsHelper.constrainToRangeInt(breakChance, 0, 100);
        markUpdated(); //Resync the count data
    }

    @OnlyIn(Dist.CLIENT)
    private HatcheryFishRender renderer;

    @OnlyIn(Dist.CLIENT)
    public HatcheryFishRender getRenderer() {
        if (renderer == null)
            renderer = new HatcheryFishRender(this);
        return renderer;
    }

    @Nullable
    public EntityType<?> getEntityType() {
        return entityType;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        if (level.isClientSide)
            getRenderer().reloadFish(entityType, count, level.random);
    }

    @Override
    public void load(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        super.load(state, nbt);
        String internal = nbt.getString("Entity");
        entityType = Strings.isNullOrEmpty(internal) ? null : ForgeRegistries.ENTITIES.getValue(new ResourceLocation(internal));
        count = nbt.getByte("Count");
        ticksPassed = nbt.getInt("TicksPassed");
        breakChance = nbt.getByte("TimesPulled");
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putString("Entity", entityType == null ? "" : entityType.getRegistryName().toString());
        nbt.putByte("Count", (byte) count);
        nbt.putInt("TicksPassed", ticksPassed);
        nbt.putByte("TimesPulled", (byte) breakChance);
        return super.save(nbt);
    }
}
