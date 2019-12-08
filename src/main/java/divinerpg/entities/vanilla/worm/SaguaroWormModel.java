package divinerpg.entities.vanilla.worm;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class SaguaroWormModel extends DivineModel<SaguaroWorm> {
    RendererModel connector2;
    RendererModel middle;
    RendererModel base;
    RendererModel connector1;
    RendererModel head;

    RendererModel Shape1;
    RendererModel Shape2;
    RendererModel Shape3;

    public SaguaroWormModel() {
        textureWidth = 64;
        textureHeight = 64;

        connector2 = new RendererModel(this, 0, 32);
        connector2.addBox(0F, 0F, 0F, 10, 16, 10);
        connector2.setRotationPoint(-5F, -24F, -21F);
        connector2.setTextureSize(64, 64);
        connector2.mirror = true;
        setRotation(connector2, 0.5759587F, 0F, 0F);
        middle = new RendererModel(this, 0, 0);
        middle.addBox(0F, 0F, 0F, 16, 16, 16);
        middle.setRotationPoint(-8F, -13F, -16F);
        middle.setTextureSize(64, 64);
        middle.mirror = true;
        setRotation(middle, 0.4363323F, 0F, 0F);
        base = new RendererModel(this, 0, 0);
        base.addBox(0F, 0F, 0F, 16, 16, 16);
        base.setRotationPoint(-8F, 8F, -8F);
        base.setTextureSize(64, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        connector1 = new RendererModel(this, 0, 32);
        connector1.addBox(0F, 0F, 0F, 10, 16, 10);
        connector1.setRotationPoint(-5F, -3F, -9F);
        connector1.setTextureSize(64, 64);
        connector1.mirror = true;
        setRotation(connector1, 0.3316126F, 0F, 0F);
        head = new RendererModel(this, 0, 0);
        head.addBox(-8F, -16F, -8F, 16, 16, 16);
        head.setRotationPoint(0F, -24F, -14F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 1.047198F, 0F, 0F);

        Shape1 = new RendererModel(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 16, 16, 16);
        Shape1.setRotationPoint(-8F, -8F, -8F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new RendererModel(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 16, 16, 16);
        Shape2.setRotationPoint(-8F, 8F, -8F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new RendererModel(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 16, 16, 16);
        Shape3.setRotationPoint(-8F, -24F, -8F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
    }

    @Override
    public void render(SaguaroWorm worm, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // TODO not correct
        if (worm.isAggressive()) {
            connector2.render(scale);
            middle.render(scale);
            base.render(scale);
            connector1.render(scale);
            head.render(scale);
        } else {
            Shape1.render(scale);
            Shape2.render(scale);
            Shape3.render(scale);
        }
    }
}
