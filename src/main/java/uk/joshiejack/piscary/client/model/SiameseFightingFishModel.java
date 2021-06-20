package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SiameseFightingFishModel extends AbstractFishModel {
    public SiameseFightingFishModel() {
        texWidth = 32;
        texHeight = 16;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 0.5F);
        setRotationAngle(tail, 0.0F, 0.0436F, 0.0F);
        tail.texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(0.0F, -3.4F, -0.5F, 0.0F, 7.0F, 7.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(11, 11).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        body.texOffs(8, 0).addBox(-1.0F, -1.6F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, -1.0F, -0.75F);
        body.addChild(fins);


        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(0.0F, 0.0F, 1.0F);
        fins.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.texOffs(0, 1).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(2.0F, 0.0F, 1.0F);
        fins.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.texOffs(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    }
}
