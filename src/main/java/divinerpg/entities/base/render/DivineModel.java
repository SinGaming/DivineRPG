package divinerpg.entities.base.render;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyValue;

import java.util.List;

public class DivineModel<T extends Entity> extends AgeableModel<T> implements IEntityRenderDeobf<T> {
    /**
     * Is entity aggressive
     */
    public boolean aggressive;
    protected LazyValue<List<ModelRenderer>> parts;

    protected DivineModel() {
        parts = new LazyValue<>(this::getRenderers);
    }

    @Override
    public final void func_225597_a_(T t, float v, float v1, float v2, float v3, float v4) {
        setRotationAngles(t, v, v1, v2, v3, v4);
    }

    @Override
    protected final Iterable<ModelRenderer> func_225602_a_() {
        return getHeadParts();
    }

    @Override
    protected final Iterable<ModelRenderer> func_225600_b_() {
        return getBodyParts();
    }

    @Override
    public final void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }


    /**
     * Using reflection once to find all ModelRenderer fields
     *
     * @return
     */
    protected List<ModelRenderer> getRenderers() {
        return ReflectionHelper.getDeclaredFieldsValues(this, ModelRenderer.class);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn,
                       int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.func_225598_a_(stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public Iterable<ModelRenderer> getBodyParts() {
        return parts.getValue();
    }

    @Override
    public Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }
}
