package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class AnglerfishModel extends AbstractFishModel {
    public AnglerfishModel() {
        texWidth = 64;
        texHeight = 64;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 3.0F);
        tail.texOffs(18, 13).addBox(0.0F, -7.3F, 12F, 0.0F, 6.0F, 6.0F, 0.0F, false);
        tail.texOffs(0, 30).addBox(-2.0F, -6.0F, 9F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 0).addBox(-5.0F, -9.0F, 1F, 10.0F, 9.0F, 10.0F, 0.0F, false);
        body.texOffs(0, 19).addBox(-4.0F, -9.0F, 11F, 8.0F, 7.0F, 1.0F, 0.0F, false);
        body.texOffs(30, 0).addBox(-3.0F, -8.0F, 12F, 6.0F, 5.0F, 1.0F, 0.0F, false);
        body.texOffs(16, 25).addBox(-3.0F, -8.45F, -1F, 6.0F, 6.0F, 2.0F, 0.0F, false);
        body.texOffs(0, 21).addBox(0.0F, -12.0F, 6F, 0.0F, 3.0F, 6.0F, 0.0F, false);
        body.texOffs(12, 25).addBox(0.0F, -2.0F, 11F, 0.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer light = new ModelRenderer(this);
        light.setPos(1.0F, -3.0F, -8.0F);
        body.addChild(light);
        setRotationAngle(light, 0.6109F, 0.0F, 0.0F);
        light.texOffs(0, 0).addBox(-2.0F, -6.0F, 10F, 2.0F, 8.0F, 2.0F, 0.0F, false);
    }
}
