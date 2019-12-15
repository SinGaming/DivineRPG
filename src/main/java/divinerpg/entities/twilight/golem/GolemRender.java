package divinerpg.entities.twilight.golem;

import divinerpg.entities.base.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GolemRender extends DivineRender<MobEntity, GolemModel> {
    public GolemRender(EntityRendererManager manager) {
        super(manager, new GolemModel(), 0, "");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MobEntity entity) {
        return CachedTexture.ENTITIES.getTexture(entity.getType().getRegistryName().getPath());
    }
}
