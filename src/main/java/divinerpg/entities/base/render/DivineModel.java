package divinerpg.entities.base.render;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyLoadBase;

import java.util.List;

public class DivineModel<T extends Entity> extends EntityModel<T> {
    protected LazyLoadBase<List<RendererModel>> parts;

    protected DivineModel() {
        parts = new LazyLoadBase<>(this::getRenderers);
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        for (RendererModel model : parts.getValue()) {
            if (model != null)
                model.render(scale);
        }
    }

    /**
     * Using reflection once to find all RendererModel fields
     *
     * @return
     */
    protected List<RendererModel> getRenderers() {
        return ReflectionHelper.getDeclaredFieldsValues(this, RendererModel.class);
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
