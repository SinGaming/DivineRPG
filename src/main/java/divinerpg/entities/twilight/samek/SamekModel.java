package divinerpg.entities.twilight.samek;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class SamekModel extends BipedModel<MobEntity> {
    ModelRenderer secondHead;

    public SamekModel() {
        this.secondHead = new ModelRenderer(this, 0, 0);
        this.secondHead.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.secondHead.setRotationPoint(1.466667F, -8.0F, -4.0F);
        this.secondHead.setTextureSize(64, 32);
        this.secondHead.mirror = true;

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-11.0F, -8.0F, -4.0F, 8, 8, 8);
        this.bipedHead.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.bipedHead.setTextureSize(64, 32);
        this.bipedHead.mirror = true;
    }

    @Override
    public void render(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        secondHead.render(scale);
    }

    @Override
    public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.secondHead.rotateAngleX = bipedHead.rotateAngleX;
        this.secondHead.rotateAngleY = bipedHead.rotateAngleY;
    }
}
