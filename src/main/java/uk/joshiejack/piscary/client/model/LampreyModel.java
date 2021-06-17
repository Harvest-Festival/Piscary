package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class LampreyModel extends AbstractFishModel {
    public LampreyModel() {
        texWidth = 64;
        texHeight = 64;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 22.0F, 10.0F);
        tail.texOffs(0, 0).addBox(-1.0F, -0.5F, 6F, 2.0F, 3.0F, 5.0F, 0.0F, false);
        tail.texOffs(29, 31).addBox(-1.0F, 0.25F, 9F, 2.0F, 2.0F, 7.0F, 0.0F, false);
        tail.texOffs(0, 2).addBox(0.0F, -1.75F, 10F, 0.0F, 2.0F, 6.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(21, 18).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
        body.texOffs(23, 0).addBox(-1.5F, 0.0F, -6.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
        body.texOffs(0, 18).addBox(-1.5F, -2.5F, -8.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        body.texOffs(9, 0).addBox(-1.0F, -1.25F, -9.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer middle = new ModelRenderer(this);
        middle.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(middle);
        middle.texOffs(0, 4).addBox(0.0F, -3.0F, 4F, 0.0F, 1.0F, 6.0F, 0.0F, false);
        middle.texOffs(0, 0).addBox(-2.0F, -3.0F, 1F, 4.0F, 3.0F, 15.0F, 0.0F, false);
        middle.texOffs(0, 18).addBox(-1.5F, 0.0F, 1F, 3.0F, 1.0F, 15.0F, 0.0F, false);
        middle.texOffs(21, 19).addBox(0.5F, -5.0F, 5F, 0.0F, 2.0F, 10.0F, 0.0F, false);
    }
}
