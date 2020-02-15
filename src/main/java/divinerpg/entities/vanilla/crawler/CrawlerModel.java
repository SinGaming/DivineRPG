package divinerpg.entities.vanilla.crawler;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class CrawlerModel<T extends Entity> extends DivineModel<T> {
    ModelRenderer earL;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;
    ModelRenderer head;
    ModelRenderer earR;
    ModelRenderer earL2;
    ModelRenderer earR2;
    
    public CrawlerModel(){
        earL = new ModelRenderer(this, 36, 2);
        earL.func_228300_a_(5F, 1F, -8F, 1, 2, 5);
        earL.setRotationPoint(-1F, 6F, -8F);
        earL.setTextureSize(64, 32);
        earL.mirror = true;
        setRotation(earL, 0F, 0F, 0F);
        rightfoot = new ModelRenderer(this, 28, 16);
        rightfoot.func_228300_a_(-4F, 7F, -4F, 6, 4, 6);
        rightfoot.setRotationPoint(-3F, 13F, -5F);
        rightfoot.setTextureSize(64, 32);
        rightfoot.mirror = true;
        setRotation(rightfoot, 0F, 0F, 0F);
        leftfoot = new ModelRenderer(this, 28, 16);
        leftfoot.func_228300_a_(-2F, 7F, -4F, 6, 4, 6);
        leftfoot.setRotationPoint(3F, 13F, -5F);
        leftfoot.setTextureSize(64, 32);
        leftfoot.mirror = true;
        setRotation(leftfoot, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.func_228300_a_(-1F, 0F, -3F, 4, 7, 4);
        leftleg.setRotationPoint(3F, 13F, -5F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.func_228300_a_(-3F, 0F, -3F, 4, 7, 4);
        rightleg.setRotationPoint(-3F, 13F, -5F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        body = new ModelRenderer(this, 19, 16);
        body.func_228300_a_(-7F, -6F, -5F, 7, 5, 10);
        body.setRotationPoint(7F, 15F, -6F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, -0.4363323F, 0F, 0F);
        body2 = new ModelRenderer(this, 19, 0);
        body2.func_228300_a_(-7F, -6F, -5F, 7, 5, 10);
        body2.setRotationPoint(0F, 15F, -6F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, -0.4363323F, 0F, 0F);
        body3 = new ModelRenderer(this, 24, 19);
        body3.func_228300_a_(-7F, -6F, -5F, 7, 5, 2);
        body3.setRotationPoint(0F, 27F, 2F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, -1.570796F, 0F, 0F);
        body4 = new ModelRenderer(this, 19, 19);
        body4.func_228300_a_(-7F, -6F, -5F, 7, 5, 2);
        body4.setRotationPoint(7F, 27F, 2F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, -1.570796F, 0F, 0F);
        body5 = new ModelRenderer(this, 24, 19);
        body5.func_228300_a_(-7F, -6F, -5F, 6, 5, 4);
        body5.setRotationPoint(1F, 24F, 3F);
        body5.setTextureSize(64, 32);
        body5.mirror = true;
        setRotation(body5, -1.308997F, 0F, 0F);
        body6 = new ModelRenderer(this, 19, 19);
        body6.func_228300_a_(-7F, -6F, -5F, 6, 5, 4);
        body6.setRotationPoint(7F, 24F, 3F);
        body6.setTextureSize(64, 32);
        body6.mirror = true;
        setRotation(body6, -1.308997F, 0F, 0F);
        body7 = new ModelRenderer(this, 24, 19);
        body7.func_228300_a_(-7F, -6F, -5F, 7, 5, 7);
        body7.setRotationPoint(0F, 20F, 1F);
        body7.setTextureSize(64, 32);
        body7.mirror = true;
        setRotation(body7, -0.7853982F, 0F, 0F);
        body8 = new ModelRenderer(this, 19, 19);
        body8.func_228300_a_(-7F, -6F, -5F, 7, 5, 7);
        body8.setRotationPoint(7F, 20F, 1F);
        body8.setTextureSize(64, 32);
        body8.mirror = true;
        setRotation(body8, -0.7853982F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.func_228300_a_(-4F, -4F, -6F, 8, 8, 6);
        head.setRotationPoint(0F, 6F, -8F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        earR = new ModelRenderer(this, 36, 2);
        earR.func_228300_a_(-4F, 1F, -8F, 1, 2, 5);
        earR.setRotationPoint(-1F, 6F, -8F);
        earR.setTextureSize(64, 32);
        earR.mirror = true;
        setRotation(earR, 0F, 0F, 0F);
        earL2 = new ModelRenderer(this, 36, 2);
        earL2.func_228300_a_(5F, -7F, -3F, 1, 5, 2);
        earL2.setRotationPoint(-1F, 6F, -8F);
        earL2.setTextureSize(64, 32);
        earL2.mirror = true;
        setRotation(earL2, 0F, 0F, 0F);
        earR2 = new ModelRenderer(this, 36, 2);
        earR2.func_228300_a_(-4F, -7F, -3F, 1, 5, 2);
        earR2.setRotationPoint(-1F, 6F, -8F);
        earR2.setTextureSize(64, 32);
        earR2.mirror = true;
        setRotation(earR2, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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
