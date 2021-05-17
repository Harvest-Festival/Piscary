package uk.joshiejack.piscary.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.settings.GraphicsFanciness;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.penguinlib.client.renderer.tile.AbstractItemTileEntityRenderer;
import uk.joshiejack.piscary.tileentity.HatcheryTileEntity;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class HatcheryTileEntityRenderer extends AbstractItemTileEntityRenderer<HatcheryTileEntity> {
    public HatcheryTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(@Nonnull HatcheryTileEntity tile, float partialTicks, @Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        HatcheryFishRender renderer = tile.getRenderer();
        Entity entity = renderer.getOrCreateDisplayEntity(tile.getEntityType());
        for (int i = 0; i < tile.getCount(); i++) {
            if (entity != null) {
                matrix.pushPose();
                matrix.translate(0.5D, 0.6D, 0.5D);
                float f = 0.53125F;
                float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());
                float height = renderer.getHeight(i);
                if ((double) f1 > 1.0D) {
                    height += 1.6F;
                }

                boolean clockwise = renderer.isClockwise(i);
                matrix.translate(0.0D, (double) -0.25F + (0.1 + height * 0.075F), 0.0D);
                float rotation = renderer.getRotation(i);
                matrix.mulPose(Vector3f.YP.rotationDegrees(rotation));
                matrix.translate(0.0D, -0.7F + (f1 > 1 ? 0.1F : 0F), 0.0D);
                matrix.mulPose(Vector3f.XP.rotationDegrees(-90F));
                matrix.mulPose(Vector3f.YP.rotationDegrees(-90F));
                matrix.scale(f, f, f);
                EntityRendererManager rm = Minecraft.getInstance().getEntityRenderDispatcher();
                matrix.pushPose();
                if (f1 > 1.0D) {
                    matrix.scale(0.2F, 0.2F, 0.2F);
                } else matrix.scale(0.5F, 0.5F, 0.5F);
                matrix.translate(1.35F, 0F, 1.35F);
                matrix.mulPose(Vector3f.XP.rotationDegrees(clockwise ? -65F : 65F));
                entity.setPose(Pose.SWIMMING);
                if (entity instanceof PufferfishEntity) {
                    matrix.mulPose(Vector3f.ZP.rotationDegrees(-90F));
                }

                if (System.currentTimeMillis() %60 == 0)
                    entity.tickCount++;
                runAsFancy(() -> Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, 1.0F, matrix, buffer, 15728880));
                matrix.popPose();
                matrix.popPose();
            }
        }
    }

    private void runAsFancy(Runnable r) {
        boolean flag = Minecraft.useShaderTransparency();
        if (!flag) {
            r.run();
        } else {
            GameSettings gamesettings = Minecraft.getInstance().options;
            GraphicsFanciness graphicsfanciness = gamesettings.graphicsMode;
            gamesettings.graphicsMode = GraphicsFanciness.FABULOUS;
            r.run();
            gamesettings.graphicsMode = graphicsfanciness;
        }
    }

    @Override
    public boolean shouldRenderOffScreen(HatcheryTileEntity te) {
        return false;
    }
}
