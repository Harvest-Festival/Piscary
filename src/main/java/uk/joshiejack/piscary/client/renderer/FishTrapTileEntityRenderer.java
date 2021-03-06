package uk.joshiejack.piscary.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractItemTileEntityRenderer;
import uk.joshiejack.piscary.tileentity.FishTrapTileEntity;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class FishTrapTileEntityRenderer extends AbstractItemTileEntityRenderer<FishTrapTileEntity> {
    public FishTrapTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(@Nonnull FishTrapTileEntity tile, float partialTicks, @Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ItemStack inSlot = tile.getItem(0);
        if (!inSlot.isEmpty() && !tile.isBaited())
            renderSpeechBubble(inSlot, matrix, buffer, combinedLightIn, combinedOverlayIn);
    }
}