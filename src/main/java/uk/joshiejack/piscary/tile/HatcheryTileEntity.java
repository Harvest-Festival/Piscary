package uk.joshiejack.piscary.tile;

import joptsimple.internal.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.penguinlib.network.PenguinNetwork;
import uk.joshiejack.penguinlib.tile.AbstractPenguinTileEntity;
import uk.joshiejack.penguinlib.util.helpers.generic.MathsHelper;
import uk.joshiejack.penguinlib.util.helpers.minecraft.TerrainHelper;
import uk.joshiejack.piscary.client.renderer.HatcheryFishRender;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.network.SyncHatcheryPacket;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("ConstantConditions")
public class HatcheryTileEntity extends AbstractPenguinTileEntity implements ITickableTileEntity {
    private static Method getBucketItemStack = null;
    private EntityType<?> entityType = null;
    private int count = 0;
    private int daysPassed = 0;
    private int breakChance = 125;

    public HatcheryTileEntity() {
        super(PiscaryTileEntities.HATCHERY.get());
    }

    public ItemStack getFishBucket() {
        try {
            if (getBucketItemStack == null)
                getBucketItemStack = ObfuscationReflectionHelper.findMethod(AbstractFishEntity.class, "func_203707_dx");
            return entityType == null ? ItemStack.EMPTY : (ItemStack) getBucketItemStack.invoke(entityType.create(level));
        } catch (InvocationTargetException | IllegalAccessException ignored) {
            return ItemStack.EMPTY;
        }
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
            renderer.reloadFish(entityType, count, level.random);
    }

    @Override
    public void tick() {
        if (getEntityType() == null) return;
        if (level.isClientSide)
            renderer.updateFish();
        if (count < 10 && isOnWaterSurface(level, worldPosition.below())) {
            int days = PiscaryRegistries.getValue(getEntityType());
            if (days >= 1) {
                daysPassed++;
                if (daysPassed >= days) {
                    daysPassed = 0; //Reset
                    count++;
                    setEntityTypeAndCount(entityType, count);
                }
            }
        }
    }

    public void spawnFish() {
        if (entityType != null) {
            for (int i = 0; i < count; i++) {
                Entity entity = entityType.spawn((ServerWorld) level, ItemStack.EMPTY, null, worldPosition, SpawnReason.BUCKET, true, false);
                if (entity != null) {
                    ((AbstractFishEntity) entity).setFromBucket(true);
                }
            }
        }
    }

    public void removeFish() {
        count--;
        if (level.random.nextInt(100) > breakChance) {
            BlockState state = level.getBlockState(worldPosition);
            level.setBlock(worldPosition, Blocks.WATER.defaultBlockState(), 2);
            level.levelEvent(null, 2001, worldPosition, Block.getId(state));
            spawnFish();
            return;
        } else breakChance -= 25;

        breakChance = MathsHelper.constrainToRangeInt(breakChance, 0, 100);
        markUpdated(); //Resync the count data
    }

    private boolean isOnWaterSurface(World world, BlockPos pos) {
        return TerrainHelper.isWater(world, pos.east(), pos.west(), pos.north(), pos.south(), pos.east().south(), pos.east().north(), pos.west().north(), pos.west().south());
    }

    @OnlyIn(Dist.CLIENT)
    private final HatcheryFishRender renderer = new HatcheryFishRender(this);

    @OnlyIn(Dist.CLIENT)
    public HatcheryFishRender getRenderer() {
        return renderer;
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    public EntityType<?> getEntityType() {
        return entityType;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCount() {
        return count;
    }

    @Override
    public void load(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        super.load(state, nbt);
        String internal = nbt.getString("Entity");
        entityType = Strings.isNullOrEmpty(internal) ? null : ForgeRegistries.ENTITIES.getValue(new ResourceLocation(internal));
        count = nbt.getByte("Count");
        daysPassed = nbt.getInt("DaysPassed");
        breakChance = nbt.getByte("TimesPulled");
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putString("Entity", entityType == null ? "" : entityType.getRegistryName().toString());
        nbt.putByte("Count", (byte) count);
        nbt.putInt("DaysPassed", daysPassed);
        nbt.putByte("TimesPulled", (byte) breakChance);
        return super.save(nbt);
    }
}
