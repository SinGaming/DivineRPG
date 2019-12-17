package divinerpg.entities.skythern.megalith;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class MegalithModel extends DivineModel<Megalith> {
    RendererModel torso;
    RendererModel body;
    RendererModel rightArm;
    RendererModel rightHand;
    RendererModel leftArm;
    RendererModel leftHand;
    RendererModel rightLeg;
    RendererModel leftLeg;
    RendererModel upperBody;
    RendererModel head;

    public MegalithModel() {
        textureWidth = 256;
        textureHeight = 64;

        torso = new RendererModel(this, 64, 0);
        torso.addBox(0F, 0F, 0F, 16, 16, 8);
        torso.setRotationPoint(-8F, -8F, -4F);
        torso.setTextureSize(256, 64);
        torso.mirror = true;
        setRotation(torso, 0F, 0F, 0F);
        body = new RendererModel(this, 0, 0);
        body.addBox(0F, 0F, 0F, 20, 12, 12);
        body.setRotationPoint(-10F, -20F, -6F);
        body.setTextureSize(256, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightArm = new RendererModel(this, 30, 42);
        rightArm.addBox(-3F, 3F, -3F, 6, 16, 6);
        rightArm.setRotationPoint(-13F, -32F, 0F);
        rightArm.setTextureSize(256, 64);
        rightArm.mirror = true;
        setRotation(rightArm, 0F, 0F, 0.0872665F);
        rightHand = new RendererModel(this, 0, 24);
        rightHand.addBox(-4.5F, 19F, -4.5F, 9, 8, 9);
        rightHand.setRotationPoint(-13F, -32F, 0F);
        rightHand.setTextureSize(256, 64);
        rightHand.mirror = true;
        setRotation(rightHand, 0F, 0F, 0.0872665F);
        leftArm = new RendererModel(this, 30, 42);
        leftArm.addBox(-3F, 3F, -3F, 6, 16, 6);
        leftArm.setRotationPoint(13F, -32F, 0F);
        leftArm.setTextureSize(256, 64);
        leftArm.mirror = true;
        setRotation(leftArm, 0F, 0F, -0.0872665F);
        leftHand = new RendererModel(this, 0, 24);
        leftHand.addBox(-4.5F, 19F, -4.5F, 9, 8, 9);
        leftHand.setRotationPoint(13F, -32F, 0F);
        leftHand.setTextureSize(256, 64);
        leftHand.mirror = true;
        setRotation(leftHand, 0F, 0F, -0.0872665F);
        rightLeg = new RendererModel(this, 54, 38);
        rightLeg.addBox(-4F, 0F, -5F, 8, 16, 10);
        rightLeg.setRotationPoint(-6F, 8F, 0F);
        rightLeg.setTextureSize(256, 64);
        rightLeg.mirror = true;
        setRotation(rightLeg, 0F, 0F, 0F);
        leftLeg = new RendererModel(this, 54, 38);
        leftLeg.addBox(-4F, 0F, -5F, 8, 16, 10);
        leftLeg.setRotationPoint(6F, 8F, 0F);
        leftLeg.setTextureSize(256, 64);
        leftLeg.mirror = true;
        setRotation(leftLeg, 0F, 0F, 0F);
        upperBody = new RendererModel(this, 112, 0);
        upperBody.addBox(0F, 0F, 0F, 22, 12, 14);
        upperBody.setRotationPoint(-11F, -32F, -7F);
        upperBody.setTextureSize(256, 64);
        upperBody.mirror = true;
        setRotation(upperBody, 0F, 0F, 0F);
        head = new RendererModel(this, 0, 49);
        head.addBox(-3F, 0F, -3F, 6, 9, 6);
        head.setRotationPoint(0F, -41F, 0F);
        head.setTextureSize(256, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Megalith entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.leftLeg.rotateAngleX = this.rightHand.rotateAngleX = this.rightArm.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
        this.rightLeg.rotateAngleX = this.leftHand.rotateAngleX = this.leftArm.rotateAngleX = MathHelper.cos(limbSwing + (float) Math.PI) * limbSwingAmount * 0.7f;

    }
}
