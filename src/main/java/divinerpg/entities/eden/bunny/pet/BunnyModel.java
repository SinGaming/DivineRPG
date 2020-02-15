package divinerpg.entities.eden.bunny.pet;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BunnyModel extends DivineModel<Bunny> {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer tail;

    // TODO wrong manager floating part on the top
    public BunnyModel() {
        textureWidth = 64;
        textureHeight = 32;

        Head = new ModelRenderer(this, 0, 0);
        Head.func_228300_a_(-3F, -3F, -2F, 6, 6, 4);
        Head.setRotationPoint(-1F, 16.5F, -7F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 18, 14);
        Body.func_228300_a_(-4F, -2F, -3F, 6, 9, 6);
        Body.setRotationPoint(0F, 17F, -3F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 1.570796F, 0F, 0F);
        Leg1 = new ModelRenderer(this, 0, 18);
        Leg1.func_228300_a_(-1F, 0F, -1F, 2, 5, 2);
        Leg1.setRotationPoint(-2.5F, 19F, 3F);
        Leg1.setTextureSize(64, 32);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 0, 18);
        Leg2.func_228300_a_(-1F, 0F, -1F, 2, 5, 2);
        Leg2.setRotationPoint(0.5F, 19F, 3F);
        Leg2.setTextureSize(64, 32);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Leg3 = new ModelRenderer(this, 0, 18);
        Leg3.func_228300_a_(-1F, 0F, -1F, 2, 5, 2);
        Leg3.setRotationPoint(-2.5F, 19F, -4F);
        Leg3.setTextureSize(64, 32);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0F, 0F);
        Leg4 = new ModelRenderer(this, 0, 18);
        Leg4.func_228300_a_(-1F, 0F, -1F, 2, 5, 2);
        Leg4.setRotationPoint(0.5F, 19F, -4F);
        Leg4.setTextureSize(64, 32);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, 0F, 0F);
        Ear1 = new ModelRenderer(this, 16, 14);
        Ear1.func_228300_a_(-3F, -5F, 0F, 2, 2, 1);
        Ear1.setRotationPoint(-1F, 16.5F, -7F);
        Ear1.setTextureSize(64, 32);
        Ear1.mirror = true;
        setRotation(Ear1, 0F, 0F, 0F);
        Ear2 = new ModelRenderer(this, 16, 14);
        Ear2.func_228300_a_(1F, -5F, 0F, 2, 2, 1);
        Ear2.setRotationPoint(-1F, 16.5F, -7F);
        Ear2.setTextureSize(64, 32);
        Ear2.mirror = true;
        setRotation(Ear2, 0F, 0F, 0F);
        tail = new ModelRenderer(this, 6, 0);
        tail.func_228300_a_(0F, -5F, 8F, 2, 2, 1);
        tail.setRotationPoint(-2F, 16F, 4F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(tail, 0F, 0F, 0F);
        Body.addChild(tail);
    }

    @Override
    public void setRotationAngles(Bunny entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if (entityIn.isSitting()) {
            Body.rotateAngleX = 0.95993f;
            Leg1.rotateAngleX = Leg2.rotateAngleX = -1.5708f;
            Leg3.rotateAngleX = Leg4.rotateAngleX = -0.2818f;
            Leg1.rotationPointY = Leg2.rotationPointY = 23;
            Leg1.rotationPointZ = Leg2.rotationPointZ = 2;
        } else {
            Leg1.rotationPointY = Leg2.rotationPointY = 19;
            Leg1.rotationPointZ = Leg2.rotationPointZ = 3;
            this.Body.rotateAngleX = ((float) Math.PI / 2F);
            this.Leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.Leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.Leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.Leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
        this.Head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.Head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.Ear1.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.Ear1.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.Ear2.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.Ear2.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.tail.rotateAngleX = ((float) Math.PI / 2F);
    }
}
