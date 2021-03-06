package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FatFishModel extends AbstractFishModel {
    public FatFishModel() {
        texWidth = 64;
        texHeight = 32;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 9.75F);
        tail.texOffs(22, 2).addBox(0.0F, -6.3F, 4.0F, 0.0F, 8.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 4).addBox(0.0F, -4.3F, 0.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 0).addBox(-2.0F, -8.0F, -3.0F, 4.0F, 8.0F, 14.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-2.0F, -5.2F, -5.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        body.texOffs(0, 18).addBox(0.0F, -12.0F, 3.0F, 0.0F, 4.0F, 6.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(0.0F, 0.0F, 3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, 23.0F, -2.0F);

        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(-1.0F, -1.0F, 3.0F);
        fins.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.texOffs(0, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(3.0F, -1.0F, 3.0F);
        fins.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.texOffs(0, 8).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);
    }
}
