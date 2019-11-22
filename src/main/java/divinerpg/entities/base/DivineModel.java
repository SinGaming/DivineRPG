package divinerpg.entities.base;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.LazyLoadBase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DivineModel<T extends Entity> extends EntityModel<T> {
    protected LazyLoadBase<List<RendererModel>> parts;

    protected DivineModel() {
        parts = new LazyLoadBase<>(this::getRenderers);
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        parts.getValue().forEach(x -> x.render(scale));
    }

    /**
     * Using reflection once to find all RendererModel fields
     *
     * @return
     */
    private List<RendererModel> getRenderers() {
        return Arrays.stream(this.getClass().getDeclaredFields()).filter(x -> x.getType().equals(RendererModel.class))
                .map(x -> {
                    try {
                        x.setAccessible(true);
                        return (RendererModel) x.get(this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
