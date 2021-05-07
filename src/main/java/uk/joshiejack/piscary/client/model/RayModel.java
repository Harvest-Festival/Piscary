package uk.joshiejack.piscary.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RayModel extends AbstractFishModel {
	protected ModelRenderer wing_left;
	protected ModelRenderer wing_right;
	protected float scale;

	public RayModel() { this(1F); }
	public RayModel(float s) {
		scale = s;
		texWidth = 128;
		texHeight = 128;

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 20.0F, 15.0F);
		tail.setTexSize(35, 65).addBox(-0.5F, 1.0F, -1.0F, 1.0F, 1.0F, 13.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.setTexSize(0, 26).addBox(-4.0F, -1.0F, -7.0F, 8.0F, 1.0F, 22.0F, 0.0F, false);
		body.setTexSize(39, 28).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 2.0F, 21.0F, 0.0F, false);
		body.setTexSize(0, 0).addBox(-5.0F, -4.0F, -8.0F, 10.0F, 3.0F, 23.0F, 0.0F, false);

		wing_left = new ModelRenderer(this);
		wing_left.setPos(4.0F, 21.0F, 4.0F);
		wing_left.setTexSize(0, 49).addBox(0.0F, -0.5F, -9.0F, 6.0F, 2.0F, 18.0F, 0.0F, false);
		wing_left.setTexSize(52, 53).addBox(6.0F, -0.5F, -8.0F, 5.0F, 2.0F, 12.0F, 0.0F, false);
		wing_left.setTexSize(0, 8).addBox(11.0F, -0.5F, -6.0F, 5.0F, 2.0F, 6.0F, 0.0F, false);
		wing_left.setTexSize(0, 26).addBox(16.0F, -0.5F, -5.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);

		wing_right = new ModelRenderer(this);
		wing_right.setPos(-4.0F, 21.0F, 4.0F);
		wing_right.setTexSize(43, 0).addBox(-6.0F, -0.5F, -9.0F, 6.0F, 2.0F, 18.0F, 0.0F, false);
		wing_right.setTexSize(30, 51).addBox(-11.0F, -0.5F, -8.0F, 5.0F, 2.0F, 12.0F, 0.0F, false);
		wing_right.setTexSize(0, 0).addBox(-16.0F, -0.5F, -6.0F, 5.0F, 2.0F, 6.0F, 0.0F, false);
		wing_right.setTexSize(0, 16).addBox(-21.0F, -0.5F, -5.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(AbstractFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float f = 1.0F;
		float f1 = 1.0F;
		if (!entity.isInWater()) {
			f = 1.3F;
			f1 = 1.7F;
		}

		wing_left.zRot = -f * 0.35F * MathHelper.sin(f1 * 0.1F * ageInTicks);
		wing_right.zRot = f * 0.35F * MathHelper.sin(f1 * 0.1F * ageInTicks);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(tail, body, wing_left, wing_right);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		if (scale == 1F) super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		else {
			matrixStack.pushPose();
			matrixStack.scale(scale, scale, scale);
			super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			matrixStack.popPose();
		}
	}
}