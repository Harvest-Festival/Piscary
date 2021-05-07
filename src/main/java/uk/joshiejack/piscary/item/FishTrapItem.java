package uk.joshiejack.piscary.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nonnull;

public class FishTrapItem extends BlockItem {
    public FishTrapItem(Block block, Item.Properties properties) {
        super(block, properties.tab(Piscary.TAB));
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext ctx, @Nonnull BlockState state) {
        return ctx.getLevel().getBlockState(ctx.getClickedPos()).getMaterial() == Material.WATER
                && super.placeBlock(ctx, state);
    }
}
