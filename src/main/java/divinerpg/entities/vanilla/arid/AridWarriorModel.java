package divinerpg.entities.vanilla.arid;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class AridWarriorModel extends DivineModel<AridWarrior> implements IHasArm {
    ModelRenderer ear1;
    ModelRenderer leftarmBS1;
    ModelRenderer rightleg;
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer leftarmBS2;
    ModelRenderer leftarmTS1;
    ModelRenderer leftarmTS2;
    ModelRenderer rightarmTS2;
    ModelRenderer rightarmBS1;
    ModelRenderer rightarmBS2;
    ModelRenderer rightarmTS1;
    ModelRenderer head;
    ModelRenderer ear2;
    ModelRenderer leftleg;
    ModelRenderer rightfoot;
    ModelRenderer rightlegpart;
    ModelRenderer rightlegpart2;
    ModelRenderer leftfoot;
    ModelRenderer leftlegpart2;
    ModelRenderer leftlegpart1;

    public AridWarriorModel() {
        textureWidth = 64;
        textureHeight = 32;

        ear1 = new ModelRenderer(this, 34, 0);
        ear1.func_228300_a_(-6F, -8F, -4F, 2, 3, 8);
        ear1.setRotationPoint(10F, -12F, -5F);
        ear1.setTextureSize(64, 32);
        ear1.mirror = true;
        setRotation(ear1, -0.8179294F, 0F, 0F);
        leftarmBS1 = new ModelRenderer(this, 28, 16);
        leftarmBS1.func_228300_a_(-1F, -1F, -2F, 7, 3, 4);
        leftarmBS1.setRotationPoint(8F, 4F, 0F);
        leftarmBS1.setTextureSize(64, 32);
        leftarmBS1.mirror = true;
        setRotation(leftarmBS1, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.func_228300_a_(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-5F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.func_228300_a_(-4F, 6F, -2F, 14, 6, 4);
        body.setRotationPoint(-3F, 0F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 16, 16);
        body2.func_228300_a_(-4F, 6F, -2F, 14, 6, 4);
        body2.setRotationPoint(-3F, -14F, 0F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        body3 = new ModelRenderer(this, 16, 16);
        body3.func_228300_a_(-4F, 6F, -2F, 3, 8, 4);
        body3.setRotationPoint(8F, -8F, 0F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        body4 = new ModelRenderer(this, 16, 16);
        body4.func_228300_a_(-4F, 6F, -2F, 3, 8, 4);
        body4.setRotationPoint(-3F, -8F, 0F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, 0F, 0F, 0F);
        leftarmBS2 = new ModelRenderer(this, 40, 16);
        leftarmBS2.func_228300_a_(2F, 1F, -2F, 4, 6, 4);
        leftarmBS2.setRotationPoint(8F, 5F, 0F);
        leftarmBS2.setTextureSize(64, 32);
        leftarmBS2.mirror = true;
        setRotation(leftarmBS2, 0F, 0F, 0F);
        leftarmTS1 = new ModelRenderer(this, 28, 16);
        leftarmTS1.func_228300_a_(-1F, -1F, -2F, 7, 3, 4);
        leftarmTS1.setRotationPoint(8F, -7F, 0F);
        leftarmTS1.setTextureSize(64, 32);
        leftarmTS1.mirror = true;
        setRotation(leftarmTS1, 0F, 0F, 0F);
        leftarmTS2 = new ModelRenderer(this, 40, 16);
        leftarmTS2.func_228300_a_(2F, 1F, -2F, 4, 6, 4);
        leftarmTS2.setRotationPoint(8F, -6F, 0F);
        leftarmTS2.setTextureSize(64, 32);
        leftarmTS2.mirror = true;
        setRotation(leftarmTS2, 0F, 0F, 0F);
        rightarmTS2 = new ModelRenderer(this, 40, 16);
        rightarmTS2.func_228300_a_(-6F, 1F, -2F, 4, 6, 4);
        rightarmTS2.setRotationPoint(-8F, -6F, 0F);
        rightarmTS2.setTextureSize(64, 32);
        rightarmTS2.mirror = true;
        setRotation(rightarmTS2, 0F, 0F, 0F);
        rightarmBS1 = new ModelRenderer(this, 28, 16);
        rightarmBS1.func_228300_a_(-6F, -1F, -2F, 7, 3, 4);
        rightarmBS1.setRotationPoint(-8F, 4F, 0F);
        rightarmBS1.setTextureSize(64, 32);
        rightarmBS1.mirror = true;
        setRotation(rightarmBS1, 0F, 0F, 0F);
        rightarmBS2 = new ModelRenderer(this, 40, 16);
        rightarmBS2.func_228300_a_(-6F, 1F, -2F, 4, 6, 4);
        rightarmBS2.setRotationPoint(-8F, 5F, 0F);
        rightarmBS2.setTextureSize(64, 32);
        rightarmBS2.mirror = true;
        setRotation(rightarmBS2, 0F, 0F, 0F);
        rightarmTS1 = new ModelRenderer(this, 28, 16);
        rightarmTS1.func_228300_a_(-6F, -1F, -2F, 7, 3, 4);
        rightarmTS1.setRotationPoint(-8F, -7F, 0F);
        rightarmTS1.setTextureSize(64, 32);
        rightarmTS1.mirror = true;
        setRotation(rightarmTS1, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.func_228300_a_(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -8F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        ear2 = new ModelRenderer(this, 34, 0);
        ear2.func_228300_a_(-6F, -8F, -4F, 2, 3, 8);
        ear2.setRotationPoint(0F, -12F, -5F);
        ear2.setTextureSize(64, 32);
        ear2.mirror = true;
        setRotation(ear2, -0.8179294F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.func_228300_a_(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(5F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightfoot = new ModelRenderer(this, 0, 16);
        rightfoot.func_228300_a_(-4F, 9F, -4F, 8, 3, 8);
        rightfoot.setRotationPoint(-5F, 12F, 0F);
        rightfoot.setTextureSize(64, 32);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
        rightlegpart = new ModelRenderer(this, 0, 16);
        rightlegpart.func_228300_a_(-3F, 1F, -3F, 6, 3, 6);
        rightlegpart.setRotationPoint(-5F, 12F, 0F);
        rightlegpart.setTextureSize(64, 32);
        rightlegpart.mirror = true;
        setRotation(rightlegpart, 0F, 0F, 0F);
        rightlegpart2 = new ModelRenderer(this, 0, 16);
        rightlegpart2.func_228300_a_(-3F, 5F, -3F, 6, 3, 6);
        rightlegpart2.setRotationPoint(-5F, 12F, 0F);
        rightlegpart2.setTextureSize(64, 32);
        rightlegpart2.mirror = true;
        setRotation(rightlegpart2, 0F, 0F, 0F);
        leftfoot = new ModelRenderer(this, 0, 16);
        leftfoot.func_228300_a_(-4F, 9F, -4F, 8, 3, 8);
        leftfoot.setRotationPoint(5F, 12F, 0F);
        leftfoot.setTextureSize(64, 32);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
        leftlegpart2 = new ModelRenderer(this, 0, 16);
        leftlegpart2.func_228300_a_(-3F, 5F, -3F, 6, 3, 6);
        leftlegpart2.setRotationPoint(5F, 12F, 0F);
        leftlegpart2.setTextureSize(64, 32);
        leftlegpart2.mirror = true;
        setRotation(leftlegpart2, 0F, 0F, 0F);
        leftlegpart1 = new ModelRenderer(this, 0, 16);
        leftlegpart1.func_228300_a_(-3F, 1F, -3F, 6, 3, 6);
        leftlegpart1.setRotationPoint(5F, 12F, 0F);
        leftlegpart1.setTextureSize(64, 32);
        leftlegpart1.mirror = true;
        setRotation(leftlegpart1, 0F, 0F, 0F);
    }


    @Override
    public void setRotationAngles(AridWarrior entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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

    @Override
    public void func_225599_a_(HandSide side, MatrixStack stack) {

        switch (side) {
            case LEFT:
                leftarmBS1.func_228307_a_(stack);
                break;

            case RIGHT:
                rightarmBS1.func_228307_a_(stack);
                break;
        }
    }
}
