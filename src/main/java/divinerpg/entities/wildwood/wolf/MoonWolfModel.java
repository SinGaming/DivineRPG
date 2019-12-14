package divinerpg.entities.wildwood.wolf;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.WolfModel;

public class MoonWolfModel extends WolfModel<MoonWolf> {
    RendererModel Ear1;
    RendererModel Ear2;
    RendererModel Nose;
    RendererModel Ear4;
    RendererModel Ear3;
    RendererModel spike1;
    RendererModel spike2;
    RendererModel spike3;
    RendererModel spike4;
    RendererModel Shape1;
    RendererModel Shape2;

    // Just a ref
    RendererModel Body;

    public MoonWolfModel() {
        super();

        this.Body = ReflectionHelper.getFieldValue(this, "body", RendererModel.class);

        textureWidth = 64;
        textureHeight = 32;


        Ear1 = new RendererModel(this, 16, 14);
        Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
        Ear1.setRotationPoint(-1F, 13.5F, -7F);
        Ear1.setTextureSize(64, 32);
        Ear1.mirror = true;
        setRotation(Ear1, 0F, 0F, 0F);
        Ear2 = new RendererModel(this, 16, 14);
        Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
        Ear2.setRotationPoint(-1F, 13.5F, -7F);
        Ear2.setTextureSize(64, 32);
        Ear2.mirror = true;
        setRotation(Ear2, 0F, 0F, 0F);
        Nose = new RendererModel(this, 0, 10);
        Nose.addBox(-2F, 0F, -5F, 3, 3, 4);
        Nose.setRotationPoint(-0.5F, 13.5F, -7F);
        Nose.setTextureSize(64, 32);
        Nose.mirror = true;
        setRotation(Nose, 0F, 0F, 0F);
        Ear4 = new RendererModel(this, 16, 14);
        Ear4.addBox(0F, 0F, 0F, 2, 2, 1);
        Ear4.setRotationPoint(0F, 8F, -5F);
        Ear4.setTextureSize(64, 32);
        Ear4.mirror = true;
        setRotation(Ear4, 0F, 0F, 0F);
        Ear3 = new RendererModel(this, 16, 14);
        Ear3.addBox(0F, 0F, 0F, 2, 2, 1);
        Ear3.setRotationPoint(-4F, 8F, -5F);
        Ear3.setTextureSize(64, 32);
        Ear3.mirror = true;
        setRotation(Ear3, 0F, 0F, 0F);
        spike1 = new RendererModel(this, 37, 0);
        spike1.addBox(13F, 0F, -1F, 2, 6, 1);
        spike1.setRotationPoint(0F, 14F, 2F);
        spike1.setTextureSize(64, 32);
        spike1.mirror = true;
        setRotation(spike1, 0.7853982F, 0F, 3 * (float) Math.PI / 2);
        spike2 = new RendererModel(this, 37, 0);
        spike2.addBox(8F, 0F, -1F, 2, 6, 1);
        spike2.setRotationPoint(0F, 14F, 2F);
        spike2.setTextureSize(64, 32);
        spike2.mirror = true;
        setRotation(spike2, 0.7853982F, 0F, 3 * (float) Math.PI / 2);
        spike3 = new RendererModel(this, 37, 0);
        spike3.addBox(-15F, 0.5F, -1.5F, 2, 6, 1);
        spike3.setRotationPoint(-1F, 14F, 2F);
        spike3.setTextureSize(64, 32);
        spike3.mirror = true;
        setRotation(spike3, 0.7853982F, 0F, (float) Math.PI / 2);
        spike4 = new RendererModel(this, 37, 0);
        spike4.addBox(-10F, 0.5F, -1.5F, 2, 6, 1);
        spike4.setRotationPoint(-1F, 14F, 2F);
        spike4.setTextureSize(64, 32);
        spike4.mirror = true;
        setRotation(spike4, 0.7853982F, 0F, (float) Math.PI / 2);
        Shape1 = new RendererModel(this, 4, 0);
        Shape1.addBox(0F, 0F, 0F, 1, 1, 4);
        Shape1.setRotationPoint(1F, 11F, -12F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new RendererModel(this, 4, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 1, 4);
        Shape2.setRotationPoint(-4F, 11F, -12F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);

        this.Body.addChild(spike1);
        this.Body.addChild(spike2);
        this.Body.addChild(spike3);
        this.Body.addChild(spike4);
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(MoonWolf entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        float yaw = netHeadYaw / (180F / (float) Math.PI);
        float pitch = headPitch / (180F / (float) Math.PI);

        this.Nose.rotateAngleY = yaw;
        this.Ear1.rotateAngleX = pitch;
        this.Ear1.rotateAngleY = yaw;
        this.Ear2.rotateAngleX = pitch;
        this.Ear2.rotateAngleY = yaw;
        this.Ear3.rotateAngleX = pitch;
        this.Ear3.rotateAngleY = yaw;
        this.Ear4.rotateAngleX = pitch;
        this.Ear4.rotateAngleY = yaw;
    }
}
