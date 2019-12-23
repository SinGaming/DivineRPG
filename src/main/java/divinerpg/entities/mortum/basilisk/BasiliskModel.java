package divinerpg.entities.mortum.basilisk;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class BasiliskModel extends DivineModel<Basilisk> {
    RendererModel head;
    RendererModel body;
    RendererModel leg1;
    RendererModel leg2;
    RendererModel leg3;
    RendererModel leg4;
    RendererModel leg5;
    RendererModel leg6;
    RendererModel Spine_1;
    RendererModel Spine_2;
    RendererModel Spine_3;
    RendererModel Spine_4;
    RendererModel Spine_5;

    public BasiliskModel()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-5.0F, -3.0F, -6.0F, 10, 6, 7);
        this.head.setRotationPoint(0.0F, 15.0F, -8.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new RendererModel(this, 32, 10);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 6);
        this.body.setRotationPoint(0.0F, 11.0F, 3.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, ((float)Math.PI / 2F), 0.0F, 0.0F);
        this.leg1 = new RendererModel(this, 0, 16);
        this.leg1.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg1.setRotationPoint(-3.0F, 19.0F, -5.0F);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        this.leg2 = new RendererModel(this, 0, 16);
        this.leg2.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg2.setRotationPoint(3.0F, 19.0F, -5.0F);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        this.leg3 = new RendererModel(this, 0, 16);
        this.leg3.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg3.setRotationPoint(-3.0F, 19.0F, 1.0F);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new RendererModel(this, 0, 16);
        this.leg4.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg4.setRotationPoint(3.0F, 19.0F, 1.0F);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        this.leg5 = new RendererModel(this, 0, 16);
        this.leg5.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg5.setRotationPoint(-3.0F, 19.0F, 7.0F);
        this.leg5.setTextureSize(64, 32);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, 0.0F, 0.0F, 0.0F);
        this.leg6 = new RendererModel(this, 0, 16);
        this.leg6.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4);
        this.leg6.setRotationPoint(3.0F, 19.0F, 7.0F);
        this.leg6.setTextureSize(64, 32);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, 0.0F, 0.0F, 0.0F);
        this.Spine_1 = new RendererModel(this, 56, 0);
        this.Spine_1.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Spine_1.setRotationPoint(0.0F, 11.0F, -5.0F);
        this.Spine_1.setTextureSize(64, 32);
        this.Spine_1.mirror = true;
        this.setRotation(this.Spine_1, 0.0F, 0.0F, 0.0F);
        this.Spine_2 = new RendererModel(this, 56, 0);
        this.Spine_2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Spine_2.setRotationPoint(0.0F, 11.0F, -2.0F);
        this.Spine_2.setTextureSize(64, 32);
        this.Spine_2.mirror = true;
        this.setRotation(this.Spine_2, 0.0F, 0.0F, 0.0F);
        this.Spine_3 = new RendererModel(this, 56, 0);
        this.Spine_3.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Spine_3.setRotationPoint(0.0F, 11.0F, 1.0F);
        this.Spine_3.setTextureSize(64, 32);
        this.Spine_3.mirror = true;
        this.setRotation(this.Spine_3, 0.0F, 0.0F, 0.0F);
        this.Spine_4 = new RendererModel(this, 56, 0);
        this.Spine_4.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Spine_4.setRotationPoint(0.0F, 11.0F, 4.0F);
        this.Spine_4.setTextureSize(64, 32);
        this.Spine_4.mirror = true;
        this.setRotation(this.Spine_4, 0.0F, 0.0F, 0.0F);
        this.Spine_5 = new RendererModel(this, 56, 0);
        this.Spine_5.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Spine_5.setRotationPoint(0.0F, 11.0F, 7.0F);
        this.Spine_5.setTextureSize(64, 32);
        this.Spine_5.mirror = true;
        this.setRotation(this.Spine_5, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(Basilisk entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg6.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }
}
