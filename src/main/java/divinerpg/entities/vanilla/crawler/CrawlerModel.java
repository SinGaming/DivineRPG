package divinerpg.entities.vanilla.crawler;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class CrawlerModel<T extends Entity> extends DivineModel<T> {
    RendererModel earL;
    RendererModel rightfoot;
    RendererModel leftfoot;
    RendererModel leftleg;
    RendererModel rightleg;
    RendererModel body;
    RendererModel body2;
    RendererModel body3;
    RendererModel body4;
    RendererModel body5;
    RendererModel body6;
    RendererModel body7;
    RendererModel body8;
    RendererModel head;
    RendererModel earR;
    RendererModel earL2;
    RendererModel earR2;
    
    public CrawlerModel(){
        earL = new RendererModel(this, 36, 2);
        earL.addBox(5F, 1F, -8F, 1, 2, 5);
        earL.setRotationPoint(-1F, 6F, -8F);
        earL.setTextureSize(64, 32);
        earL.mirror = true;
        setRotation(earL, 0F, 0F, 0F);
        rightfoot = new RendererModel(this, 28, 16);
        rightfoot.addBox(-4F, 7F, -4F, 6, 4, 6);
        rightfoot.setRotationPoint(-3F, 13F, -5F);
        rightfoot.setTextureSize(64, 32);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
        leftfoot = new RendererModel(this, 28, 16);
        leftfoot.addBox(-2F, 7F, -4F, 6, 4, 6);
        leftfoot.setRotationPoint(3F, 13F, -5F);
        leftfoot.setTextureSize(64, 32);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
        leftleg = new RendererModel(this, 0, 16);
        leftleg.addBox(-1F, 0F, -3F, 4, 7, 4);
        leftleg.setRotationPoint(3F, 13F, -5F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightleg = new RendererModel(this, 0, 16);
        rightleg.addBox(-3F, 0F, -3F, 4, 7, 4);
        rightleg.setRotationPoint(-3F, 13F, -5F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        body = new RendererModel(this, 19, 16);
        body.addBox(-7F, -6F, -5F, 7, 5, 10);
        body.setRotationPoint(7F, 15F, -6F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, -0.4363323F, 0F, 0F);
        body2 = new RendererModel(this, 19, 0);
        body2.addBox(-7F, -6F, -5F, 7, 5, 10);
        body2.setRotationPoint(0F, 15F, -6F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, -0.4363323F, 0F, 0F);
        body3 = new RendererModel(this, 24, 19);
        body3.addBox(-7F, -6F, -5F, 7, 5, 2);
        body3.setRotationPoint(0F, 27F, 2F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, -1.570796F, 0F, 0F);
        body4 = new RendererModel(this, 19, 19);
        body4.addBox(-7F, -6F, -5F, 7, 5, 2);
        body4.setRotationPoint(7F, 27F, 2F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, -1.570796F, 0F, 0F);
        body5 = new RendererModel(this, 24, 19);
        body5.addBox(-7F, -6F, -5F, 6, 5, 4);
        body5.setRotationPoint(1F, 24F, 3F);
        body5.setTextureSize(64, 32);
        body5.mirror = true;
        setRotation(body5, -1.308997F, 0F, 0F);
        body6 = new RendererModel(this, 19, 19);
        body6.addBox(-7F, -6F, -5F, 6, 5, 4);
        body6.setRotationPoint(7F, 24F, 3F);
        body6.setTextureSize(64, 32);
        body6.mirror = true;
        setRotation(body6, -1.308997F, 0F, 0F);
        body7 = new RendererModel(this, 24, 19);
        body7.addBox(-7F, -6F, -5F, 7, 5, 7);
        body7.setRotationPoint(0F, 20F, 1F);
        body7.setTextureSize(64, 32);
        body7.mirror = true;
        setRotation(body7, -0.7853982F, 0F, 0F);
        body8 = new RendererModel(this, 19, 19);
        body8.addBox(-7F, -6F, -5F, 7, 5, 7);
        body8.setRotationPoint(7F, 20F, 1F);
        body8.setTextureSize(64, 32);
        body8.mirror = true;
        setRotation(body8, -0.7853982F, 0F, 0F);
        head = new RendererModel(this, 0, 0);
        head.addBox(-4F, -4F, -6F, 8, 8, 6);
        head.setRotationPoint(0F, 6F, -8F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        earR = new RendererModel(this, 36, 2);
        earR.addBox(-4F, 1F, -8F, 1, 2, 5);
        earR.setRotationPoint(-1F, 6F, -8F);
        earR.setTextureSize(64, 32);
        earR.mirror = true;
        setRotation(earR, 0F, 0F, 0F);
        earL2 = new RendererModel(this, 36, 2);
        earL2.addBox(5F, -7F, -3F, 1, 5, 2);
        earL2.setRotationPoint(-1F, 6F, -8F);
        earL2.setTextureSize(64, 32);
        earL2.mirror = true;
        setRotation(earL2, 0F, 0F, 0F);
        earR2 = new RendererModel(this, 36, 2);
        earR2.addBox(-4F, -7F, -3F, 1, 5, 2);
        earR2.setRotationPoint(-1F, 6F, -8F);
        earR2.setTextureSize(64, 32);
        earR2.mirror = true;
        setRotation(earR2, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.earL.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.earL.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.earR.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.earR.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.earL2.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.earL2.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.earR2.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.earR2.rotateAngleX = headPitch / (180F / (float)Math.PI);

        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightfoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.leftleg.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;
        this.leftfoot.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.6662F + Math.PI)) * 1.4F * limbSwingAmount;

        this.rightleg.rotateAngleY = 0.0F;
        this.rightfoot.rotateAngleY = 0.0F;

        this.leftleg.rotateAngleY = 0.0F;
        this.leftfoot.rotateAngleY = 0.0F;
    }
}
