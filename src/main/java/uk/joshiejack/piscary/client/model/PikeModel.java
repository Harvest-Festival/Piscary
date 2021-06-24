package uk.joshiejack.piscary.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeModel extends AbstractFishModel {
    public PikeModel() {
        reinit();
    }

    public void reinit() {
        texWidth = 64;
        texHeight = 32;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 22.0F, 10.0F);
        tail.texOffs(24, 23).addBox(-1.5F, -3.0F, 4.0F, 3.0F, 4.0F, 5.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(-1.0F, -2.25F, 9.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        tail.texOffs(0, 15).addBox(0.0F, -4.75F, 12.0F, 0.0F, 7.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(35, 14).addBox(-2.0F, -6.0F, -6.0F, 4.0F, 6.0F, 8.0F, 0.0F, false);
        body.texOffs(21, 0).addBox(-1.5F, -5.0F, -11.0F, 3.0F, 5.0F, 5.0F, 0.0F, false);
        body.texOffs(30, 15).addBox(-1.0F, -3.6F, -15.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);
        body.texOffs(30, 15).addBox(-1.0F, -3.6F, -15.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);

        ModelRenderer middle = new ModelRenderer(this);
        middle.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(middle);
        middle.texOffs(16, 17).addBox(0.0F, -5.0F, 4.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);
        middle.texOffs(21, 8).addBox(0.0F, 0.0F, 7.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);
        middle.texOffs(0, 0).addBox(-2.0F, -6.0F, 2.0F, 4.0F, 6.0F, 13.0F, 0.0F, false);
        middle.texOffs(16, 12).addBox(0.5F, -10.0F, 8.0F, 0.0F, 4.0F, 7.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(2.0F, -1.0F, 12.0F);
        middle.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F, false);
        fin_left_r1.texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F, false);

        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(-2.0F, -1.0F, 12.0F);
        middle.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 5.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, -1.0F, -2.0F);
        body.addChild(fins);


        ModelRenderer fin_right_r2 = new ModelRenderer(this);
        fin_right_r2.setPos(-1.0F, 0.0F, 3.0F);
        fins.addChild(fin_right_r2);
        setRotationAngle(fin_right_r2, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r2.texOffs(21, 6).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_right_r3 = new ModelRenderer(this);
        fin_right_r3.setPos(-1.0F, 0.0F, -3.0F);
        fins.addChild(fin_right_r3);
        setRotationAngle(fin_right_r3, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r3.texOffs(21, 6).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_left_r2 = new ModelRenderer(this);
        fin_left_r2.setPos(3.0F, 0.0F, 3.0F);
        fins.addChild(fin_left_r2);
        setRotationAngle(fin_left_r2, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r2.texOffs(21, 6).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_left_r3 = new ModelRenderer(this);
        fin_left_r3.setPos(3.0F, 0.0F, -3.0F);
        fins.addChild(fin_left_r3);
        setRotationAngle(fin_left_r3, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r3.texOffs(21, 6).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
