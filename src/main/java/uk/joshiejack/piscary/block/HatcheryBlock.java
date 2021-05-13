package uk.joshiejack.piscary.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemHandlerHelper;
import uk.joshiejack.penguinlib.block.base.AbstractPenguinBlock;
import uk.joshiejack.piscary.tile.HatcheryTileEntity;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class HatcheryBlock extends AbstractPenguinBlock {
    private static final VoxelShape SHAPE = Block.box(0D, 0D, 0D, 16D, 16D, 16D);

    public HatcheryBlock() {
        super(AbstractBlock.Properties.of(Material.WOOL).randomTicks().strength(0.5F).sound(SoundType.WOOL).noOcclusion());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
        TileEntity tile = world.getBlockEntity(pos);
        ItemStack held = player.getItemInHand(hand);
        if (!(tile instanceof HatcheryTileEntity)) return ActionResultType.PASS;
        HatcheryTileEntity hatchery = (HatcheryTileEntity) tile;
        if (held.getItem() == Items.BUCKET) {
            if (!world.isClientSide) {
                if (!player.abilities.instabuild) {
                    held.shrink(1);
                }

                world.destroyBlock(pos, true);
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.WATER_BUCKET));
            }

            return ActionResultType.sidedSuccess(world.isClientSide);
        } else if (held.getItem() == Items.WATER_BUCKET) {
            if (!hatchery.isEmpty() && !world.isClientSide) {
                if (!player.abilities.instabuild) {
                    held.shrink(1); //Reduce ot y
                }

                ItemHandlerHelper.giveItemToPlayer(player, hatchery.getFishBucket());
                hatchery.removeFish(); //Remove the fish from the hatchery
                player.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            }

            return ActionResultType.sidedSuccess(world.isClientSide);
        } else if (held.getItem() instanceof FishBucketItem) {
            if (hatchery.isEmpty() && !world.isClientSide) {
                Supplier<EntityType<?>> type = ObfuscationReflectionHelper.getPrivateValue(FishBucketItem.class, (FishBucketItem) held.getItem(), "fishTypeSupplier");
                if (!player.abilities.instabuild) {
                    held.shrink(1); //Reduce ot y
                }

                hatchery.setEntityTypeAndCount(type.get(), 1); //Set the hatchery to the correct fish bucket entity type
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.WATER_BUCKET));
                player.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            }

            return ActionResultType.sidedSuccess(world.isClientSide);
        } else if (!hatchery.isEmpty() && hatchery.getCount() > 1)  {
            if (!world.isClientSide)
                hatchery.extractFish(true).die(DamageSource.DROWN);
            return ActionResultType.sidedSuccess(world.isClientSide);
        }

        return ActionResultType.PASS;
    }

    @Override
    public void onRemove(BlockState oldState, @Nonnull World world, @Nonnull BlockPos pos, BlockState newState, boolean p_196243_5_) {
        if (!oldState.is(newState.getBlock())) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof HatcheryTileEntity) {
                ((HatcheryTileEntity) tileentity).spawnFish(((HatcheryTileEntity)tileentity).getCount());
            }

            super.onRemove(oldState, world, pos, newState, p_196243_5_);
        }
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public FluidState getFluidState(@Nonnull BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos, @Nonnull ISelectionContext ctx) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return new HatcheryTileEntity();
    }
}
