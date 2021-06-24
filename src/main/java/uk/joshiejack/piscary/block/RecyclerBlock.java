package uk.joshiejack.piscary.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.items.IItemHandler;
import uk.joshiejack.penguinlib.block.base.AbstractDoubleBlock;
import uk.joshiejack.piscary.tileentity.RecyclerTileEntity;

import javax.annotation.Nonnull;

public class RecyclerBlock extends AbstractDoubleBlock {
    private static final VoxelShape SHAPE = Block.box(1D, 0D, 1D, 15D, 16D, 15D);

    public RecyclerBlock() {
        super(AbstractBlock.Properties.of(Material.HEAVY_METAL).strength(1.2F).requiresCorrectToolForDrops());
        hasInventory = true;
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
        return new RecyclerTileEntity();
    }

    @Override
    protected int getExtractAmount(IItemHandler handler, int slot) {
        return handler.getStackInSlot(slot).isEmpty() ? 0 : handler.getStackInSlot(slot).getCount();
    }
}
