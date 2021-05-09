package uk.joshiejack.piscary.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractMachineTileEntityRenderer;
import uk.joshiejack.piscary.tile.RecyclerTileEntity;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class RecyclerTileEntityRenderer extends AbstractMachineTileEntityRenderer<RecyclerTileEntity> {
    public RecyclerTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected double getYOffset() {
        return 2.5D;
    }
}