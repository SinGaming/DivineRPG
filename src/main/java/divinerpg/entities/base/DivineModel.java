package divinerpg.entities.base;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyLoadBase;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DivineModel<T extends Entity> extends EntityModel<T> {
    protected LazyLoadBase<List<RendererModel>> parts;
    private final float scale;

    protected DivineModel() {
        this(1);
    }

    protected DivineModel(float scale) {
        this.scale = scale;
        parts = new LazyLoadBase<>(this::getRenderers);
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (this.scale != 1) {
            GL11.glScaled(this.scale, this.scale, this.scale);
            GL11.glTranslatef(0f, -(this.scale / 1.8F), 0f);
        }

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        for (RendererModel model : parts.getValue()) {
            model.render(scale);
        }
    }

    /**
     * Using reflection once to find all RendererModel fields
     *
     * @return
     */
    private List<RendererModel> getRenderers() {
        List<RendererModel> result = getDistinct(this.getClass().getFields());
        result.addAll(getDistinct(this.getClass().getDeclaredFields()));

        return result.stream().distinct().collect(Collectors.toList());
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
