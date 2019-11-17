package divinerpg.entities.vanilla.EnthralledDramcryx;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import org.lwjgl.opengl.GL11;

public class EnthralledDramcryxModel extends EntityModel<EnthralledDramcryx> {
    RendererModel WolfHead;
    RendererModel Body;
    RendererModel Mane;
    RendererModel Leg1;
    RendererModel Leg2;
    RendererModel Leg3;
    RendererModel Leg4;
    RendererModel Tail;
    RendererModel Ear1;
    RendererModel Ear2;
    RendererModel Nose;
    RendererModel Shape1;
    RendererModel Shape2;
    RendererModel Shape3;

    public EnthralledDramcryxModel() {
        textureWidth = 64;
        textureHeight = 32;
        WolfHead = new RendererModel(this, 0, 0);
        WolfHead.addBox(-3F, -3F, -2F, 10, 9, 4);
        WolfHead.setRotationPoint(-3F, 11.5F, -7F);
        WolfHead.setTextureSize(64, 32);
        WolfHead.mirror = true;
        setRotation(WolfHead, 0F, 0F, 0F);
        Body = new RendererModel(this, 18, 14);
        Body.addBox(-4F, -2F, -3F, 6, 9, 6);
        Body.setRotationPoint(0F, 14F, 2F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, ((float) Math.PI / 2F), 0F, 0F);
        Mane = new RendererModel(this, 21, 0);
        Mane.addBox(-4F, -3F, -3F, 8, 10, 7);
        Mane.setRotationPoint(-1F, 14F, -2F);
        Mane.setTextureSize(64, 32);
        Mane.mirror = true;
        setRotation(Mane, ((float) Math.PI / 2F), 0F, 0F);
        Leg1 = new RendererModel(this, 0, 18);
        Leg1.addBox(-1F, 0F, -1F, 3, 8, 3);
        Leg1.setRotationPoint(-5.5F, 16F, 7F);
        Leg1.setTextureSize(64, 32);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new RendererModel(this, 0, 18);
        Leg2.addBox(-1F, 0F, -1F, 3, 8, 3);
        Leg2.setRotationPoint(2.5F, 16F, 7F);
        Leg2.setTextureSize(64, 32);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Leg3 = new RendererModel(this, 0, 18);
        Leg3.addBox(-1F, 0F, -1F, 3, 8, 3);
        Leg3.setRotationPoint(-6.5F, 16F, -4F);
        Leg3.setTextureSize(64, 32);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0F, 0F);
        Leg4 = new RendererModel(this, 0, 18);
        Leg4.addBox(-1F, 0F, -1F, 3, 8, 3);
        Leg4.setRotationPoint(3.5F, 16F, -4F);
        Leg4.setTextureSize(64, 32);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, 0F, 0F);
        Tail = new RendererModel(this, 22, 13);
        Tail.addBox(-1F, 0F, -1F, 4, 12, 4);
        Tail.setRotationPoint(-2F, 14F, 7F);
        Tail.setTextureSize(64, 32);
        Tail.mirror = true;
        setRotation(Tail, 1.130069F, 0F, 0F);
        Ear1 = new RendererModel(this, 16, 14);
        Ear1.addBox(-3F, -5F, 0F, 2, 4, 1);
        Ear1.setRotationPoint(-2F, 9.9F, -7F);
        Ear1.setTextureSize(64, 32);
        Ear1.mirror = true;
        setRotation(Ear1, 0.3490659F, 0F, 0F);
        Ear2 = new RendererModel(this, 16, 14);
        Ear2.addBox(1F, -5F, 0F, 2, 4, 1);
        Ear2.setRotationPoint(0F, 9.9F, -7F);
        Ear2.setTextureSize(64, 32);
        Ear2.mirror = true;
        setRotation(Ear2, 0.3490659F, 0F, 0F);
        Nose = new RendererModel(this, 0, 20);
        Nose.addBox(-2F, 0F, -5F, 6, 5, 4);
        Nose.setRotationPoint(-2F, 11.5F, -8F);
        Nose.setTextureSize(64, 32);
        Nose.mirror = true;
        setRotation(Nose, 0F, 0F, 0F);
        Shape1 = new RendererModel(this, 52, 15);
        Shape1.addBox(-1F, -10F, 0F, 2, 10, 4);
        Shape1.setRotationPoint(-1F, 11F, 4F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, -1.003822F, 0F, 0F);
        Shape2 = new RendererModel(this, 52, 15);
        Shape2.addBox(0F, 0F, 0F, 2, 13, 4);
        Shape2.setRotationPoint(-2F, 0F, 7F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, -0.6320364F, 0F, 0F);
        Shape3 = new RendererModel(this, 52, 15);
        Shape3.addBox(0F, 0F, 0F, 2, 13, 4);
        Shape3.setRotationPoint(-2F, -2F, -5.466667F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0.1115358F, 0F, 0F);
    }

    @Override
    public void render(EnthralledDramcryx entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GL11.glScaled(1.5, 1.5, 1.5);
        GL11.glTranslatef(0f, -0.5f, 0f);
        WolfHead.render(scale);
        Body.render(scale);
        Mane.render(scale);
        Leg1.render(scale);
        Leg2.render(scale);
        Leg3.render(scale);
        Leg4.render(scale);
        Tail.render(scale);
        Ear1.render(scale);
        Ear2.render(scale);
        Nose.render(scale);
        Shape1.render(scale);
        Shape2.render(scale);
        Shape3.render(scale);
    }

    private void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
