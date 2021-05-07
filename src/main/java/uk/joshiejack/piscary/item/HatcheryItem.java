package uk.joshiejack.piscary.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nonnull;

public class HatcheryItem extends BlockItem {
    public HatcheryItem(Block block, Properties properties) {
        super(block, properties.tab(Piscary.TAB));
    }

    @Nonnull
    @Override
    public ActionResultType useOn(@Nonnull ItemUseContext ctx) {
        return ActionResultType.PASS;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
        boolean waterAbove = world.getBlockState(blockraytraceresult1.getBlockPos()).getMaterial() == Material.WATER;
        if (!waterAbove && world.getBlockState(blockraytraceresult.getBlockPos()).getMaterial() == Material.WATER) {
            ActionResultType actionresulttype = super.useOn(new ItemUseContext(player, hand, blockraytraceresult));
            return new ActionResult<>(actionresulttype, player.getItemInHand(hand));
        }

        return new ActionResult<>(ActionResultType.PASS, player.getItemInHand(hand));
    }
}
