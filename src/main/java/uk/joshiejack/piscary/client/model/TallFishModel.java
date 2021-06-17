package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class TallFishModel extends AbstractFishModel {
    public TallFishModel() {
        texWidth = 32;
        texHeight = 32;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 20.0F, 3.0F);
        tail.texOffs(4, 2).addBox(0.0F, 0.0F, 8.0F, 0.0F, 4.0F, 1.0F, 0.0F, false);
        tail.texOffs(2, 4).addBox(0.0F, 0.0F, 9.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        tail.texOffs(0, 4).addBox(0.0F, 3.0F, 9.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        tail.texOffs(0, 1).addBox(0.0F, 1.0F, 6.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 0).addBox(-1.0F, -4.0F, 3.0F, 2.0F, 4.0F, 7.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        body.texOffs(0, 8).addBox(0.0F, -5.0F, 4.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);
        body.texOffs(0, 7).addBox(0.0F, 0.0F, 5.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);
    }
}
