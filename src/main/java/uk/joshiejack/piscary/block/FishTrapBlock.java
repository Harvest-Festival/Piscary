package uk.joshiejack.piscary.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import uk.joshiejack.penguinlib.block.base.AbstractPenguinBlock;
import uk.joshiejack.penguinlib.util.helpers.minecraft.FakePlayerHelper;
import uk.joshiejack.piscary.crafting.BaitRegistry;
import uk.joshiejack.piscary.tileentity.FishTrapTileEntity;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class FishTrapBlock extends AbstractPenguinBlock implements IWaterLoggable {
    public static final EnumProperty<FishTrapState> TRAP_STATE = EnumProperty.create("state", FishTrapState.class);

    public FishTrapBlock() {
        super(AbstractBlock.Properties.of(Material.CORAL).strength(0.1F).sound(SoundType.WOOL));
        registerDefaultState(stateDefinition.any().setValue(TRAP_STATE, FishTrapState.EMPTY));
        hasInventory = true;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainerBuilder) {
        stateContainerBuilder.add(TRAP_STATE);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public FluidState getFluidState(@Nonnull BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    private ResourceLocation getLootTable(ItemStack bait, Random random) {
        if (random.nextInt(4) == 0) {
            ResourceLocation lootTable = BaitRegistry.getValue(bait).getLootTable();
            return lootTable != BaitRegistry.BaitData.EMPTY.getLootTable() ? lootTable : LootTables.FISHING_FISH;
        } else return LootTables.FISHING_JUNK;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        TileEntity tile = world.getBlockEntity(pos);
        if (!(tile instanceof FishTrapTileEntity)) return;
        FishTrapTileEntity trap = (FishTrapTileEntity) tile;
        if (trap.isSurroundedByWater()) {
            if (trap.isBaited()) {
                PlayerEntity player = FakePlayerHelper.getFakePlayerWithPosition(world, pos);
                LootContext.Builder lootcontext$builder = (new LootContext.Builder(world))
                        .withParameter(LootParameters.ORIGIN, player.position())
                        .withParameter(LootParameters.TOOL, trap.getItem(0))
                        .withParameter(LootParameters.KILLER_ENTITY, player)
                        .withParameter(LootParameters.THIS_ENTITY, player)
                        .withRandom(player.getRandom())
                        .withLuck(player.getLuck());

                ItemStack stack = ItemStack.EMPTY;
                while (stack.isEmpty()) {
                    LootTable loottable = world.getServer().getLootTables().get(getLootTable(trap.getItem(0), random));
                    List<ItemStack> result = loottable.getRandomItems(lootcontext$builder.create(LootParameterSets.FISHING));
                    if (!result.isEmpty())
                        stack = result.get(0);
                }

                trap.setItem(0, stack);
                trap.setTimeCaught(world.getDayTime());
            }
        } else world.getBlockTicks().scheduleTick(pos, this, trap.getTimeUnit());
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return new FishTrapTileEntity();
    }

    public enum FishTrapState implements IStringSerializable {
        EMPTY, BAITED;

        @Nonnull
        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }
}
