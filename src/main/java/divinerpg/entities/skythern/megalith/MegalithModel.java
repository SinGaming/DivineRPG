package divinerpg.entities.skythern.megalith;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MegalithModel extends DivineModel<Megalith> {
    ModelRenderer torso;
    ModelRenderer body;
    ModelRenderer rightArm;
    ModelRenderer rightHand;
    ModelRenderer leftArm;
    ModelRenderer leftHand;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
    ModelRenderer upperBody;
    ModelRenderer head;

    public MegalithModel() {
        textureWidth = 256;
        textureHeight = 64;

        torso = new ModelRenderer(this, 64, 0);
        torso.func_228300_a_(0F, 0F, 0F, 16, 16, 8);
        torso.setRotationPoint(-8F, -8F, -4F);
        torso.setTextureSize(256, 64);
        torso.mirror = true;
        setRotation(torso, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 0);
        body.func_228300_a_(0F, 0F, 0F, 20, 12, 12);
        body.setRotationPoint(-10F, -20F, -6F);
        body.setTextureSize(256, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightArm = new ModelRenderer(this, 30, 42);
        rightArm.func_228300_a_(-3F, 3F, -3F, 6, 16, 6);
        rightArm.setRotationPoint(-13F, -32F, 0F);
        rightArm.setTextureSize(256, 64);
        rightArm.mirror = true;
        setRotation(rightArm, 0F, 0F, 0.0872665F);
        rightHand = new ModelRenderer(this, 0, 24);
        rightHand.func_228300_a_(-4.5F, 19F, -4.5F, 9, 8, 9);
        rightHand.setRotationPoint(-13F, -32F, 0F);
        rightHand.setTextureSize(256, 64);
        rightHand.mirror = true;
        setRotation(rightHand, 0F, 0F, 0.0872665F);
        leftArm = new ModelRenderer(this, 30, 42);
        leftArm.func_228300_a_(-3F, 3F, -3F, 6, 16, 6);
        leftArm.setRotationPoint(13F, -32F, 0F);
        leftArm.setTextureSize(256, 64);
        leftArm.mirror = true;
        setRotation(leftArm, 0F, 0F, -0.0872665F);
        leftHand = new ModelRenderer(this, 0, 24);
        leftHand.func_228300_a_(-4.5F, 19F, -4.5F, 9, 8, 9);
        leftHand.setRotationPoint(13F, -32F, 0F);
        leftHand.setTextureSize(256, 64);
        leftHand.mirror = true;
        setRotation(leftHand, 0F, 0F, -0.0872665F);
        rightLeg = new ModelRenderer(this, 54, 38);
        rightLeg.func_228300_a_(-4F, 0F, -5F, 8, 16, 10);
        rightLeg.setRotationPoint(-6F, 8F, 0F);
        rightLeg.setTextureSize(256, 64);
        rightLeg.mirror = true;
        setRotation(rightLeg, 0F, 0F, 0F);
        leftLeg = new ModelRenderer(this, 54, 38);
        leftLeg.func_228300_a_(-4F, 0F, -5F, 8, 16, 10);
        leftLeg.setRotationPoint(6F, 8F, 0F);
        leftLeg.setTextureSize(256, 64);
        leftLeg.mirror = true;
        setRotation(leftLeg, 0F, 0F, 0F);
        upperBody = new ModelRenderer(this, 112, 0);
        upperBody.func_228300_a_(0F, 0F, 0F, 22, 12, 14);
        upperBody.setRotationPoint(-11F, -32F, -7F);
        upperBody.setTextureSize(256, 64);
        upperBody.mirror = true;
        setRotation(upperBody, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 49);
        head.func_228300_a_(-3F, 0F, -3F, 6, 9, 6);
        head.setRotationPoint(0F, -41F, 0F);
        head.setTextureSize(256, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Megalith entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.leftLeg.rotateAngleX = this.rightHand.rotateAngleX = this.rightArm.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
        this.rightLeg.rotateAngleX = this.leftHand.rotateAngleX = this.leftArm.rotateAngleX = MathHelper.cos(limbSwing + (float) Math.PI) * limbSwingAmount * 0.7f;

    }
}
