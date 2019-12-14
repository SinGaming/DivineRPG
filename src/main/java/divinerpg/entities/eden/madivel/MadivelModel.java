package divinerpg.entities.eden.madivel;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class MadivelModel extends DivineModel<Madivel> {
    RendererModel head;
    RendererModel body;
    RendererModel rightarm;
    RendererModel leftarm;
    RendererModel rightleg;
    RendererModel leftleg;
    RendererModel neckbone;
    RendererModel neck;
    RendererModel headbone;
    RendererModel rightarmbones;
    RendererModel leftarmbones;

    public MadivelModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new RendererModel(this, 35, 0);
        head.addBox(-4F, -8F, -4F, 6, 9, 7);
        head.setRotationPoint(1F, -13F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new RendererModel(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, -7F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new RendererModel(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, -5F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new RendererModel(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, -5F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new RendererModel(this, 0, 9);
        rightleg.addBox(-2F, 0F, -2F, 4, 19, 4);
        rightleg.setRotationPoint(-2F, 5F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new RendererModel(this, 0, 9);
        leftleg.addBox(-2F, 0F, -2F, 4, 19, 4);
        leftleg.setRotationPoint(2F, 5F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        neckbone = new RendererModel(this, 2, 1);
        neckbone.addBox(-6F, 1F, -1F, 10, 2, 2);
        neckbone.setRotationPoint(1F, -13F, 0F);
        neckbone.setTextureSize(64, 32);
        neckbone.mirror = true;
        setRotation(neckbone, 0F, 0F, 0F);
        neck = new RendererModel(this, 36, 20);
        neck.addBox(0F, 0F, 0F, 4, 5, 4);
        neck.setRotationPoint(-2F, -12F, -2F);
        neck.setTextureSize(64, 32);
        neck.mirror = true;
        setRotation(neck, 0F, 0F, 0F);
        headbone = new RendererModel(this, 2, 1);
        headbone.addBox(-6F, -2F, 0F, 10, 2, 2);
        headbone.setRotationPoint(1F, -18F, -1F);
        headbone.setTextureSize(64, 32);
        headbone.mirror = true;
        setRotation(headbone, 0F, 0F, 0F);
        rightarmbones = new RendererModel(this, 40, 24);
        rightarmbones.addBox(-8F, -2F, -1F, 6, 3, 2);
        rightarmbones.setRotationPoint(-5F, -1F, 0F);
        rightarmbones.setTextureSize(64, 32);
        rightarmbones.mirror = true;
        setRotation(rightarmbones, 0F, 0F, 0F);
        leftarmbones = new RendererModel(this, 40, 24);
        leftarmbones.addBox(2F, 2F, -1F, 6, 3, 2);
        leftarmbones.setRotationPoint(5F, -5F, 0F);
        leftarmbones.setTextureSize(64, 32);
        leftarmbones.mirror = true;
        setRotation(leftarmbones, 0F, 0F, 0F);


    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(Madivel entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

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
