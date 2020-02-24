package divinerpg.entities.eden.madivel;

import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MadivelModel extends DivineModel<Madivel> {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer neckbone;
    ModelRenderer neck;
    ModelRenderer headbone;
    ModelRenderer rightarmbones;
    ModelRenderer leftarmbones;

    public MadivelModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 35, 0);
        head.func_228300_a_(-4F, -8F, -4F, 6, 9, 7);
        head.setRotationPoint(1F, -13F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.func_228300_a_(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, -7F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.func_228300_a_(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, -5F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.func_228300_a_(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, -5F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 9);
        rightleg.func_228300_a_(-2F, 0F, -2F, 4, 19, 4);
        rightleg.setRotationPoint(-2F, 5F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 9);
        leftleg.func_228300_a_(-2F, 0F, -2F, 4, 19, 4);
        leftleg.setRotationPoint(2F, 5F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        neckbone = new ModelRenderer(this, 2, 1);
        neckbone.func_228300_a_(-6F, 1F, -1F, 10, 2, 2);
        neckbone.setRotationPoint(1F, -13F, 0F);
        neckbone.setTextureSize(64, 32);
        neckbone.mirror = true;
        setRotation(neckbone, 0F, 0F, 0F);
        neck = new ModelRenderer(this, 36, 20);
        neck.func_228300_a_(0F, 0F, 0F, 4, 5, 4);
        neck.setRotationPoint(-2F, -12F, -2F);
        neck.setTextureSize(64, 32);
        neck.mirror = true;
        setRotation(neck, 0F, 0F, 0F);
        headbone = new ModelRenderer(this, 2, 1);
        headbone.func_228300_a_(-6F, -2F, 0F, 10, 2, 2);
        headbone.setRotationPoint(1F, -18F, -1F);
        headbone.setTextureSize(64, 32);
        headbone.mirror = true;
        setRotation(headbone, 0F, 0F, 0F);
        rightarmbones = new ModelRenderer(this, 40, 24);
        rightarmbones.func_228300_a_(-8F, -2F, -1F, 6, 3, 2);
        rightarmbones.setRotationPoint(-5F, -1F, 0F);
        rightarmbones.setTextureSize(64, 32);
        rightarmbones.mirror = true;
        setRotation(rightarmbones, 0F, 0F, 0F);
        leftarmbones = new ModelRenderer(this, 40, 24);
        leftarmbones.func_228300_a_(2F, 2F, -1F, 6, 3, 2);
        leftarmbones.setRotationPoint(5F, -5F, 0F);
        leftarmbones.setTextureSize(64, 32);
        leftarmbones.mirror = true;
        setRotation(leftarmbones, 0F, 0F, 0F);


    }

    @Override
    public void setRotationAngles(Madivel entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
    }
}
