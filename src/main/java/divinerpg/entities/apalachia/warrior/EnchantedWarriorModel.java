package divinerpg.entities.apalachia.warrior;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EnchantedWarriorModel extends BipedModel<EnchantedWarrior> {
    ModelRenderer hornleft;
    ModelRenderer hornright;

    public EnchantedWarriorModel() {
        this.hornleft = new ModelRenderer(this, 38, 2);
        this.hornleft.addBox(0.0F, 0.0F, 0.0F, 8, 4, 4);
        this.hornleft.setRotationPoint(4.0F, -8.0F, -2.0F);
        this.hornleft.setTextureSize(64, 32);
        this.hornleft.mirror = true;

        this.hornright = new ModelRenderer(this, 38, 2);
        this.hornright.addBox(-8.0F, 0.0F, 0.0F, 8, 4, 4);
        this.hornright.setRotationPoint(-4.0F, -8.0F, -2.0F);
        this.hornright.setTextureSize(64, 32);
        this.hornright.mirror = true;

        this.bipedHead.addChild(hornright);
        this.bipedHead.addChild(hornleft);
    }
}
