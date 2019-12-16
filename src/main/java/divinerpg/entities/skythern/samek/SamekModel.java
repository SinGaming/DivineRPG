package divinerpg.entities.skythern.samek;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class SamekModel extends BipedModel<Samek> {
    RendererModel secondHead;

    public SamekModel() {
        this.secondHead = new RendererModel(this, 0, 0);
        this.secondHead.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.secondHead.setRotationPoint(1.466667F, -8.0F, -4.0F);
        this.secondHead.setTextureSize(64, 32);
        this.secondHead.mirror = true;

        this.bipedHead = new RendererModel(this, 0, 0);
        this.bipedHead.addBox(-11.0F, -8.0F, -4.0F, 8, 8, 8);
        this.bipedHead.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.bipedHead.setTextureSize(64, 32);
        this.bipedHead.mirror = true;
    }

    @Override
    public void render(Samek entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        secondHead.render(scale);
    }

    @Override
    public void setRotationAngles(Samek entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.secondHead.rotateAngleX = bipedHead.rotateAngleX;
        this.secondHead.rotateAngleY = bipedHead.rotateAngleY;
    }
}
