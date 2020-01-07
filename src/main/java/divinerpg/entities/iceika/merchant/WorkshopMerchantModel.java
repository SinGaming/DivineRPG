package divinerpg.entities.iceika.merchant;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class WorkshopMerchantModel extends DivineModel<WorkshopMerchant> {
    //fields
    RendererModel head;
    RendererModel body;
    RendererModel rightarmtop;
    RendererModel rightlegbottom;
    RendererModel rightleg;
    RendererModel leftlegbottom;
    RendererModel leftleg;
    RendererModel rightarm;
    RendererModel leftarm;
    RendererModel leftarmtop;

    public WorkshopMerchantModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new RendererModel(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -7F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new RendererModel(this, 30, 14);
        body.addBox(-4F, 0F, -2F, 8, 14, 4);
        body.setRotationPoint(0F, -7F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarmtop = new RendererModel(this, 37, 16);
        rightarmtop.addBox(-6F, -3F, -2F, 8, 5, 6);
        rightarmtop.setRotationPoint(-6F, -5F, -1F);
        rightarmtop.setTextureSize(64, 32);
        rightarmtop.mirror = true;
        setRotation(rightarmtop, 0F, 0F, 0F);
        rightlegbottom = new RendererModel(this, 0, 16);
        rightlegbottom.addBox(-3F, 8F, -3F, 6, 9, 6);
        rightlegbottom.setRotationPoint(-4F, 7F, 0F);
        rightlegbottom.setTextureSize(64, 32);
        rightlegbottom.mirror = true;
        setRotation(rightlegbottom, 0F, 0F, 0F);
        rightleg = new RendererModel(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-4F, 7F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftlegbottom = new RendererModel(this, 0, 16);
        leftlegbottom.addBox(-3F, 8F, -3F, 6, 9, 6);
        leftlegbottom.setRotationPoint(4F, 7F, 0F);
        leftlegbottom.setTextureSize(64, 32);
        leftlegbottom.mirror = true;
        setRotation(leftlegbottom, 0F, 0F, 0F);
        leftleg = new RendererModel(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(4F, 7F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightarm = new RendererModel(this, 40, 16);
        rightarm.addBox(-5F, 2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-6F, -5F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new RendererModel(this, 40, 16);
        leftarm.addBox(0F, 2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(7F, -5F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        leftarmtop = new RendererModel(this, 36, 16);
        leftarmtop.addBox(-3F, -3F, -2F, 8, 5, 6);
        leftarmtop.setRotationPoint(7F, -5F, -1F);
        leftarmtop.setTextureSize(64, 32);
        leftarmtop.mirror = true;
        setRotation(leftarmtop, 0F, 0F, 0F);
    }

    @Override
    public void render(WorkshopMerchant entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        rightarmtop.render(scale);
        rightlegbottom.render(scale);
        leftarmtop.render(scale);
        leftlegbottom.render(scale);
    }

    @Override
    public void setRotationAngles(WorkshopMerchant entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);


        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
        this.rightarmtop.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarmtop.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarmtop.rotateAngleZ = 0.0F;
        this.leftarmtop.rotateAngleZ = 0.0F;
        this.rightlegbottom.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftlegbottom.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightlegbottom.rotateAngleY = 0.0F;
        this.leftlegbottom.rotateAngleY = 0.0F;
    }
}
