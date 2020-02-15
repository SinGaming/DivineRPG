package divinerpg.entities.iceika.fractile;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class FractiteModel extends DivineModel<Fractite> {
    ModelRenderer frostBody;
    ModelRenderer shard1;
    ModelRenderer shard2;
    ModelRenderer shard7;
    ModelRenderer shard8;
    ModelRenderer shard4;
    ModelRenderer shard3;
    ModelRenderer shard5;
    ModelRenderer shard6;

    public FractiteModel() {
        textureWidth = 64;
        textureHeight = 32;

        frostBody = new ModelRenderer(this, 0, 11);
        frostBody.func_228300_a_(0F, 0F, 0F, 8, 8, 6);
        frostBody.setRotationPoint(-4F, 5F, -4F);
        frostBody.setTextureSize(64, 32);
        frostBody.mirror = true;
        setRotation(frostBody, 0F, 0F, 0F);
        shard1 = new ModelRenderer(this, 0, 0);
        shard1.func_228300_a_(8F, 0F, 0F, 9, 1, 2);
        shard1.setRotationPoint(1F, 8F, -2F);
        shard1.setTextureSize(64, 32);
        shard1.mirror = true;
        setRotation(shard1, 0F, 0F, -0.7853982F);
        shard2 = new ModelRenderer(this, 0, 0);
        shard2.func_228300_a_(-19F, 0F, 0F, 9, 1, 2);
        shard2.setRotationPoint(0F, 9F, -2F);
        shard2.setTextureSize(64, 32);
        shard2.mirror = true;
        setRotation(shard2, 0F, 0F, -0.7853982F);
        shard7 = new ModelRenderer(this, 0, 0);
        shard7.func_228300_a_(10F, 0F, 0F, 10, 1, 2);
        shard7.setRotationPoint(0F, 9F, -2F);
        shard7.setTextureSize(64, 32);
        shard7.mirror = true;
        setRotation(shard7, 0F, 0F, 0.7853982F);
        shard8 = new ModelRenderer(this, 0, 0);
        shard8.func_228300_a_(-19F, 0F, 0F, 10, 1, 2);
        shard8.setRotationPoint(-1F, 9F, -2F);
        shard8.setTextureSize(64, 32);
        shard8.mirror = true;
        setRotation(shard8, 0F, 0F, 0.7853982F);
        shard4 = new ModelRenderer(this, 0, 0);
        shard4.func_228300_a_(-6F, -8F, 0F, 15, 1, 2);
        shard4.setRotationPoint(0F, 8F, -2F);
        shard4.setTextureSize(64, 32);
        shard4.mirror = true;
        setRotation(shard4, 0F, 0F, 1.570796F);
        shard3 = new ModelRenderer(this, 0, 0);
        shard3.func_228300_a_(-7F, 7F, 0F, 15, 1, 2);
        shard3.setRotationPoint(0F, 9F, -2F);
        shard3.setTextureSize(64, 32);
        shard3.mirror = true;
        setRotation(shard3, 0F, 0F, 1.570796F);
        shard5 = new ModelRenderer(this, 0, 0);
        shard5.func_228300_a_(-8F, -7F, 0F, 14, 1, 2);
        shard5.setRotationPoint(1F, 9F, -2F);
        shard5.setTextureSize(64, 32);
        shard5.mirror = true;
        setRotation(shard5, 0F, 0F, 0F);
        shard6 = new ModelRenderer(this, 0, 0);
        shard6.func_228300_a_(-7F, 7F, 0F, 14, 1, 2);
        shard6.setRotationPoint(0F, 9F, -2F);
        shard6.setTextureSize(64, 32);
        shard6.mirror = true;
        setRotation(shard6, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Fractite entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

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
