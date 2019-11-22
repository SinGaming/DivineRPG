package divinerpg.entities.vanilla.glacon;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class GlaconModel extends DivineModel<Glacon> {
    RendererModel head;
    RendererModel body;
    RendererModel legRBT;
    RendererModel legLBT;
    RendererModel legRFT;
    RendererModel legLFT;
    RendererModel udders;
    RendererModel legRMT;
    RendererModel legLMT;
    RendererModel legLF;
    RendererModel legRF;
    RendererModel legLM;
    RendererModel legLB;
    RendererModel legRM;
    RendererModel legRB;
    RendererModel body1;
    RendererModel body2;
    RendererModel body3;
    RendererModel body4;
    RendererModel body5;
    RendererModel body7;
    RendererModel body6;
    RendererModel body8;
    RendererModel body9;
    RendererModel body10;
    RendererModel body11;
    RendererModel body12;
    RendererModel body13;
    RendererModel body14;
    RendererModel body15;
    RendererModel body16;
    RendererModel body17;
    RendererModel body18;
    RendererModel body19;
    RendererModel body20;
    RendererModel body21;
    RendererModel body22;
    RendererModel body23;
    RendererModel body24;
    RendererModel body25;
    RendererModel body26;

    public GlaconModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 11);
        this.head.setRotationPoint(0.0F, 6.0F, -13.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new RendererModel(this, 32, 6);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 2, 5, 2);
        this.body.setRotationPoint(12.0F, -4.0F, 17.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        setRotation(this.body, 0.7853982F, 0.0F, 0.0F);
        this.legRBT = new RendererModel(this, 32, 18);
        this.legRBT.addBox(-4.0F, -4.0F, -2.0F, 5, 4, 5);
        this.legRBT.setRotationPoint(-3.0F, 12.0F, 8.0F);
        this.legRBT.setTextureSize(64, 32);
        this.legRBT.mirror = true;
        setRotation(this.legRBT, 0.0F, 0.0F, 0.0F);
        this.legLBT = new RendererModel(this, 32, 18);
        this.legLBT.addBox(-1.0F, -4.0F, -2.0F, 5, 4, 5);
        this.legLBT.setRotationPoint(3.0F, 12.0F, 8.0F);
        this.legLBT.setTextureSize(64, 32);
        this.legLBT.mirror = true;
        setRotation(this.legLBT, 0.0F, 0.0F, 0.0F);
        this.legRFT = new RendererModel(this, 32, 18);
        this.legRFT.addBox(-4.0F, -4.0F, -3.0F, 5, 4, 5);
        this.legRFT.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.legRFT.setTextureSize(64, 32);
        this.legRFT.mirror = true;
        setRotation(this.legRFT, 0.0F, 0.0F, 0.0F);
        this.legLFT = new RendererModel(this, 32, 18);
        this.legLFT.addBox(-1.0F, -4.0F, -3.0F, 5, 4, 5);
        this.legLFT.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.legLFT.setTextureSize(64, 32);
        this.legLFT.mirror = true;
        setRotation(this.legLFT, 0.0F, 0.0F, 0.0F);
        this.udders = new RendererModel(this, 52, 0);
        this.udders.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 2);
        this.udders.setRotationPoint(0.0F, 14.0F, 6.0F);
        this.udders.setTextureSize(64, 32);
        this.udders.mirror = true;
        setRotation(this.udders, 1.570796F, 0.0F, 0.0F);
        this.legRMT = new RendererModel(this, 32, 17);
        this.legRMT.addBox(-1.0F, -4.0F, 0.0F, 5, 4, 6);
        this.legRMT.setRotationPoint(-6.0F, 12.0F, -1.733333F);
        this.legRMT.setTextureSize(64, 32);
        this.legRMT.mirror = true;
        setRotation(this.legRMT, 0.0F, 0.0F, 0.0F);
        this.legLMT = new RendererModel(this, 32, 17);
        this.legLMT.addBox(0.0F, -4.0F, 0.0F, 5, 4, 6);
        this.legLMT.setRotationPoint(2.0F, 12.0F, -1.7F);
        this.legLMT.setTextureSize(64, 32);
        this.legLMT.mirror = true;
        setRotation(this.legLMT, 0.0F, 0.0F, 0.0F);
        this.legLF = new RendererModel(this, 0, 16);
        this.legLF.addBox(-1.0F, 0.0F, -3.0F, 4, 12, 4);
        this.legLF.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.legLF.setTextureSize(64, 32);
        this.legLF.mirror = true;
        setRotation(this.legLF, 0.0F, 0.0F, 0.0F);
        this.legRF = new RendererModel(this, 0, 16);
        this.legRF.addBox(-3.0F, 0.0F, -3.0F, 4, 12, 4);
        this.legRF.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.legRF.setTextureSize(64, 32);
        this.legRF.mirror = true;
        setRotation(this.legRF, 0.0F, 0.0F, 0.0F);
        this.legLM = new RendererModel(this, 0, 15);
        this.legLM.addBox(0.0F, 0.0F, 0.0F, 4, 12, 5);
        this.legLM.setRotationPoint(2.0F, 12.0F, -1.7F);
        this.legLM.setTextureSize(64, 32);
        this.legLM.mirror = true;
        setRotation(this.legLM, 0.0F, 0.0F, 0.0F);
        this.legLB = new RendererModel(this, 0, 16);
        this.legLB.addBox(-1.0F, 0.0F, -2.0F, 4, 12, 4);
        this.legLB.setRotationPoint(3.0F, 12.0F, 8.0F);
        this.legLB.setTextureSize(64, 32);
        this.legLB.mirror = true;
        setRotation(this.legLB, 0.0F, 0.0F, 0.0F);
        this.legRM = new RendererModel(this, 0, 15);
        this.legRM.addBox(0.0F, 0.0F, 0.0F, 4, 12, 5);
        this.legRM.setRotationPoint(-6.0F, 12.0F, -1.733333F);
        this.legRM.setTextureSize(64, 32);
        this.legRM.mirror = true;
        setRotation(this.legRM, 0.0F, 0.0F, 0.0F);
        this.legRB = new RendererModel(this, 0, 16);
        this.legRB.addBox(-3.0F, 0.0F, -2.0F, 4, 12, 4);
        this.legRB.setRotationPoint(-3.0F, 12.0F, 8.0F);
        this.legRB.setTextureSize(64, 32);
        this.legRB.mirror = true;
        setRotation(this.legRB, 0.0F, 0.0F, 0.0F);
        this.body1 = new RendererModel(this, 20, 26);
        this.body1.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body1.setRotationPoint(12.0F, 5.0F, 18.0F);
        this.body1.setTextureSize(64, 32);
        this.body1.mirror = true;
        setRotation(this.body1, 1.570796F, 0.0F, 0.0F);
        this.body2 = new RendererModel(this, 20, 4);
        this.body2.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10);
        this.body2.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        setRotation(this.body2, 1.570796F, 0.0F, 0.0F);
        this.body3 = new RendererModel(this, 20, 26);
        this.body3.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body3.setRotationPoint(12.0F, 2.0F, 2.0F);
        this.body3.setTextureSize(64, 32);
        this.body3.mirror = true;
        setRotation(this.body3, 1.570796F, 0.0F, 0.0F);
        this.body4 = new RendererModel(this, 32, 6);
        this.body4.addBox(-6.0F, -10.0F, -7.0F, 2, 18, 2);
        this.body4.setRotationPoint(12.0F, -4.0F, -4.0F);
        this.body4.setTextureSize(64, 32);
        this.body4.mirror = true;
        setRotation(this.body4, 2.356194F, 0.0F, 0.0F);
        this.body5 = new RendererModel(this, 32, 6);
        this.body5.addBox(-6.0F, -10.0F, -7.0F, 4, 3, 4);
        this.body5.setRotationPoint(11.0F, 14.0F, 6.0F);
        this.body5.setTextureSize(64, 32);
        this.body5.mirror = true;
        setRotation(this.body5, -2.356194F, 0.0F, 0.0F);
        this.body7 = new RendererModel(this, 20, 26);
        this.body7.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body7.setRotationPoint(-3.0F, 2.0F, 2.0F);
        this.body7.setTextureSize(64, 32);
        this.body7.mirror = true;
        setRotation(this.body7, 1.570796F, 0.0F, 0.0F);
        this.body6 = new RendererModel(this, 20, 26);
        this.body6.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body6.setRotationPoint(-3.0F, 5.0F, 18.0F);
        this.body6.setTextureSize(64, 32);
        this.body6.mirror = true;
        setRotation(this.body6, 1.570796F, 0.0F, 0.0F);
        this.body8 = new RendererModel(this, 32, 6);
        this.body8.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 4);
        this.body8.setRotationPoint(7.0F, 7.0F, 5.0F);
        this.body8.setTextureSize(64, 32);
        this.body8.mirror = true;
        setRotation(this.body8, -2.356194F, 0.0F, 0.0F);
        this.body9 = new RendererModel(this, 32, 6);
        this.body9.addBox(-6.0F, -10.0F, -7.0F, 2, 18, 2);
        this.body9.setRotationPoint(-2.0F, -4.0F, -4.0F);
        this.body9.setTextureSize(64, 32);
        this.body9.mirror = true;
        setRotation(this.body9, 2.356194F, 0.0F, 0.0F);
        this.body10 = new RendererModel(this, 32, 6);
        this.body10.addBox(-6.0F, -10.0F, -7.0F, 2, 5, 2);
        this.body10.setRotationPoint(-2.0F, -4.0F, 17.0F);
        this.body10.setTextureSize(64, 32);
        this.body10.mirror = true;
        setRotation(this.body10, 0.7853982F, 0.0F, 0.0F);
        this.body11 = new RendererModel(this, 20, 26);
        this.body11.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body11.setRotationPoint(-3.0F, 5.0F, 8.0F);
        this.body11.setTextureSize(64, 32);
        this.body11.mirror = true;
        setRotation(this.body11, 1.570796F, 0.0F, 0.0F);
        this.body12 = new RendererModel(this, 32, 6);
        this.body12.addBox(-6.0F, -10.0F, -7.0F, 2, 18, 2);
        this.body12.setRotationPoint(-2.0F, -1.0F, 2.0F);
        this.body12.setTextureSize(64, 32);
        this.body12.mirror = true;
        setRotation(this.body12, 2.356194F, 0.0F, 0.0F);
        this.body13 = new RendererModel(this, 32, 6);
        this.body13.addBox(-6.0F, -10.0F, -7.0F, 2, 5, 2);
        this.body13.setRotationPoint(-2.0F, -1.0F, 23.0F);
        this.body13.setTextureSize(64, 32);
        this.body13.mirror = true;
        setRotation(this.body13, 0.7853982F, 0.0F, 0.0F);
        this.body14 = new RendererModel(this, 20, 26);
        this.body14.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 3);
        this.body14.setRotationPoint(12.0F, 5.0F, 8.0F);
        this.body14.setTextureSize(64, 32);
        this.body14.mirror = true;
        setRotation(this.body14, 1.570796F, 0.0F, 0.0F);
        this.body15 = new RendererModel(this, 32, 6);
        this.body15.addBox(-6.0F, -10.0F, -7.0F, 2, 18, 2);
        this.body15.setRotationPoint(12.0F, -1.0F, 2.0F);
        this.body15.setTextureSize(64, 32);
        this.body15.mirror = true;
        setRotation(this.body15, 2.356194F, 0.0F, 0.0F);
        this.body16 = new RendererModel(this, 32, 6);
        this.body16.addBox(-6.0F, -10.0F, -7.0F, 2, 5, 2);
        this.body16.setRotationPoint(12.0F, -1.0F, 23.0F);
        this.body16.setTextureSize(64, 32);
        this.body16.mirror = true;
        setRotation(this.body16, 0.7853982F, 0.0F, 0.0F);
        this.body17 = new RendererModel(this, 32, 6);
        this.body17.addBox(-6.0F, -10.0F, -7.0F, 2, 9, 2);
        this.body17.setRotationPoint(12.0F, 14.0F, 5.0F);
        this.body17.setTextureSize(64, 32);
        this.body17.mirror = true;
        setRotation(this.body17, -2.356194F, 0.0F, 0.0F);
        this.body18 = new RendererModel(this, 32, 6);
        this.body18.addBox(-6.0F, -10.0F, -7.0F, 1, 9, 2);
        this.body18.setRotationPoint(8.0F, 7.0F, 4.0F);
        this.body18.setTextureSize(64, 32);
        this.body18.mirror = true;
        setRotation(this.body18, -2.356194F, 0.0F, 0.0F);
        this.body19 = new RendererModel(this, 32, 6);
        this.body19.addBox(-6.0F, -10.0F, -7.0F, 4, 3, 4);
        this.body19.setRotationPoint(-3.0F, 14.0F, 6.0F);
        this.body19.setTextureSize(64, 32);
        this.body19.mirror = true;
        setRotation(this.body19, -2.356194F, 0.0F, 0.0F);
        this.body20 = new RendererModel(this, 32, 6);
        this.body20.addBox(-6.0F, -10.0F, -7.0F, 2, 9, 2);
        this.body20.setRotationPoint(-2.0F, 14.0F, 5.0F);
        this.body20.setTextureSize(64, 32);
        this.body20.mirror = true;
        setRotation(this.body20, -2.356194F, 0.0F, 0.0F);
        this.body21 = new RendererModel(this, 32, 6);
        this.body21.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 4);
        this.body21.setRotationPoint(2.0F, 7.0F, 5.0F);
        this.body21.setTextureSize(64, 32);
        this.body21.mirror = true;
        setRotation(this.body21, -2.356194F, 0.0F, 0.0F);
        this.body22 = new RendererModel(this, 32, 6);
        this.body22.addBox(-6.0F, -10.0F, -7.0F, 1, 9, 2);
        this.body22.setRotationPoint(3.0F, 7.0F, 4.0F);
        this.body22.setTextureSize(64, 32);
        this.body22.mirror = true;
        setRotation(this.body22, -2.356194F, 0.0F, 0.0F);
        this.body23 = new RendererModel(this, 32, 6);
        this.body23.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 4);
        this.body23.setRotationPoint(2.0F, 14.0F, 6.0F);
        this.body23.setTextureSize(64, 32);
        this.body23.mirror = true;
        setRotation(this.body23, -2.356194F, 0.0F, 0.0F);
        this.body24 = new RendererModel(this, 32, 6);
        this.body24.addBox(-6.0F, -10.0F, -7.0F, 1, 9, 2);
        this.body24.setRotationPoint(3.0F, 14.0F, 5.0F);
        this.body24.setTextureSize(64, 32);
        this.body24.mirror = true;
        setRotation(this.body24, -2.356194F, 0.0F, 0.0F);
        this.body25 = new RendererModel(this, 32, 6);
        this.body25.addBox(-6.0F, -10.0F, -7.0F, 1, 9, 2);
        this.body25.setRotationPoint(8.0F, 14.0F, 5.0F);
        this.body25.setTextureSize(64, 32);
        this.body25.mirror = true;
        setRotation(this.body25, -2.356194F, 0.0F, 0.0F);
        this.body26 = new RendererModel(this, 32, 6);
        this.body26.addBox(-6.0F, -10.0F, -7.0F, 3, 3, 4);
        this.body26.setRotationPoint(7.0F, 14.0F, 6.0F);
        this.body26.setTextureSize(64, 32);
        this.body26.mirror = true;
        setRotation(this.body26, -2.356194F, 0.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(Glacon entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.legRB.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        this.legRBT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        this.legRM.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        this.legRMT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        this.legRF.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
        this.legRFT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);

        this.legLB.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
        this.legLBT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
        this.legLM.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
        this.legLMT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
        this.legLF.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
        this.legLFT.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount);
    }
}
