package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class SmallFishModel extends AbstractFishModel {
    public SmallFishModel() {
        texWidth = 32;
        texHeight = 32;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 5.0F);
        tail.texOffs(11, 2).addBox(0.0F, -1.4F, 1.25F, 0.0F, 3.0F, 2.0F, 0.0F, false);
        tail.texOffs(11, 8).addBox(0.0F, -0.4F, -0.75F, 0.0F, 1.0F, 2.0F, 0.0F, false);
        //THIRD NUMBER = coordinates of the fishy!
        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
        body.texOffs(12, 12).addBox(-1.0F, -1.6F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        body.texOffs(1, 11).addBox(0.0F, -4.0F, 1.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);
        body.texOffs(11, 11).addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);
        body.texOffs(11, 11).addBox(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, -1.0F, -2.0F);
        body.addChild(fins);

        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(0.0F, 0.0F, 2.0F);
        fins.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.texOffs(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(2.0F, 0.0F, 2.0F);
        fins.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.texOffs(0, 0).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    }
}
