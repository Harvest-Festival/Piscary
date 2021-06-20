package uk.joshiejack.piscary.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MantaRayModel extends RayModel {
	public MantaRayModel() {
		super(2F);
		ModelRenderer filter_1 = new ModelRenderer(this);
		filter_1.setPos(1.0F, 2.75F, -6.05F);
		body.addChild(filter_1);
		setRotationAngle(filter_1, -0.9599F, 0.0F, -0.1309F);
		filter_1.texOffs(0, 32).addBox(-4.0F, -2.0F, -6.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		ModelRenderer filter_2 = new ModelRenderer(this);
		filter_2.setPos(1.0F, 2.75F, -6.05F);
		body.addChild(filter_2);
		setRotationAngle(filter_2, -0.9599F, 0.0F, 0.1309F);
		filter_2.texOffs(8, 32).addBox(0.0F, -2.0F, -6.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(tail, body, wing_left, wing_right);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		matrixStack.pushPose();
		matrixStack.translate(0F, -1.5F, 0F);
		super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		matrixStack.popPose();
	}
}