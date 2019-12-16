package divinerpg.entities.twilight.mystic;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.MobEntity;

public class MysticModel extends BipedModel<MobEntity> {

    RendererModel staffProng2;
    RendererModel staffPole;
    RendererModel staffCap;
    RendererModel staffProng1;
    RendererModel staffProng3;
    RendererModel staffProng4;
    RendererModel ray2;
    RendererModel ray8;
    RendererModel ray6;
    RendererModel ray4;
    RendererModel ray1;
    RendererModel ray7;
    RendererModel ray5;
    RendererModel ray3;

    public MysticModel() {
        textureWidth = 64;
        textureHeight = 32;

        staffProng2 = new RendererModel(this, 40, 20);
        staffProng2.addBox(4F, -7F, -3F, 1, 2, 1);
        staffProng2.setRotationPoint(5F, 2F, 0F);
        staffProng2.setTextureSize(64, 32);
        staffProng2.mirror = true;
        staffPole = new RendererModel(this, 53, 0);
        staffPole.addBox(2F, -4F, -5F, 2, 22, 2);
        staffPole.setRotationPoint(5F, 2F, 0F);
        staffPole.setTextureSize(64, 32);
        staffPole.mirror = true;
        setRotation(staffPole, 0F, 0F, 0F);
        staffCap = new RendererModel(this, 40, 25);
        staffCap.addBox(1F, -5F, -6F, 4, 2, 4);
        staffCap.setRotationPoint(5F, 2F, 0F);
        staffCap.setTextureSize(64, 32);
        staffCap.mirror = true;
        setRotation(staffCap, 0F, 0F, 0F);
        staffProng1 = new RendererModel(this, 40, 20);
        staffProng1.addBox(1F, -7F, -3F, 1, 2, 1);
        staffProng1.setRotationPoint(5F, 2F, 0F);
        staffProng1.setTextureSize(64, 32);
        staffProng1.mirror = true;
        setRotation(staffProng1, 0F, 0F, 0F);
        staffProng3 = new RendererModel(this, 40, 20);
        staffProng3.addBox(4F, -7F, -6F, 1, 2, 1);
        staffProng3.setRotationPoint(5F, 2F, 0F);
        staffProng3.setTextureSize(64, 32);
        staffProng3.mirror = true;
        setRotation(staffProng3, 0F, 0F, 0F);
        staffProng4 = new RendererModel(this, 40, 20);
        staffProng4.addBox(1F, -7F, -6F, 1, 2, 1);
        staffProng4.setRotationPoint(5F, 2F, 0F);
        staffProng4.setTextureSize(64, 32);
        staffProng4.mirror = true;
        setRotation(staffProng4, 0F, 0F, 0F);
        ray2 = new RendererModel(this, 0, 23);
        ray2.addBox(-7F, -1F, -1F, 2, 2, 2);
        ray2.setRotationPoint(0F, -9F, -1F);
        ray2.setTextureSize(64, 32);
        ray2.mirror = true;
        setRotation(ray2, 0F, 0F, -0.7853982F);
        ray8 = new RendererModel(this, 0, 23);
        ray8.addBox(-1F, 6F, -1F, 2, 2, 2);
        ray8.setRotationPoint(0F, -9F, -1F);
        ray8.setTextureSize(64, 32);
        ray8.mirror = true;
        setRotation(ray8, 0F, 0F, -0.7853982F);
        ray6 = new RendererModel(this, 0, 23);
        ray6.addBox(5F, -1F, -1F, 2, 2, 2);
        ray6.setRotationPoint(0F, -9F, -1F);
        ray6.setTextureSize(64, 32);
        ray6.mirror = true;
        setRotation(ray6, 0F, 0F, -0.7853982F);
        ray4 = new RendererModel(this, 0, 23);
        ray4.addBox(-1F, -7F, -1F, 2, 2, 2);
        ray4.setRotationPoint(0F, -9F, -1F);
        ray4.setTextureSize(64, 32);
        ray4.mirror = true;
        setRotation(ray4, 0F, 0F, -0.7853982F);
        ray1 = new RendererModel(this, 0, 23);
        ray1.addBox(-1F, 6F, -1F, 2, 2, 2);
        ray1.setRotationPoint(0F, -9F, -1F);
        ray1.setTextureSize(64, 32);
        ray1.mirror = true;
        setRotation(ray1, 0F, 0F, 0F);
        ray7 = new RendererModel(this, 0, 23);
        ray7.addBox(5F, -1F, -1F, 2, 2, 2);
        ray7.setRotationPoint(0F, -9F, -1F);
        ray7.setTextureSize(64, 32);
        ray7.mirror = true;
        setRotation(ray7, 0F, 0F, 0F);
        ray5 = new RendererModel(this, 0, 23);
        ray5.addBox(-1F, -7F, -1F, 2, 2, 2);
        ray5.setRotationPoint(0F, -9F, -1F);
        ray5.setTextureSize(64, 32);
        ray5.mirror = true;
        setRotation(ray5, 0F, 0F, 0F);
        ray3 = new RendererModel(this, 0, 23);
        ray3.addBox(-7F, -1F, -1F, 2, 2, 2);
        ray3.setRotationPoint(0F, -9F, -1F);
        ray3.setTextureSize(64, 32);
        ray3.mirror = true;
        setRotation(ray3, 0F, 0F, 0F);

        bipedBody = new RendererModel(this, 24, 0);
        bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
        bipedBody.setRotationPoint(0F, 0F, 0F);
        bipedBody.setTextureSize(64, 32);
        bipedBody.mirror = true;

        bipedRightArm = new RendererModel(this, 10, 23);
        bipedRightArm.addBox(-6F, 4F, -2F, 4, 4, 4);
        bipedRightArm.setRotationPoint(-4F, 2F, -2F);
        bipedRightArm.setTextureSize(64, 32);

        bipedRightLeg = new RendererModel(this, 0, 12);
        bipedRightLeg.addBox(-2F, 10F, -6F, 4, 2, 8);
        bipedRightLeg.setRotationPoint(-3F, 12F, 0F);
        bipedRightLeg.setTextureSize(64, 32);
        bipedRightLeg.mirror = true;

        bipedLeftLeg = new RendererModel(this, 0, 12);
        bipedLeftLeg.addBox(-2F, 10F, -6F, 4, 2, 8);
        bipedLeftLeg.setRotationPoint(3F, 12F, 0F);
        bipedLeftLeg.setTextureSize(64, 32);
        bipedLeftLeg.mirror = true;

        bipedLeftArm = new RendererModel(this, 10, 23);
        bipedLeftArm.addBox(1F, 4F, -4F, 4, 4, 4);
        bipedLeftArm.setRotationPoint(5F, 2F, 0F);
        bipedLeftArm.setTextureSize(64, 32);
        bipedLeftArm.mirror = true;

        bipedHead = new RendererModel(this, 0, 0);
        bipedHead.addBox(-3F, -6F, -3F, 6, 6, 6);
        bipedHead.setRotationPoint(0F, -5F, -1F);
        bipedHead.setTextureSize(64, 32);
        bipedHead.mirror = true;
    }

    @Override
    public void render(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        staffProng2.render(scale);
        staffPole.render(scale);
        staffCap.render(scale);
        staffProng1.render(scale);
        staffProng3.render(scale);
        staffProng4.render(scale);
        ray2.render(scale);
        ray8.render(scale);
        ray6.render(scale);
        ray4.render(scale);
        ray1.render(scale);
        ray7.render(scale);
        ray5.render(scale);
        ray3.render(scale);
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.ray1.rotateAngleZ = this.ray3.rotateAngleZ = this.ray5.rotateAngleZ = this.ray7.rotateAngleZ = ageInTicks / 10;
        this.ray2.rotateAngleZ = this.ray4.rotateAngleZ = this.ray6.rotateAngleZ = this.ray8.rotateAngleZ = ageInTicks / 10 - 0.7853982F;
    }
}
