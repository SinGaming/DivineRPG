package divinerpg.entities.base.render;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyLoadBase;

import java.util.List;

public class DivineModel<T extends Entity> extends EntityModel<T> {
    protected LazyLoadBase<List<ModelRenderer>> parts;

    protected DivineModel() {
        parts = new LazyLoadBase<>(this::getRenderers);
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        for (ModelRenderer model : parts.getValue()) {
            if (model != null)
                model.render(scale);
        }
    }

    /**
     * Using reflection once to find all ModelRenderer fields
     *
     * @return
     */
    protected List<ModelRenderer> getRenderers() {
        return ReflectionHelper.getDeclaredFieldsValues(this, ModelRenderer.class);
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
