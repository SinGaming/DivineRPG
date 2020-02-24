package divinerpg.entities.eden.archer;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class SunArcherModel extends DivineModel<SunArcher> implements IHasArm {
    ModelRenderer legRight;
    ModelRenderer legLeft;
    ModelRenderer body;
    ModelRenderer footRight;
    ModelRenderer footLeft;
    ModelRenderer spine1;
    ModelRenderer spine2;
    ModelRenderer spine3;
    ModelRenderer spine4;
    ModelRenderer spine5;
    ModelRenderer head;
    ModelRenderer armRight;
    ModelRenderer armBandRight;
    ModelRenderer armLeft;
    ModelRenderer armBandLeft;

    public SunArcherModel() {
        textureWidth = 128;
        textureHeight = 64;

        legRight = new ModelRenderer(this, 0, 0);
        legRight.func_228300_a_(-1F, 0F, -1F, 2, 13, 2);
        legRight.setRotationPoint(-4F, 11F, 1F);
        legRight.setTextureSize(128, 64);
        legRight.mirror = true;
        setRotation(legRight, 0F, 0F, 0F);
        legLeft = new ModelRenderer(this, 0, 0);
        legLeft.func_228300_a_(-1F, 0F, -1F, 2, 13, 2);
        legLeft.setRotationPoint(4F, 11F, 1F);
        legLeft.setTextureSize(128, 64);
        legLeft.mirror = true;
        setRotation(legLeft, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 43);
        body.func_228300_a_(0F, 0F, 0F, 12, 16, 5);
        body.setRotationPoint(-6F, -5F, -1.5F);
        body.setTextureSize(128, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        footRight = new ModelRenderer(this, 0, 15);
        footRight.func_228300_a_(-1.5F, 12F, -2.5F, 3, 1, 4);
        footRight.setRotationPoint(-4F, 11F, 1F);
        footRight.setTextureSize(128, 64);
        footRight.mirror = true;
        setRotation(footRight, 0F, 0F, 0F);
        footLeft = new ModelRenderer(this, 0, 15);
        footLeft.func_228300_a_(-1.5F, 12F, -2.5F, 3, 1, 4);
        footLeft.setRotationPoint(4F, 11F, 1F);
        footLeft.setTextureSize(128, 64);
        footLeft.mirror = true;
        setRotation(footLeft, 0F, 0F, 0F);
        spine1 = new ModelRenderer(this, 8, 0);
        spine1.func_228300_a_(0F, -4F, 0F, 3, 4, 1);
        spine1.setRotationPoint(-1.5F, 0F, 2.5F);
        spine1.setTextureSize(128, 64);
        spine1.mirror = true;
        setRotation(spine1, -0.5235988F, 0F, 0F);
        spine2 = new ModelRenderer(this, 8, 0);
        spine2.func_228300_a_(0F, -4F, 0F, 3, 4, 1);
        spine2.setRotationPoint(-1.5F, 3F, 2.5F);
        spine2.setTextureSize(128, 64);
        spine2.mirror = true;
        setRotation(spine2, -0.5235988F, 0F, 0F);
        spine3 = new ModelRenderer(this, 8, 0);
        spine3.func_228300_a_(0F, -4F, 0F, 3, 4, 1);
        spine3.setRotationPoint(-1.5F, 6F, 2.5F);
        spine3.setTextureSize(128, 64);
        spine3.mirror = true;
        setRotation(spine3, -0.5235988F, 0F, 0F);
        spine4 = new ModelRenderer(this, 8, 0);
        spine4.func_228300_a_(0F, -4F, 0F, 3, 4, 1);
        spine4.setRotationPoint(-1.5F, 9F, 2.5F);
        spine4.setTextureSize(128, 64);
        spine4.mirror = true;
        setRotation(spine4, -0.5235988F, 0F, 0F);
        spine5 = new ModelRenderer(this, 8, 0);
        spine5.func_228300_a_(0F, -4F, 0F, 3, 4, 1);
        spine5.setRotationPoint(-1.5F, -3F, 2.5F);
        spine5.setTextureSize(128, 64);
        spine5.mirror = true;
        setRotation(spine5, -0.5235988F, 0F, 0F);
        head = new ModelRenderer(this, 0, 31);
        head.func_228300_a_(-3F, -6F, -3F, 6, 6, 6);
        head.setRotationPoint(0F, -5F, 0F);
        head.setTextureSize(128, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        armRight = new ModelRenderer(this, 16, 0);
        armRight.func_228300_a_(-1F, 0F, -1F, 2, 12, 2);
        armRight.setRotationPoint(-7F, -4F, 1F);
        armRight.setTextureSize(128, 64);
        armRight.mirror = true;
        setRotation(armRight, -1.308997F, 0F, 0F);
        armBandRight = new ModelRenderer(this, 24, 0);
        armBandRight.func_228300_a_(-1.5F, 3F, -1.5F, 3, 6, 3);
        armBandRight.setRotationPoint(-7F, -4F, 1F);
        armBandRight.setTextureSize(128, 64);
        armBandRight.mirror = true;
        setRotation(armBandRight, -1.308997F, 0F, 0F);
        armLeft = new ModelRenderer(this, 16, 0);
        armLeft.func_228300_a_(-1F, 0F, -1F, 2, 12, 2);
        armLeft.setRotationPoint(6.5F, -4F, 1F);
        armLeft.setTextureSize(128, 64);
        armLeft.mirror = true;
        setRotation(armLeft, 0F, 0F, -0.0872665F);
        armBandLeft = new ModelRenderer(this, 24, 0);
        armBandLeft.func_228300_a_(-1.5F, 3F, -1.5F, 3, 6, 3);
        armBandLeft.setRotationPoint(6.5F, -4F, 1F);
        armBandLeft.setTextureSize(128, 64);
        armBandLeft.mirror = true;
        setRotation(armBandLeft, 0F, 0F, -0.0872665F);
    }

    @Override
    public void setRotationAngles(SunArcher entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.legLeft.rotateAngleX = this.footLeft.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
        this.legRight.rotateAngleX = this.footRight.rotateAngleX = MathHelper.cos(limbSwing + (float) Math.PI) * limbSwingAmount;

        this.armRight.rotateAngleX = this.armBandRight.rotateAngleX = -1.308997f + MathHelper.cos(limbSwing) * limbSwingAmount * 0.3f;
        this.armLeft.rotateAngleX = this.armBandLeft.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
    }

    @Override
    public void func_225599_a_(HandSide side, MatrixStack stack) {
        if (side == HandSide.LEFT) {
            armLeft.func_228307_a_(stack);
        } else {
            armRight.func_228307_a_(stack);
        }
    }
}
