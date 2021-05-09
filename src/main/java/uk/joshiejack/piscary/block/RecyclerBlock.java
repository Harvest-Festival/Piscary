package uk.joshiejack.piscary.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.items.IItemHandler;
import uk.joshiejack.penguinlib.block.base.AbstractDoubleBlock;
import uk.joshiejack.piscary.tile.RecyclerTileEntity;

import javax.annotation.Nonnull;

public class RecyclerBlock extends AbstractDoubleBlock {
    public RecyclerBlock() {
        super(AbstractBlock.Properties.of(Material.HEAVY_METAL).strength(1.2F).requiresCorrectToolForDrops());
        hasInventory = true;
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
