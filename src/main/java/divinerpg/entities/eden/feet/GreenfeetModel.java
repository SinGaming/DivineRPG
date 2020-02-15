package divinerpg.entities.eden.feet;

import divinerpg.entities.base.render.DivineBipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GreenfeetModel extends DivineBipedModel<Greenfeet> {
    ModelRenderer leftlegext;
    ModelRenderer rightlegext;
    ModelRenderer leftarmbox;
    ModelRenderer rightarmbox;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public GreenfeetModel() {
        textureWidth = 64;
        textureHeight = 32;

        leftarmbox = new ModelRenderer(this, 32, 0);
        leftarmbox.func_228300_a_(-2F, 10F, -3F, 6, 4, 6);
        leftarmbox.setRotationPoint(8F, 2F, 3F);
        leftarmbox.setTextureSize(64, 32);
        leftarmbox.mirror = true;
        setRotation(leftarmbox, 0F, 0F, 0F);
        rightlegext = new ModelRenderer(this, 0, 16);
        rightlegext.func_228300_a_(-2F, 0F, -6F, 4, 3, 8);
        rightlegext.setRotationPoint(-4F, 9F, 4F);
        rightlegext.setTextureSize(64, 32);
        rightlegext.mirror = true;
        setRotation(rightlegext, 0F, 0F, 0F);
        leftlegext = new ModelRenderer(this, 0, 16);
        leftlegext.func_228300_a_(-2F, 0F, -6F, 4, 3, 8);
        leftlegext.setRotationPoint(4F, 9F, 4F);
        leftlegext.setTextureSize(64, 32);
        leftlegext.mirror = true;
        setRotation(leftlegext, 0F, 0F, 0F);
        rightarmbox = new ModelRenderer(this, 32, 0);
        rightarmbox.func_228300_a_(-4F, 10F, -3F, 6, 4, 6);
        rightarmbox.setRotationPoint(-8F, 2F, 3F);
        rightarmbox.setTextureSize(64, 32);
        rightarmbox.mirror = true;
        setRotation(rightarmbox, 0F, 0F, 0F);
        Shape1 = new ModelRenderer(this, 57, 0);
        Shape1.func_228300_a_(-5F, 0F, 0F, 2, 9, 1);
        Shape1.setRotationPoint(-1F, -3F, 10F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, -0.5948578F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 57, 0);
        Shape2.func_228300_a_(-5F, 0F, 0F, 2, 9, 1);
        Shape2.setRotationPoint(-1F, 0F, 12F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, -0.5948578F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 57, 0);
        Shape3.func_228300_a_(0F, 0F, 0F, 2, 9, 1);
        Shape3.setRotationPoint(-1F, -3F, 10F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, -0.5948578F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 57, 0);
        Shape4.func_228300_a_(0F, 0F, 0F, 2, 9, 1);
        Shape4.setRotationPoint(-1F, 0F, 12F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, -0.5948578F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 57, 0);
        Shape5.func_228300_a_(5F, 0F, 0F, 2, 9, 1);
        Shape5.setRotationPoint(-1F, -3F, 10F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, -0.5948578F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 57, 0);
        Shape6.func_228300_a_(5F, 0F, 0F, 2, 9, 1);
        Shape6.setRotationPoint(-1F, 0F, 12F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, -0.5948578F, 0F, 0F);
    }


    @Override
    public void setRotationAngles(Greenfeet entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.rightarmbox.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarmbox.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarmbox.rotateAngleZ = 0.0F;
        this.leftarmbox.rotateAngleZ = 0.0F;
        this.rightlegext.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftlegext.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightlegext.rotateAngleY = 0.0F;
        this.leftlegext.rotateAngleY = 0.0F;

        this.rightarmbox.rotateAngleY = 0.0F;
        this.leftarmbox.rotateAngleY = 0.0F;

        this.rightarmbox.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftarmbox.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;

        this.rightarmbox.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftarmbox.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
