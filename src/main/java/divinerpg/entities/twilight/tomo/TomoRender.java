package divinerpg.entities.twilight.tomo;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class TomoRender extends DivineRender<MobEntity, TomoModel> {
    public TomoRender(EntityRendererManager manager) {
        super(manager, new TomoModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(MobEntity entity) {
        return textureBasedOnType(entity);
    }
}
