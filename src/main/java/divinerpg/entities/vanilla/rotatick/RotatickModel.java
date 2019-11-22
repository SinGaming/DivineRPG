package divinerpg.entities.vanilla.rotatick;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class RotatickModel extends DivineModel<Rotatick> {
    RendererModel Box8;
    RendererModel Body;
    RendererModel LegRBT;
    RendererModel LegLBT;
    RendererModel LegRFT;
    RendererModel LegLFT;
    RendererModel LegRMT;
    RendererModel LegLMT;
    RendererModel LegRF;
    RendererModel LegLF;
    RendererModel LegLM;
    RendererModel LegLB;
    RendererModel LegRM;
    RendererModel LegRB;
    RendererModel LegRFB;
    RendererModel LegLFB;
    RendererModel LegRMB;
    RendererModel LegRBB;
    RendererModel LegLMB;
    RendererModel LegLBB;
    RendererModel LegLFC;
    RendererModel LegLMC;
    RendererModel LegLBC;
    RendererModel LegRBC;
    RendererModel LegRMC;
    RendererModel LegRFC;
    RendererModel Box1;
    RendererModel Box2;
    RendererModel Box3;
    RendererModel Box4;
    RendererModel Box5;
    RendererModel Box6;
    RendererModel Box7;
    RendererModel Box9;
    RendererModel Head;
    RendererModel Box10;

    public RotatickModel() {
        textureWidth = 64;
        textureHeight = 32;

        Box8 = new RendererModel(this, 41, 13);
        Box8.addBox(-4F, -3F, -1F, 3, 3, 1);
        Box8.setRotationPoint(5F, 19F, -10F);
        Box8.setTextureSize(64, 32);
        Box8.mirror = true;
        setRotation(Box8, 0.6320364F, 0F, 0F);
        Body = new RendererModel(this, 40, 7);
        Body.addBox(-4F, -2F, -3F, 6, 19, 6);
        Body.setRotationPoint(0F, 14F, -3F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 1.570796F, 0F, 0F);
        LegRBT = new RendererModel(this, 42, 15);
        LegRBT.addBox(-2F, -1F, -2F, 4, 2, 4);
        LegRBT.setRotationPoint(-4.5F, 16F, 12F);
        LegRBT.setTextureSize(64, 32);
        LegRBT.mirror = true;
        setRotation(LegRBT, 0F, 0F, 0F);
        LegLBT = new RendererModel(this, 42, 15);
        LegLBT.addBox(-2F, -1F, -2F, 4, 2, 4);
        LegLBT.setRotationPoint(2.5F, 16F, 12F);
        LegLBT.setTextureSize(64, 32);
        LegLBT.mirror = true;
        setRotation(LegLBT, 0F, 0F, 0F);
        LegRFT = new RendererModel(this, 42, 15);
        LegRFT.addBox(-2F, -1F, -2F, 4, 2, 4);
        LegRFT.setRotationPoint(-4.5F, 16F, -4F);
        LegRFT.setTextureSize(64, 32);
        LegRFT.mirror = true;
        setRotation(LegRFT, 0F, 0F, 0F);
        LegLFT = new RendererModel(this, 42, 15);
        LegLFT.addBox(-2F, -1F, -2F, 4, 2, 4);
        LegLFT.setRotationPoint(2.5F, 16F, -4F);
        LegLFT.setTextureSize(64, 32);
        LegLFT.mirror = true;
        setRotation(LegLFT, 0F, 0F, 0F);
        LegRMT = new RendererModel(this, 42, 15);
        LegRMT.addBox(-2F, -1F, -1F, 4, 2, 4);
        LegRMT.setRotationPoint(-4.5F, 16F, 2F);
        LegRMT.setTextureSize(64, 32);
        LegRMT.mirror = true;
        setRotation(LegRMT, 0F, 0F, 0F);
        LegLMT = new RendererModel(this, 42, 15);
        LegLMT.addBox(-2F, -1F, -1F, 4, 2, 4);
        LegLMT.setRotationPoint(2.5F, 16F, 2F);
        LegLMT.setTextureSize(64, 32);
        LegLMT.mirror = true;
        setRotation(LegLMT, 0F, 0F, 0F);
        LegRF = new RendererModel(this, 0, 18);
        LegRF.addBox(-1F, 0F, -1F, 2, 8, 2);
        LegRF.setRotationPoint(-4.5F, 16F, -4F);
        LegRF.setTextureSize(64, 32);
        LegRF.mirror = true;
        setRotation(LegRF, 0F, 0F, 0F);
        LegLF = new RendererModel(this, 0, 18);
        LegLF.addBox(-1F, 0F, -1F, 2, 8, 2);
        LegLF.setRotationPoint(2.5F, 16F, -4F);
        LegLF.setTextureSize(64, 32);
        LegLF.mirror = true;
        setRotation(LegLF, 0F, 0F, 0F);
        LegLM = new RendererModel(this, 0, 18);
        LegLM.addBox(-1F, 0F, 0F, 2, 8, 2);
        LegLM.setRotationPoint(-4.5F, 16F, 2F);
        LegLM.setTextureSize(64, 32);
        LegLM.mirror = true;
        setRotation(LegLM, 0F, 0F, 0F);
        LegLB = new RendererModel(this, 0, 18);
        LegLB.addBox(-1F, 0F, -1F, 2, 8, 2);
        LegLB.setRotationPoint(-4.5F, 16F, 12F);
        LegLB.setTextureSize(64, 32);
        LegLB.mirror = true;
        setRotation(LegLB, 0F, 0F, 0F);
        LegRM = new RendererModel(this, 0, 18);
        LegRM.addBox(-1F, 0F, 0F, 2, 8, 2);
        LegRM.setRotationPoint(2.5F, 16F, 2F);
        LegRM.setTextureSize(64, 32);
        LegRM.mirror = true;
        setRotation(LegRM, 0F, 0F, 0F);
        LegRB = new RendererModel(this, 0, 18);
        LegRB.addBox(-1F, 0F, -1F, 2, 8, 2);
        LegRB.setRotationPoint(2.5F, 16F, 12F);
        LegRB.setTextureSize(64, 32);
        LegRB.mirror = true;
        setRotation(LegRB, 0F, 0F, 0F);
        LegRFB = new RendererModel(this, 14, 26);
        LegRFB.addBox(-2F, 6F, -2F, 4, 2, 4);
        LegRFB.setRotationPoint(-4.5F, 16F, -4F);
        LegRFB.setTextureSize(64, 32);
        LegRFB.mirror = true;
        setRotation(LegRFB, 0F, 0F, 0F);
        LegLFB = new RendererModel(this, 14, 26);
        LegLFB.addBox(-2F, 6F, -2F, 4, 2, 4);
        LegLFB.setRotationPoint(2.5F, 16F, -4F);
        LegLFB.setTextureSize(64, 32);
        LegLFB.mirror = true;
        setRotation(LegLFB, 0F, 0F, 0F);
        LegRMB = new RendererModel(this, 14, 26);
        LegRMB.addBox(-2F, 6F, -1F, 4, 2, 4);
        LegRMB.setRotationPoint(-4.5F, 16F, 2F);
        LegRMB.setTextureSize(64, 32);
        LegRMB.mirror = true;
        setRotation(LegRMB, 0F, 0F, 0F);
        LegRBB = new RendererModel(this, 14, 26);
        LegRBB.addBox(-2F, 6F, -2F, 4, 2, 4);
        LegRBB.setRotationPoint(-4.5F, 16F, 12F);
        LegRBB.setTextureSize(64, 32);
        LegRBB.mirror = true;
        setRotation(LegRBB, 0F, 0F, 0F);
        LegLMB = new RendererModel(this, 14, 26);
        LegLMB.addBox(-2F, 6F, -1F, 4, 2, 4);
        LegLMB.setRotationPoint(2.5F, 16F, 2F);
        LegLMB.setTextureSize(64, 32);
        LegLMB.mirror = true;
        setRotation(LegLMB, 0F, 0F, 0F);
        LegLBB = new RendererModel(this, 14, 26);
        LegLBB.addBox(-2F, 6F, -2F, 4, 2, 4);
        LegLBB.setRotationPoint(2.5F, 16F, 12F);
        LegLBB.setTextureSize(64, 32);
        LegLBB.mirror = true;
        setRotation(LegLBB, 0F, 0F, 0F);
        LegLFC = new RendererModel(this, 14, 18);
        LegLFC.addBox(-2F, 3F, -2F, 4, 1, 4);
        LegLFC.setRotationPoint(2.5F, 16F, -4F);
        LegLFC.setTextureSize(64, 32);
        LegLFC.mirror = true;
        setRotation(LegLFC, 0F, 0F, 0F);
        LegLMC = new RendererModel(this, 14, 18);
        LegLMC.addBox(-2F, 3F, -1F, 4, 1, 4);
        LegLMC.setRotationPoint(2.5F, 16F, 2F);
        LegLMC.setTextureSize(64, 32);
        LegLMC.mirror = true;
        setRotation(LegLMC, 0F, 0F, 0F);
        LegLBC = new RendererModel(this, 14, 18);
        LegLBC.addBox(-2F, 3F, -2F, 4, 1, 4);
        LegLBC.setRotationPoint(2.5F, 16F, 12F);
        LegLBC.setTextureSize(64, 32);
        LegLBC.mirror = true;
        setRotation(LegLBC, 0F, 0F, 0F);
        LegRBC = new RendererModel(this, 14, 18);
        LegRBC.addBox(-2F, 3F, -2F, 4, 1, 4);
        LegRBC.setRotationPoint(-4.5F, 16F, 12F);
        LegRBC.setTextureSize(64, 32);
        LegRBC.mirror = true;
        setRotation(LegRBC, 0F, 0F, 0F);
        LegRMC = new RendererModel(this, 14, 18);
        LegRMC.addBox(-2F, 3F, -1F, 4, 1, 4);
        LegRMC.setRotationPoint(-4.5F, 16F, 2F);
        LegRMC.setTextureSize(64, 32);
        LegRMC.mirror = true;
        setRotation(LegRMC, 0F, 0F, 0F);
        LegRFC = new RendererModel(this, 14, 18);
        LegRFC.addBox(-2F, 3F, -2F, 4, 1, 4);
        LegRFC.setRotationPoint(-4.5F, 16F, -4F);
        LegRFC.setTextureSize(64, 32);
        LegRFC.mirror = true;
        setRotation(LegRFC, 0F, 0F, 0F);
        Box1 = new RendererModel(this, 42, 21);
        Box1.addBox(-2F, -3F, 3F, 4, 4, 4);
        Box1.setRotationPoint(-1F, 11F, 7F);
        Box1.setTextureSize(64, 32);
        Box1.mirror = true;
        setRotation(Box1, 0F, 0F, 0F);
        Box2 = new RendererModel(this, 41, 13);
        Box2.addBox(-4F, -3F, -1F, 3, 3, 1);
        Box2.setRotationPoint(-2F, 19F, -10F);
        Box2.setTextureSize(64, 32);
        Box2.mirror = true;
        setRotation(Box2, 0.6320364F, 0F, 0F);
        Box3 = new RendererModel(this, 45, 16);
        Box3.addBox(-3F, 0F, -2F, 2, 2, 2);
        Box3.setRotationPoint(5F, 13.5F, -6F);
        Box3.setTextureSize(64, 32);
        Box3.mirror = true;
        setRotation(Box3, 0F, 0F, 0F);
        Box4 = new RendererModel(this, 42, 12);
        Box4.addBox(-2F, -3F, -2F, 4, 7, 4);
        Box4.setRotationPoint(-1F, 11.5F, -3F);
        Box4.setTextureSize(64, 32);
        Box4.mirror = true;
        setRotation(Box4, 0F, 0F, 0F);
        Box5 = new RendererModel(this, 42, 12);
        Box5.addBox(-2F, -3F, -2F, 4, 6, 4);
        Box5.setRotationPoint(-1F, 11.5F, 2F);
        Box5.setTextureSize(64, 32);
        Box5.mirror = true;
        setRotation(Box5, 0F, 0F, 0F);
        Box6 = new RendererModel(this, 42, 21);
        Box6.addBox(-2F, -3F, -2F, 4, 6, 4);
        Box6.setRotationPoint(-1F, 11.5F, 7F);
        Box6.setTextureSize(64, 32);
        Box6.mirror = true;
        setRotation(Box6, 0F, 0F, 0F);
        Box7 = new RendererModel(this, 41, 13);
        Box7.addBox(-4F, -3F, -1F, 1, 1, 6);
        Box7.setRotationPoint(6F, 19.5F, -9F);
        Box7.setTextureSize(64, 32);
        Box7.mirror = true;
        setRotation(Box7, 0.6320364F, 0F, 0F);
        Box9 = new RendererModel(this, 41, 13);
        Box9.addBox(-4F, -3F, -1F, 1, 1, 6);
        Box9.setRotationPoint(-1F, 19.5F, -9F);
        Box9.setTextureSize(64, 32);
        Box9.mirror = true;
        setRotation(Box9, 0.6320364F, 0F, 0F);
        Head = new RendererModel(this, 0, 0);
        Head.addBox(-3F, -3F, -2F, 6, 6, 4);
        Head.setRotationPoint(-1F, 13.5F, -7F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Box10 = new RendererModel(this, 45, 16);
        Box10.addBox(-3F, 0F, -2F, 2, 2, 2);
        Box10.setRotationPoint(-3F, 13.5F, -6F);
        Box10.setTextureSize(64, 32);
        Box10.mirror = true;
        setRotation(Box10, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Rotatick entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.Head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.Head.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.LegRBT.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegLB.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRBB.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRMT.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegLM.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRMB.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRFT.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegLF.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRFB.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRFC.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRMC.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegRBC.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.LegLBT.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegRB.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLBB.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLMT.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegRM.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLMB.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLFT.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegRF.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLFB.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLFC.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLMC.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.LegLFC.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;

        this.LegRBT.rotateAngleY = 0.0F;
        this.LegRB.rotateAngleY = 0.0F;
        this.LegRBB.rotateAngleY = 0.0F;
        this.LegRMT.rotateAngleY = 0.0F;
        this.LegRM.rotateAngleY = 0.0F;
        this.LegRMB.rotateAngleY = 0.0F;
        this.LegRFT.rotateAngleY = 0.0F;
        this.LegRF.rotateAngleY = 0.0F;
        this.LegRFB.rotateAngleY = 0.0F;

        this.LegLBT.rotateAngleY = 0.0F;
        this.LegLB.rotateAngleY = 0.0F;
        this.LegLBB.rotateAngleY = 0.0F;
        this.LegLMT.rotateAngleY = 0.0F;
        this.LegLM.rotateAngleY = 0.0F;
        this.LegLMB.rotateAngleY = 0.0F;
        this.LegLFT.rotateAngleY = 0.0F;
        this.LegLF.rotateAngleY = 0.0F;
        this.LegLFB.rotateAngleY = 0.0F;
    }
}
