package uk.joshiejack.piscary.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.piscary.Piscary;

@OnlyIn(Dist.CLIENT)
public class FishRenderer extends MobRenderer<AbstractFishEntity, EntityModel<AbstractFishEntity>> {
    private final ResourceLocation texture;

    public FishRenderer(EntityRendererManager manager, EntityModel<AbstractFishEntity> model, String entity) {
        super(manager, model, 0.35F);
        this.texture = new ResourceLocation(Piscary.MODID, "textures/entity/" + entity + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractFishEntity entity) {
        return texture;
    }

    @Override
    protected void setupRotations(AbstractFishEntity fish, MatrixStack stack, float x, float y, float z) {
        super.setupRotations(fish, stack, x, y, z);
        float f = 1.0F;
        float f1 = 1.0F;
        if (!fish.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }

        float f2 = f * 4.3F * MathHelper.sin(f1 * 0.6F * x);
        stack.mulPose(Vector3f.YP.rotationDegrees(f2));
        stack.translate(0.0D, 0.0D, -0.4F);
        if (!fish.isInWater()) {
            stack.translate(0.2F, 0.1F, 0.0D);
            stack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
