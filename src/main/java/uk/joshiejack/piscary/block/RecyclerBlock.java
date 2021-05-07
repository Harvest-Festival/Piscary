package uk.joshiejack.piscary.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import uk.joshiejack.penguinlib.block.base.AbstractInventoryBlock;
import uk.joshiejack.piscary.tile.RecyclerTileEntity;

import javax.annotation.Nonnull;

public class RecyclerBlock extends AbstractInventoryBlock {
    public RecyclerBlock() {
        super(AbstractBlock.Properties.of(Material.HEAVY_METAL));
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return new RecyclerTileEntity();
    }
}
