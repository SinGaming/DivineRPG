package divinerpg.entities.vanilla.spider.pumpkin;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import divinerpg.entities.base.render.DeobfHelper;
import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PumpkinSpiderModel extends DivineModel<PumpkinSpider> {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RearEnd;
    private final ModelRenderer Leg8;
    private final ModelRenderer Leg6;
    private final ModelRenderer Leg4;
    private final ModelRenderer Leg2;
    private final ModelRenderer Leg7;
    private final ModelRenderer Leg5;
    private final ModelRenderer Leg3;
    private final ModelRenderer Leg1;

    public PumpkinSpiderModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelRenderer(this, 32, 4);
        Head.func_228300_a_(-4F, -4F, -8F, 8, 8, 8);
        Head.setRotationPoint(0F, 20F, -3F);
        Head.setTextureSize(64, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 0, 0);
        Body.func_228300_a_(-3F, -3F, -3F, 6, 6, 6);
        Body.setRotationPoint(0F, 20F, 0F);
        Body.setTextureSize(64, 64);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        RearEnd = new ModelRenderer(this, 0, 20);
        RearEnd.func_228300_a_(-5F, -4F, -6F, 16, 16, 16);
        RearEnd.setRotationPoint(-3F, 12F, 8F);
        RearEnd.setTextureSize(64, 64);
        RearEnd.mirror = true;
        setRotation(RearEnd, 0F, 0F, 0F);
        Leg8 = new ModelRenderer(this, 18, 0);
        Leg8.func_228300_a_(-1F, -1F, -1F, 16, 2, 2);
        Leg8.setRotationPoint(4F, 20F, -1F);
        Leg8.setTextureSize(64, 64);
        Leg8.mirror = true;
        setRotation(Leg8, 0F, 0.5759587F, 0.1919862F);
        Leg6 = new ModelRenderer(this, 18, 0);
        Leg6.func_228300_a_(-1F, -1F, -1F, 16, 2, 2);
        Leg6.setRotationPoint(4F, 20F, 0F);
        Leg6.setTextureSize(64, 64);
        Leg6.mirror = true;
        setRotation(Leg6, 0F, 0.2792527F, 0.1919862F);
        Leg4 = new ModelRenderer(this, 18, 0);
        Leg4.func_228300_a_(-1F, -1F, -1F, 16, 2, 2);
        Leg4.setRotationPoint(4F, 20F, 1F);
        Leg4.setTextureSize(64, 64);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, -0.2792527F, 0.1919862F);
        Leg2 = new ModelRenderer(this, 18, 0);
        Leg2.func_228300_a_(-1F, -1F, -1F, 16, 2, 2);
        Leg2.setRotationPoint(4F, 20F, 2F);
        Leg2.setTextureSize(64, 64);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, -0.5759587F, 0.1919862F);
        Leg7 = new ModelRenderer(this, 18, 0);
        Leg7.func_228300_a_(-15F, -1F, -1F, 16, 2, 2);
        Leg7.setRotationPoint(-4F, 20F, -1F);
        Leg7.setTextureSize(64, 64);
        Leg7.mirror = true;
        setRotation(Leg7, 0F, -0.5759587F, -0.1919862F);
        Leg5 = new ModelRenderer(this, 18, 0);
        Leg5.func_228300_a_(-15F, -1F, -1F, 16, 2, 2);
        Leg5.setRotationPoint(-4F, 20F, 0F);
        Leg5.setTextureSize(64, 64);
        Leg5.mirror = true;
        setRotation(Leg5, 0F, -0.2792527F, -0.1919862F);
        Leg3 = new ModelRenderer(this, 18, 0);
        Leg3.func_228300_a_(-15F, -1F, -1F, 16, 2, 2);
        Leg3.setRotationPoint(-4F, 20F, 1F);
        Leg3.setTextureSize(64, 64);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0.2792527F, -0.1919862F);
        Leg1 = new ModelRenderer(this, 18, 0);
        Leg1.func_228300_a_(-15F, -1F, -1F, 16, 2, 2);
        Leg1.setRotationPoint(-4F, 20F, 2F);
        Leg1.setTextureSize(64, 64);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0.5759587F, -0.1919862F);
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        DeobfHelper.useMatirx(stack, () -> {
            if (aggressive) {
                DeobfHelper.translate(stack, 0, -0.1875f, 0);
            }
            super.render(stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public Iterable<ModelRenderer> getBodyParts() {
        return aggressive
                ? ImmutableSet.of(Head, Body, Leg8, Leg6, Leg4, Leg2, Leg7, Leg5, Leg3, Leg1)
                : ImmutableSet.of(RearEnd);
    }

    @Override
    public void setRotationAngles(PumpkinSpider entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float var8 = ((float) Math.PI / 4F);
        this.Leg1.rotateAngleZ = -var8;
        this.Leg2.rotateAngleZ = var8;
        this.Leg3.rotateAngleZ = -var8 * 0.74F;
        this.Leg4.rotateAngleZ = var8 * 0.74F;
        this.Leg5.rotateAngleZ = -var8 * 0.74F;
        this.Leg6.rotateAngleZ = var8 * 0.74F;
        this.Leg7.rotateAngleZ = -var8;
        this.Leg8.rotateAngleZ = var8;
        float var9 = -0.0F;
        float var10 = 0.3926991F;
        this.Leg1.rotateAngleY = var10 * 2.0F + var9;
        this.Leg2.rotateAngleY = -var10 * 2.0F - var9;
        this.Leg3.rotateAngleY = var10 * 1.0F + var9;
        this.Leg4.rotateAngleY = -var10 * 1.0F - var9;
        this.Leg5.rotateAngleY = -var10 * 1.0F + var9;
        this.Leg6.rotateAngleY = var10 * 1.0F - var9;
        this.Leg7.rotateAngleY = -var10 * 2.0F + var9;
        this.Leg8.rotateAngleY = var10 * 2.0F - var9;
        float var11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float var12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float var13 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float var14 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float var15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float var16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float var17 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float var18 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.Leg1.rotateAngleY += var11;
        this.Leg2.rotateAngleY += -var11;
        this.Leg3.rotateAngleY += var12;
        this.Leg4.rotateAngleY += -var12;
        this.Leg5.rotateAngleY += var13;
        this.Leg6.rotateAngleY += -var13;
        this.Leg7.rotateAngleY += var14;
        this.Leg8.rotateAngleY += -var14;
        this.Leg1.rotateAngleZ += var15;
        this.Leg2.rotateAngleZ += -var15;
        this.Leg3.rotateAngleZ += var16;
        this.Leg4.rotateAngleZ += -var16;
        this.Leg5.rotateAngleZ += var17;
        this.Leg6.rotateAngleZ += -var17;
        this.Leg7.rotateAngleZ += var18;
        this.Leg8.rotateAngleZ += -var18;
    }
}
