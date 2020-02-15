package divinerpg.entities.vanilla.grue;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class GrueModel extends DivineModel<Grue> {
    ModelRenderer toeRightOut;
    ModelRenderer toeLeftOut;
    ModelRenderer toeRightIn;
    ModelRenderer toeLeftIn;
    ModelRenderer legRight;
    ModelRenderer legLeft;
    ModelRenderer thighRight;
    ModelRenderer thighLeft;
    ModelRenderer torso;
    ModelRenderer body;
    ModelRenderer Shape6;
    ModelRenderer tail1;
    ModelRenderer tail3;
    ModelRenderer tail2;
    ModelRenderer mouth;
    ModelRenderer head;

    public GrueModel() {
        textureWidth = 64;
        textureHeight = 27;

        toeRightOut = new ModelRenderer(this, 0, 0);
        toeRightOut.addBox(3F, -6F, 15F, 2, 11, 2);
        toeRightOut.setRotationPoint(-5F, 2F, 4F);
        toeRightOut.setTextureSize(32, 27);
        toeRightOut.mirror = true;
        setRotation(toeRightOut, -1.152537F, 0.8922867F, 0F);
        toeLeftOut = new ModelRenderer(this, 0, 0);
        toeLeftOut.addBox(-6F, -6F, 15F, 2, 11, 2);
        toeLeftOut.setRotationPoint(8F, 2F, 4F);
        toeLeftOut.setTextureSize(32, 27);
        toeLeftOut.mirror = true;
        setRotation(toeLeftOut, -1.152537F, -0.8179294F, 0F);
        toeRightIn = new ModelRenderer(this, 0, 0);
        toeRightIn.addBox(11F, 3F, 12F, 2, 11, 2);
        toeRightIn.setRotationPoint(-5F, 2F, 4F);
        toeRightIn.setTextureSize(32, 27);
        toeRightIn.mirror = true;
        setRotation(toeRightIn, -1.152537F, -0.0743572F, 0F);
        toeLeftIn = new ModelRenderer(this, 0, 0);
        toeLeftIn.addBox(-14F, 3F, 11F, 2, 11, 2);
        toeLeftIn.setRotationPoint(8F, 2F, 4F);
        toeLeftIn.setTextureSize(32, 27);
        toeLeftIn.mirror = true;
        setRotation(toeLeftIn, -1.152537F, 0.1487144F, 0F);
        legRight = new ModelRenderer(this, 0, 0);
        legRight.addBox(4F, 4F, 6F, 3, 10, 3);
        legRight.setRotationPoint(-5F, 2F, 4F);
        legRight.setTextureSize(32, 27);
        legRight.mirror = true;
        setRotation(legRight, -0.3717861F, 0F, 0.2230717F);
        legLeft = new ModelRenderer(this, 0, 0);
        legLeft.addBox(-6F, 4F, 7F, 3, 10, 3);
        legLeft.setRotationPoint(8F, 2F, 4F);
        legLeft.setTextureSize(32, 27);
        legLeft.mirror = true;
        setRotation(legLeft, -0.4089647F, 0F, -0.2230717F);
        thighRight = new ModelRenderer(this, 0, 0);
        thighRight.addBox(-2F, 0F, -2F, 4, 11, 4);
        thighRight.setRotationPoint(-5F, 2F, 4F);
        thighRight.setTextureSize(32, 27);
        thighRight.mirror = true;
        setRotation(thighRight, 0.6145831F, 0F, -0.4089647F);
        thighLeft = new ModelRenderer(this, 0, 0);
        thighLeft.addBox(-2F, 0F, -2F, 4, 11, 4);
        thighLeft.setRotationPoint(8F, 2F, 4F);
        thighLeft.setTextureSize(32, 27);
        thighLeft.mirror = true;
        setRotation(thighLeft, 0.6145831F, 0F, 0.4089647F);
        torso = new ModelRenderer(this, 0, 0);
        torso.addBox(0F, 0F, 0F, 12, 6, 4);
        torso.setRotationPoint(-5F, -3F, 3F);
        torso.setTextureSize(32, 27);
        torso.mirror = true;
        setRotation(torso, -0.2230717F, 0F, 0F);
        body = new ModelRenderer(this, 0, 0);
        body.addBox(0F, 0F, 0F, 8, 9, 10);
        body.setRotationPoint(-3F, -9.333333F, 0F);
        body.setTextureSize(32, 27);
        body.mirror = true;
        setRotation(body, -0.0743572F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 6, 8, 8);
        Shape6.setRotationPoint(-2F, -6F, -7F);
        Shape6.setTextureSize(32, 27);
        Shape6.mirror = true;
        setRotation(Shape6, 0.3717861F, 0F, 0F);
        tail1 = new ModelRenderer(this, 0, 0);
        tail1.addBox(0F, 0F, 0F, 6, 6, 7);
        tail1.setRotationPoint(-2F, -2F, 7F);
        tail1.setTextureSize(32, 27);
        tail1.mirror = true;
        setRotation(tail1, 1.226894F, 0F, 0F);
        tail3 = new ModelRenderer(this, 0, 0);
        tail3.addBox(0F, 0F, 0F, 2, 2, 2);
        tail3.setRotationPoint(0F, -3F, 17F);
        tail3.setTextureSize(32, 27);
        tail3.mirror = true;
        setRotation(tail3, 0.7807508F, 0F, 0F);
        tail2 = new ModelRenderer(this, 0, 0);
        tail2.addBox(0F, 0F, 0F, 4, 4, 4);
        tail2.setRotationPoint(-1F, -3F, 13F);
        tail2.setTextureSize(32, 27);
        tail2.mirror = true;
        setRotation(tail2, 1.041001F, 0F, 0F);
        mouth = new ModelRenderer(this, 0, 0);
        mouth.addBox(0F, 0F, 0F, 8, 2, 6);
        mouth.setRotationPoint(-3F, 4F, -9.466666F);
        mouth.setTextureSize(32, 27);
        mouth.mirror = true;
        setRotation(mouth, 0.669215F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(0F, 0F, 0F, 8, 7, 11);
        head.setRotationPoint(-3F, -3F, -17F);
        head.setTextureSize(32, 27);
        head.mirror = true;
        setRotation(head, 0.3346075F, 0F, 0F);
        legLeft.addChild(toeLeftIn);
        legLeft.addChild(toeLeftOut);
        legRight.addChild(toeRightIn);
        legRight.addChild(toeRightOut);
    }

    @Override
    public void render(Grue entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.1875f, 0);

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(Grue entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.legLeft.rotateAngleX = -0.4089647F + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4f * limbSwingAmount);
        this.thighLeft.rotateAngleX = 0.6145831F + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4f * limbSwingAmount);
        this.legRight.rotateAngleX = -0.4089647F + (MathHelper.cos(limbSwing * 0.6662F) * 1.4f * limbSwingAmount);
        this.thighRight.rotateAngleX = 0.6145831F + (MathHelper.cos(limbSwing * 0.6662F) * 1.4f * limbSwingAmount);
    }
}
