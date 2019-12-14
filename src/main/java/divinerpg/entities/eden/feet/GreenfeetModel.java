package divinerpg.entities.eden.feet;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class GreenfeetModel extends BipedModel<Greenfeet> {
    RendererModel leftlegext;
    RendererModel rightlegext;
    RendererModel leftarmbox;
    RendererModel rightarmbox;
    RendererModel Shape1;
    RendererModel Shape2;
    RendererModel Shape3;
    RendererModel Shape4;
    RendererModel Shape5;
    RendererModel Shape6;

    public GreenfeetModel() {
        textureWidth = 64;
        textureHeight = 32;

        leftarmbox = new RendererModel(this, 32, 0);
        leftarmbox.addBox(-2F, 10F, -3F, 6, 4, 6);
        leftarmbox.setRotationPoint(8F, 2F, 3F);
        leftarmbox.setTextureSize(64, 32);
        leftarmbox.mirror = true;
        setRotation(leftarmbox, 0F, 0F, 0F);
        rightlegext = new RendererModel(this, 0, 16);
        rightlegext.addBox(-2F, 0F, -6F, 4, 3, 8);
        rightlegext.setRotationPoint(-4F, 9F, 4F);
        rightlegext.setTextureSize(64, 32);
        rightlegext.mirror = true;
        setRotation(rightlegext, 0F, 0F, 0F);
        leftlegext = new RendererModel(this, 0, 16);
        leftlegext.addBox(-2F, 0F, -6F, 4, 3, 8);
        leftlegext.setRotationPoint(4F, 9F, 4F);
        leftlegext.setTextureSize(64, 32);
        leftlegext.mirror = true;
        setRotation(leftlegext, 0F, 0F, 0F);
        rightarmbox = new RendererModel(this, 32, 0);
        rightarmbox.addBox(-4F, 10F, -3F, 6, 4, 6);
        rightarmbox.setRotationPoint(-8F, 2F, 3F);
        rightarmbox.setTextureSize(64, 32);
        rightarmbox.mirror = true;
        setRotation(rightarmbox, 0F, 0F, 0F);
        Shape1 = new RendererModel(this, 57, 0);
        Shape1.addBox(-5F, 0F, 0F, 2, 9, 1);
        Shape1.setRotationPoint(-1F, -3F, 10F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, -0.5948578F, 0F, 0F);
        Shape2 = new RendererModel(this, 57, 0);
        Shape2.addBox(-5F, 0F, 0F, 2, 9, 1);
        Shape2.setRotationPoint(-1F, 0F, 12F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, -0.5948578F, 0F, 0F);
        Shape3 = new RendererModel(this, 57, 0);
        Shape3.addBox(0F, 0F, 0F, 2, 9, 1);
        Shape3.setRotationPoint(-1F, -3F, 10F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, -0.5948578F, 0F, 0F);
        Shape4 = new RendererModel(this, 57, 0);
        Shape4.addBox(0F, 0F, 0F, 2, 9, 1);
        Shape4.setRotationPoint(-1F, 0F, 12F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, -0.5948578F, 0F, 0F);
        Shape5 = new RendererModel(this, 57, 0);
        Shape5.addBox(5F, 0F, 0F, 2, 9, 1);
        Shape5.setRotationPoint(-1F, -3F, 10F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, -0.5948578F, 0F, 0F);
        Shape6 = new RendererModel(this, 57, 0);
        Shape6.addBox(5F, 0F, 0F, 2, 9, 1);
        Shape6.setRotationPoint(-1F, 0F, 12F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, -0.5948578F, 0F, 0F);
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(Greenfeet entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

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
