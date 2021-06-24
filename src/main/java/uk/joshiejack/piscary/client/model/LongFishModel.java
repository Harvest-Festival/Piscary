package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LongFishModel extends AbstractFishModel {
    public LongFishModel() {
        texWidth = 32;
        texHeight = 32;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);

        ModelRenderer head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(head);
        head.texOffs(0, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 4.0F, 10.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        head.texOffs(18, 4).addBox(-1.0F, -2.6F, -7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer fins = new ModelRenderer(this);
        fins.setPos(-1.0F, -1.0F, -2.0F);
        body.addChild(fins);


        ModelRenderer fin_right_r1 = new ModelRenderer(this);
        fin_right_r1.setPos(-1.0F, 0.0F, 3.0F);
        fins.addChild(fin_right_r1);
        setRotationAngle(fin_right_r1, -0.1745F, -0.6109F, 0.2618F);
        fin_right_r1.texOffs(0, 10).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer fin_left_r1 = new ModelRenderer(this);
        fin_left_r1.setPos(3.0F, 0.0F, 3.0F);
        fins.addChild(fin_left_r1);
        setRotationAngle(fin_left_r1, -0.1745F, 0.6109F, -0.2618F);
        fin_left_r1.texOffs(0, 3).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(-0.3F, 22.0F, 6.0F);
        tail.texOffs(17, 14).addBox(-1.1947F, -1.0F, 6.9933F, 3.0F, 3.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 16).addBox(-0.6947F, -1.25F, 12.9933F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        tail.texOffs(17, 14).addBox(-0.1947F, -1.75F, 14.9933F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tail.texOffs(18, 0).addBox(-1.1947F, -0.5F, 10.9933F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer tailfin = new ModelRenderer(this);
        tailfin.setPos(0.0F, 0.0F, 16.0F);
        tail.addChild(tailfin);
        setRotationAngle(tailfin, 0.3491F, 0.0F, 0.0F);
        tailfin.texOffs(7, 27).addBox(-3.1947F, -0.75F, -1.0067F, 7.0F, 0.0F, 5.0F, 0.0F, false);

        ModelRenderer backfin = new ModelRenderer(this);
        backfin.setPos(0.0F, 0.0F, -3.0F);
        tail.addChild(backfin);
        setRotationAngle(backfin, 0.1309F, 0.0F, 0.0F);
        backfin.texOffs(0, 0).addBox(0.3053F, 1.4128F, 13.9335F, 0.0F, 2.0F, 5.0F, 0.0F, false);

        ModelRenderer middle = new ModelRenderer(this);
        middle.setPos(0.3F, 2.0F, -6.0F);
        tail.addChild(middle);
        middle.texOffs(17, 15).addBox(0.0F, -6.0F, 6.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
        middle.texOffs(0, 23).addBox(0.0F, 0.0F, 9.0F, 0.0F, 3.0F, 4.0F, 0.0F, false);
        middle.texOffs(0, 14).addBox(-2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);
    }
}
