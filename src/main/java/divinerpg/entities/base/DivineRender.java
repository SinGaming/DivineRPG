package divinerpg.entities.base;

import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DivineRender<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation location;

    public DivineRender(EntityRendererManager manager, M model, float shadow, String name) {
        super(manager, model, shadow);

        location = new ResourceLocation(DivineRPG.MODID, String.format("textures/entity/%s.png", name));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return location;
    }
}
