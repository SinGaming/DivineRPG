package divinerpg.entities.base;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyLoadBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private List<RendererModel> getRenderers() {
        return ReflectionHelper.getDeclaredFieldsValues(this, RendererModel.class);
    }

    private List<RendererModel> getDistinct(Field[] fields) {
        if (fields != null && fields.length > 0) {
            Class<RendererModel> modelClass = RendererModel.class;

            return Stream.of(fields)
                    .filter(x -> x.getType().equals(modelClass))
                    .map(x -> {
                        try {
                            x.setAccessible(true);
                            return (RendererModel) x.get(this);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
