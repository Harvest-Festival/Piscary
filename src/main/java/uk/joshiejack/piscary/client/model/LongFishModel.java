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
        body.texOffs(0, 9).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-1.5F, -3.0F, -4F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-1.0F, -2.6F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        body.texOffs(0, 9).addBox(-2.0F, -4.0F, 6.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);
        body.texOffs(0, 9).addBox(0.0F, -5.0F, 5.0F, 0.0F, 1.0F, 7.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 22.0F, 6.0F);
        tail.texOffs(0, 9).addBox(-1.5F, -1.0F, 6.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        tail.texOffs(0, 9).addBox(-1.0F, -1.25F, 11F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        tail.texOffs(0, 9).addBox(-0.5F, -1.75F, 13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tail.texOffs(0, 9).addBox(-1.5F, -0.5F, 9.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer tail_fin = new ModelRenderer(this);
        tail_fin.setPos(0.0F, 0.0F, -3.0F);
        tail.addChild(tail_fin);
        setRotationAngle(tail_fin, 0.1309F, 0.0F, 0.0F);
        tail_fin.texOffs(0, 9).addBox(0.0F, 0.5F, 12.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
    }
}
