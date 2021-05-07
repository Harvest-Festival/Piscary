package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class FatFishModel extends AbstractFishModel {
    public FatFishModel() {
        texWidth = 64;
        texHeight = 64;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 3.0F);
        tail.setTexSize(8, 6).addBox(0.0F, -5.8F, 14F, 0.0F, 2.0F, 2.0F, 0.0F, false);
        tail.setTexSize(8, 8).addBox(0.0F, -1.3F, 14F, 0.0F, 2.0F, 2.0F, 0.0F, false);
        tail.setTexSize(0, 4).addBox(0.0F, -4.3F, 10F, 0.0F, 4.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.setTexSize(0, 0).addBox(-2.0F, -8.0F, -1F, 4.0F, 8.0F, 14.0F, 0.0F, false);
        body.setTexSize(0, 0).addBox(-2.0F, -5.2F, -3F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        body.setTexSize(8, 18).addBox(0.0F, -10.0F, 7F, 0.0F, 2.0F, 4.0F, 0.0F, false);
        body.setTexSize(0, 0).addBox(0.0F, 0.0F, 5F, 0.0F, 2.0F, 6.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, 23.0F, -2.0F);

        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(-1.0F, -1.0F, -2.0F);
        fins.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.setTexSize(0, 18).addBox(0.0F, -1.0F, 7F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(3.0F, -1.0F, -2.0F);
        fins.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.setTexSize(0, 8).addBox(0.0F, -1.0F, 7F, 0.0F, 2.0F, 4.0F, 0.0F, false);
    }
}
