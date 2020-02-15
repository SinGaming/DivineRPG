package divinerpg.entities.vanilla.crab;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class CrabModel<T extends Entity> extends DivineModel<T> {
    private final ModelRenderer RearEnd;
    private final ModelRenderer Leg8;
    private final ModelRenderer Leg6;
    private final ModelRenderer Leg4;
    private final ModelRenderer Leg7;
    private final ModelRenderer Leg5;
    private final ModelRenderer Leg3;

    public CrabModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.RearEnd = new ModelRenderer(this, 0, 12);
        this.RearEnd.addBox(-5.0F, -4.0F, -6.0F, 12, 8, 12);
        this.RearEnd.setRotationPoint(-1.0F, 16.0F, 0.0F);
        this.RearEnd.setTextureSize(64, 32);
        this.RearEnd.mirror = true;
        this.setRotation(this.RearEnd, 0.0F, 0.0F, 0.0F);
        this.Leg8 = new ModelRenderer(this, 18, 0);
        this.Leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 4, 4);
        this.Leg8.setRotationPoint(4.0F, 16.0F, -4.0F);
        this.Leg8.setTextureSize(64, 32);
        this.Leg8.mirror = true;
        this.setRotation(this.Leg8, 0.0F, 0.5759587F, -0.1396263F);
        this.Leg6 = new ModelRenderer(this, 18, 0);
        this.Leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
        this.Leg6.setRotationPoint(4.0F, 16.0F, 0.0F);
        this.Leg6.setTextureSize(64, 32);
        this.Leg6.mirror = true;
        this.setRotation(this.Leg6, 0.0F, 0.2792527F, 0.1919862F);
        this.Leg4 = new ModelRenderer(this, 18, 0);
        this.Leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
        this.Leg4.setRotationPoint(4.0F, 16.0F, 1.0F);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0F, -0.2792527F, 0.1919862F);
        this.Leg7 = new ModelRenderer(this, 18, 0);
        this.Leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 4, 4);
        this.Leg7.setRotationPoint(-4.0F, 16.0F, -4.0F);
        this.Leg7.setTextureSize(64, 32);
        this.Leg7.mirror = true;
        this.setRotation(this.Leg7, 0.0F, -0.5759587F, 0.1396263F);
        this.Leg5 = new ModelRenderer(this, 18, 0);
        this.Leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        this.Leg5.setRotationPoint(-4.0F, 16.0F, 0.0F);
        this.Leg5.setTextureSize(64, 32);
        this.Leg5.mirror = true;
        this.setRotation(this.Leg5, 0.0F, -0.2792527F, -0.1919862F);
        this.Leg3 = new ModelRenderer(this, 18, 0);
        this.Leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        this.Leg3.setRotationPoint(-4.0F, 16.0F, 1.0F);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0F, 0.2792527F, -0.1919862F);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float var7 = ((float) Math.PI / 4F);
        this.Leg3.rotateAngleZ = -var7 * 0.74F;
        this.Leg4.rotateAngleZ = var7 * 0.74F;
        this.Leg5.rotateAngleZ = -var7 * 0.74F;
        this.Leg6.rotateAngleZ = var7 * 0.74F;
        this.Leg7.rotateAngleZ = -var7;
        this.Leg8.rotateAngleZ = var7;
        float var8 = -0.0F;
        float var9 = 0.3926991F;
        this.Leg3.rotateAngleY = var9 * 1.0F + var8;
        this.Leg4.rotateAngleY = -var9 * 1.0F - var8;
        this.Leg5.rotateAngleY = -var9 * 1.0F + var8;
        this.Leg6.rotateAngleY = var9 * 1.0F - var8;
        this.Leg7.rotateAngleY = -var9 * 2.0F + var8;
        this.Leg8.rotateAngleY = var9 * 2.0F - var8;
        float var11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float var12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float var13 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float var15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float var16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float var17 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.Leg3.rotateAngleY += var11;
        this.Leg4.rotateAngleY += -var11;
        this.Leg5.rotateAngleY += var12;
        this.Leg6.rotateAngleY += -var12;
        this.Leg7.rotateAngleY += var13;
        this.Leg8.rotateAngleY += -var13;
        this.Leg3.rotateAngleZ += var15;
        this.Leg4.rotateAngleZ += -var15;
        this.Leg5.rotateAngleZ += var16;
        this.Leg6.rotateAngleZ += -var16;
        this.Leg7.rotateAngleZ += var17;
        this.Leg8.rotateAngleZ += -var17;
    }
}
