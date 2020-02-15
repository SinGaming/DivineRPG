package divinerpg.entities.twilight.archer;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ArcherRender extends DivineRender<MobEntity, ArcherModel> {
    public ArcherRender(EntityRendererManager manager) {
        super(manager, new ArcherModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(MobEntity entity) {
        return textureBasedOnType(entity);
    }
}
