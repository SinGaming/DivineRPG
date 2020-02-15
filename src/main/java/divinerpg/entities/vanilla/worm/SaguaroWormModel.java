package divinerpg.entities.vanilla.worm;

import com.google.common.collect.ImmutableList;
import divinerpg.entities.base.render.DivineModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SaguaroWormModel extends DivineModel<SaguaroWorm> {
    ModelRenderer connector2;
    ModelRenderer middle;
    ModelRenderer base;
    ModelRenderer connector1;
    ModelRenderer head;

    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public SaguaroWormModel() {
        textureWidth = 64;
        textureHeight = 64;

        connector2 = new ModelRenderer(this, 0, 32);
        connector2.func_228300_a_(0F, 0F, 0F, 10, 16, 10);
        connector2.setRotationPoint(-5F, -24F, -21F);
        connector2.setTextureSize(64, 64);
        connector2.mirror = true;
        setRotation(connector2, 0.5759587F, 0F, 0F);
        middle = new ModelRenderer(this, 0, 0);
        middle.func_228300_a_(0F, 0F, 0F, 16, 16, 16);
        middle.setRotationPoint(-8F, -13F, -16F);
        middle.setTextureSize(64, 64);
        middle.mirror = true;
        setRotation(middle, 0.4363323F, 0F, 0F);
        base = new ModelRenderer(this, 0, 0);
        base.func_228300_a_(0F, 0F, 0F, 16, 16, 16);
        base.setRotationPoint(-8F, 8F, -8F);
        base.setTextureSize(64, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        connector1 = new ModelRenderer(this, 0, 32);
        connector1.func_228300_a_(0F, 0F, 0F, 10, 16, 10);
        connector1.setRotationPoint(-5F, -3F, -9F);
        connector1.setTextureSize(64, 64);
        connector1.mirror = true;
        setRotation(connector1, 0.3316126F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.func_228300_a_(-8F, -16F, -8F, 16, 16, 16);
        head.setRotationPoint(0F, -24F, -14F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 1.047198F, 0F, 0F);

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.func_228300_a_(0F, 0F, 0F, 16, 16, 16);
        Shape1.setRotationPoint(-8F, -8F, -8F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.func_228300_a_(0F, 0F, 0F, 16, 16, 16);
        Shape2.setRotationPoint(-8F, 8F, -8F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.func_228300_a_(0F, 0F, 0F, 16, 16, 16);
        Shape3.setRotationPoint(-8F, -24F, -8F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
    }

    @Override
    public Iterable<ModelRenderer> getBodyParts() {
        return aggressive
                ? ImmutableList.of(connector2, middle, base, connector1)
                : ImmutableList.of(Shape1, Shape2, Shape3);
    }

    @Override
    public Iterable<ModelRenderer> getHeadParts() {
        return aggressive
                ? ImmutableList.of(head)
                : ImmutableList.of();
    }
}
