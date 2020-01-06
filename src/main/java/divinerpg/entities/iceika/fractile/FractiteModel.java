package divinerpg.entities.iceika.fractile;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class FractiteModel extends DivineModel<Fractite> {
    RendererModel frostBody;
    RendererModel shard1;
    RendererModel shard2;
    RendererModel shard7;
    RendererModel shard8;
    RendererModel shard4;
    RendererModel shard3;
    RendererModel shard5;
    RendererModel shard6;

    public FractiteModel() {
        textureWidth = 64;
        textureHeight = 32;

        frostBody = new RendererModel(this, 0, 11);
        frostBody.addBox(0F, 0F, 0F, 8, 8, 6);
        frostBody.setRotationPoint(-4F, 5F, -4F);
        frostBody.setTextureSize(64, 32);
        frostBody.mirror = true;
        setRotation(frostBody, 0F, 0F, 0F);
        shard1 = new RendererModel(this, 0, 0);
        shard1.addBox(8F, 0F, 0F, 9, 1, 2);
        shard1.setRotationPoint(1F, 8F, -2F);
        shard1.setTextureSize(64, 32);
        shard1.mirror = true;
        setRotation(shard1, 0F, 0F, -0.7853982F);
        shard2 = new RendererModel(this, 0, 0);
        shard2.addBox(-19F, 0F, 0F, 9, 1, 2);
        shard2.setRotationPoint(0F, 9F, -2F);
        shard2.setTextureSize(64, 32);
        shard2.mirror = true;
        setRotation(shard2, 0F, 0F, -0.7853982F);
        shard7 = new RendererModel(this, 0, 0);
        shard7.addBox(10F, 0F, 0F, 10, 1, 2);
        shard7.setRotationPoint(0F, 9F, -2F);
        shard7.setTextureSize(64, 32);
        shard7.mirror = true;
        setRotation(shard7, 0F, 0F, 0.7853982F);
        shard8 = new RendererModel(this, 0, 0);
        shard8.addBox(-19F, 0F, 0F, 10, 1, 2);
        shard8.setRotationPoint(-1F, 9F, -2F);
        shard8.setTextureSize(64, 32);
        shard8.mirror = true;
        setRotation(shard8, 0F, 0F, 0.7853982F);
        shard4 = new RendererModel(this, 0, 0);
        shard4.addBox(-6F, -8F, 0F, 15, 1, 2);
        shard4.setRotationPoint(0F, 8F, -2F);
        shard4.setTextureSize(64, 32);
        shard4.mirror = true;
        setRotation(shard4, 0F, 0F, 1.570796F);
        shard3 = new RendererModel(this, 0, 0);
        shard3.addBox(-7F, 7F, 0F, 15, 1, 2);
        shard3.setRotationPoint(0F, 9F, -2F);
        shard3.setTextureSize(64, 32);
        shard3.mirror = true;
        setRotation(shard3, 0F, 0F, 1.570796F);
        shard5 = new RendererModel(this, 0, 0);
        shard5.addBox(-8F, -7F, 0F, 14, 1, 2);
        shard5.setRotationPoint(1F, 9F, -2F);
        shard5.setTextureSize(64, 32);
        shard5.mirror = true;
        setRotation(shard5, 0F, 0F, 0F);
        shard6 = new RendererModel(this, 0, 0);
        shard6.addBox(-7F, 7F, 0F, 14, 1, 2);
        shard6.setRotationPoint(0F, 9F, -2F);
        shard6.setTextureSize(64, 32);
        shard6.mirror = true;
        setRotation(shard6, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Fractite entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.shard1.rotateAngleX =
                this.shard2.rotateAngleX =
                        this.shard3.rotateAngleX =
                                this.shard4.rotateAngleX =
                                        this.shard5.rotateAngleX =
                                                this.shard6.rotateAngleX =
                                                        this.shard7.rotateAngleX =
                                                                this.shard8.rotateAngleX = MathHelper.cos(ageInTicks * 0.1F) * (float) Math.PI;
    }
}
