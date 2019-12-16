package divinerpg.entities.twilight.mystic;

import divinerpg.entities.base.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class MysticRender extends DivineRender<MobEntity, MysticModel> {

    public MysticRender(EntityRendererManager manager) {
        super(manager, new MysticModel(), 0, "");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MobEntity entity) {
        return CachedTexture.ENTITIES.getTexture(entity.getType().getRegistryName().getPath());
    }
}
