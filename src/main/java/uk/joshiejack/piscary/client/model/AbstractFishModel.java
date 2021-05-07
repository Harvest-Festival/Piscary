package uk.joshiejack.piscary.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractFishModel extends EntityModel<AbstractFishEntity> {
    protected ModelRenderer tail;
    protected ModelRenderer body;

    @Override
    public void setupAnim(AbstractFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        float f1 = 1.0F;
        if (!entity.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }

        tail.yRot = -f * 0.35F * MathHelper.sin(f1 * 0.6F * ageInTicks);
    }


    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        parts().forEach(part -> part.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(tail, body);
    }

    protected void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
