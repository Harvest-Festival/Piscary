package uk.joshiejack.piscary.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ElectricRayModel extends RayModel {
    public ElectricRayModel() {
        super(0.75F);
        texWidth = 128;
        texHeight = 128;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 20.0F, 15.0F);
        tail.texOffs(0, 0).addBox(-1.5F, 3.0F, -1.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
        tail.texOffs(0, 9).addBox(-2.5F, 4.0F, 7.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 26).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 1.0F, 19.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-5.0F, -3.0F, -8.0F, 10.0F, 3.0F, 23.0F, 0.0F, false);

        wing_left = new ModelRenderer(this);
        wing_left.setPos(4.0F, 21.0F, 4.0F);
        wing_left.texOffs(43, 0).addBox(0.0F, 1.0F, -9.0F, 6.0F, 2.0F, 18.0F, 0.0F, false);
        wing_left.texOffs(22, 48).addBox(6.0F, 2.0F, -8.0F, 5.0F, 1.0F, 12.0F, 0.0F, false);

        wing_right = new ModelRenderer(this);
        wing_right.setPos(-4.0F, 21.0F, 4.0F);
        wing_right.texOffs(36, 28).addBox(-6.0F, 1.0F, -9.0F, 6.0F, 2.0F, 18.0F, 0.0F, false);
        wing_right.texOffs(0, 46).addBox(-11.0F, 2.0F, -8.0F, 5.0F, 1.0F, 12.0F, 0.0F, false);
    }
}
