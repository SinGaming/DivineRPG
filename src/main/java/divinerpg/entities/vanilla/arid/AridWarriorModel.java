package divinerpg.entities.vanilla.arid;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class AridWarriorModel extends DivineModel<AridWarrior> implements IHasArm {
    RendererModel ear1;
    RendererModel leftarmBS1;
    RendererModel rightleg;
    RendererModel body;
    RendererModel body2;
    RendererModel body3;
    RendererModel body4;
    RendererModel leftarmBS2;
    RendererModel leftarmTS1;
    RendererModel leftarmTS2;
    RendererModel rightarmTS2;
    RendererModel rightarmBS1;
    RendererModel rightarmBS2;
    RendererModel rightarmTS1;
    RendererModel head;
    RendererModel ear2;
    RendererModel leftleg;
    RendererModel rightfoot;
    RendererModel rightlegpart;
    RendererModel rightlegpart2;
    RendererModel leftfoot;
    RendererModel leftlegpart2;
    RendererModel leftlegpart1;

    public AridWarriorModel() {
        textureWidth = 64;
        textureHeight = 32;

        ear1 = new RendererModel(this, 34, 0);
        ear1.addBox(-6F, -8F, -4F, 2, 3, 8);
        ear1.setRotationPoint(10F, -12F, -5F);
        ear1.setTextureSize(64, 32);
        ear1.mirror = true;
        setRotation(ear1, -0.8179294F, 0F, 0F);
        leftarmBS1 = new RendererModel(this, 28, 16);
        leftarmBS1.addBox(-1F, -1F, -2F, 7, 3, 4);
        leftarmBS1.setRotationPoint(8F, 4F, 0F);
        leftarmBS1.setTextureSize(64, 32);
        leftarmBS1.mirror = true;
        setRotation(leftarmBS1, 0F, 0F, 0F);
        rightleg = new RendererModel(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-5F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        body = new RendererModel(this, 16, 16);
        body.addBox(-4F, 6F, -2F, 14, 6, 4);
        body.setRotationPoint(-3F, 0F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        body2 = new RendererModel(this, 16, 16);
        body2.addBox(-4F, 6F, -2F, 14, 6, 4);
        body2.setRotationPoint(-3F, -14F, 0F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        body3 = new RendererModel(this, 16, 16);
        body3.addBox(-4F, 6F, -2F, 3, 8, 4);
        body3.setRotationPoint(8F, -8F, 0F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        body4 = new RendererModel(this, 16, 16);
        body4.addBox(-4F, 6F, -2F, 3, 8, 4);
        body4.setRotationPoint(-3F, -8F, 0F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, 0F, 0F, 0F);
        leftarmBS2 = new RendererModel(this, 40, 16);
        leftarmBS2.addBox(2F, 1F, -2F, 4, 6, 4);
        leftarmBS2.setRotationPoint(8F, 5F, 0F);
        leftarmBS2.setTextureSize(64, 32);
        leftarmBS2.mirror = true;
        setRotation(leftarmBS2, 0F, 0F, 0F);
        leftarmTS1 = new RendererModel(this, 28, 16);
        leftarmTS1.addBox(-1F, -1F, -2F, 7, 3, 4);
        leftarmTS1.setRotationPoint(8F, -7F, 0F);
        leftarmTS1.setTextureSize(64, 32);
        leftarmTS1.mirror = true;
        setRotation(leftarmTS1, 0F, 0F, 0F);
        leftarmTS2 = new RendererModel(this, 40, 16);
        leftarmTS2.addBox(2F, 1F, -2F, 4, 6, 4);
        leftarmTS2.setRotationPoint(8F, -6F, 0F);
        leftarmTS2.setTextureSize(64, 32);
        leftarmTS2.mirror = true;
        setRotation(leftarmTS2, 0F, 0F, 0F);
        rightarmTS2 = new RendererModel(this, 40, 16);
        rightarmTS2.addBox(-6F, 1F, -2F, 4, 6, 4);
        rightarmTS2.setRotationPoint(-8F, -6F, 0F);
        rightarmTS2.setTextureSize(64, 32);
        rightarmTS2.mirror = true;
        setRotation(rightarmTS2, 0F, 0F, 0F);
        rightarmBS1 = new RendererModel(this, 28, 16);
        rightarmBS1.addBox(-6F, -1F, -2F, 7, 3, 4);
        rightarmBS1.setRotationPoint(-8F, 4F, 0F);
        rightarmBS1.setTextureSize(64, 32);
        rightarmBS1.mirror = true;
        setRotation(rightarmBS1, 0F, 0F, 0F);
        rightarmBS2 = new RendererModel(this, 40, 16);
        rightarmBS2.addBox(-6F, 1F, -2F, 4, 6, 4);
        rightarmBS2.setRotationPoint(-8F, 5F, 0F);
        rightarmBS2.setTextureSize(64, 32);
        rightarmBS2.mirror = true;
        setRotation(rightarmBS2, 0F, 0F, 0F);
        rightarmTS1 = new RendererModel(this, 28, 16);
        rightarmTS1.addBox(-6F, -1F, -2F, 7, 3, 4);
        rightarmTS1.setRotationPoint(-8F, -7F, 0F);
        rightarmTS1.setTextureSize(64, 32);
        rightarmTS1.mirror = true;
        setRotation(rightarmTS1, 0F, 0F, 0F);
        head = new RendererModel(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -8F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        ear2 = new RendererModel(this, 34, 0);
        ear2.addBox(-6F, -8F, -4F, 2, 3, 8);
        ear2.setRotationPoint(0F, -12F, -5F);
        ear2.setTextureSize(64, 32);
        ear2.mirror = true;
        setRotation(ear2, -0.8179294F, 0F, 0F);
        leftleg = new RendererModel(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(5F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightfoot = new RendererModel(this, 0, 16);
        rightfoot.addBox(-4F, 9F, -4F, 8, 3, 8);
        rightfoot.setRotationPoint(-5F, 12F, 0F);
        rightfoot.setTextureSize(64, 32);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
        rightlegpart = new RendererModel(this, 0, 16);
        rightlegpart.addBox(-3F, 1F, -3F, 6, 3, 6);
        rightlegpart.setRotationPoint(-5F, 12F, 0F);
        rightlegpart.setTextureSize(64, 32);
        rightlegpart.mirror = true;
        setRotation(rightlegpart, 0F, 0F, 0F);
        rightlegpart2 = new RendererModel(this, 0, 16);
        rightlegpart2.addBox(-3F, 5F, -3F, 6, 3, 6);
        rightlegpart2.setRotationPoint(-5F, 12F, 0F);
        rightlegpart2.setTextureSize(64, 32);
        rightlegpart2.mirror = true;
        setRotation(rightlegpart2, 0F, 0F, 0F);
        leftfoot = new RendererModel(this, 0, 16);
        leftfoot.addBox(-4F, 9F, -4F, 8, 3, 8);
        leftfoot.setRotationPoint(5F, 12F, 0F);
        leftfoot.setTextureSize(64, 32);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
        leftlegpart2 = new RendererModel(this, 0, 16);
        leftlegpart2.addBox(-3F, 5F, -3F, 6, 3, 6);
        leftlegpart2.setRotationPoint(5F, 12F, 0F);
        leftlegpart2.setTextureSize(64, 32);
        leftlegpart2.mirror = true;
        setRotation(leftlegpart2, 0F, 0F, 0F);
        leftlegpart1 = new RendererModel(this, 0, 16);
        leftlegpart1.addBox(-3F, 1F, -3F, 6, 3, 6);
        leftlegpart1.setRotationPoint(5F, 12F, 0F);
        leftlegpart1.setTextureSize(64, 32);
        leftlegpart1.mirror = true;
        setRotation(leftlegpart1, 0F, 0F, 0F);
    }


    @Override
    public void setRotationAngles(AridWarrior entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        float zArmAngle = MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        float xArmAngle = MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        float headYawAngle = netHeadYaw / (180F / (float) Math.PI);
        float headPitchAngle = headPitch / (180F / (float) Math.PI);

        float unknownXAngle = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        float unknownXAngle2 = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

        float xAngleLeg = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        float anotherXAngleLeg = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;

        this.head.rotateAngleY = headYawAngle;
        this.head.rotateAngleX = headPitchAngle;
        this.ear1.rotateAngleY = headYawAngle;
        this.ear1.rotateAngleX = headPitchAngle;
        this.ear2.rotateAngleY = headYawAngle;
        this.ear2.rotateAngleX = headPitchAngle;

        this.rightarmBS1.rotateAngleX = unknownXAngle;
        this.rightarmBS2.rotateAngleX = unknownXAngle;
        this.rightarmTS1.rotateAngleX = unknownXAngle;
        this.rightarmTS2.rotateAngleX = unknownXAngle;

        this.leftarmBS1.rotateAngleX = unknownXAngle2;
        this.leftarmBS2.rotateAngleX = unknownXAngle2;
        this.leftarmTS1.rotateAngleX = unknownXAngle2;
        this.leftarmTS2.rotateAngleX = unknownXAngle2;

        this.rightarmBS1.rotateAngleZ = 0.0F;
        this.rightarmBS2.rotateAngleZ = 0.0F;
        this.rightarmTS1.rotateAngleZ = 0.0F;
        this.rightarmTS2.rotateAngleZ = 0.0F;

        this.leftarmBS1.rotateAngleZ = 0.0F;
        this.leftarmBS2.rotateAngleZ = 0.0F;
        this.leftarmTS1.rotateAngleZ = 0.0F;
        this.leftarmTS2.rotateAngleZ = 0.0F;

        this.rightleg.rotateAngleX = xAngleLeg;
        this.rightlegpart.rotateAngleX = xAngleLeg;
        this.rightlegpart2.rotateAngleX = xAngleLeg;
        this.rightfoot.rotateAngleX = xAngleLeg;

        this.leftleg.rotateAngleX = anotherXAngleLeg;
        this.leftlegpart1.rotateAngleX = anotherXAngleLeg;
        this.leftlegpart2.rotateAngleX = anotherXAngleLeg;
        this.leftfoot.rotateAngleX = anotherXAngleLeg;

        this.rightleg.rotateAngleY = 0.0F;
        this.rightlegpart.rotateAngleY = 0.0F;
        this.rightlegpart2.rotateAngleY = 0.0F;
        this.rightfoot.rotateAngleY = 0.0F;

        this.leftleg.rotateAngleY = 0.0F;
        this.leftlegpart1.rotateAngleY = 0.0F;
        this.leftlegpart2.rotateAngleY = 0.0F;
        this.leftfoot.rotateAngleY = 0.0F;

        this.rightarmBS1.rotateAngleY = 0.0F;
        this.rightarmBS2.rotateAngleY = 0.0F;
        this.rightarmTS1.rotateAngleY = 0.0F;
        this.rightarmTS2.rotateAngleY = 0.0F;

        this.leftarmBS1.rotateAngleY = 0.0F;
        this.leftarmBS2.rotateAngleY = 0.0F;
        this.leftarmTS2.rotateAngleY = 0.0F;
        this.leftarmTS1.rotateAngleY = 0.0F;

        this.rightarmBS1.rotateAngleZ += zArmAngle;
        this.rightarmBS2.rotateAngleZ += zArmAngle;
        this.rightarmBS1.rotateAngleZ += zArmAngle;
        this.rightarmBS2.rotateAngleZ += zArmAngle;

        this.leftarmBS1.rotateAngleZ -= zArmAngle;
        this.leftarmBS2.rotateAngleZ -= zArmAngle;
        this.leftarmTS1.rotateAngleZ -= zArmAngle;
        this.leftarmTS2.rotateAngleZ -= zArmAngle;

        this.rightarmBS1.rotateAngleX += xArmAngle;
        this.rightarmBS2.rotateAngleX += xArmAngle;
        this.rightarmTS1.rotateAngleX += xArmAngle;
        this.rightarmTS2.rotateAngleX += xArmAngle;

        this.leftarmBS1.rotateAngleX -= xArmAngle;
        this.leftarmBS2.rotateAngleX -= xArmAngle;
        this.leftarmTS1.rotateAngleX -= xArmAngle;
        this.leftarmTS2.rotateAngleX -= xArmAngle;
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void postRenderArm(float scale, HandSide side) {
        if (side == HandSide.LEFT) {
            leftarmBS1.postRender(scale);
        } else {
            rightarmBS1.postRender(scale);
        }

    }
}
