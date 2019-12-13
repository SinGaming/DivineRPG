package divinerpg.entities.twilight.cori;

import divinerpg.entities.base.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CoriRender extends DivineRender<MobEntity, CoriModel> {
    public CoriRender(EntityRendererManager manager) {
        super(manager, new CoriModel(), 0, "");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MobEntity entity) {
        return CachedTexture.ENTITIES.getTexture(entity.getType().getRegistryName().getPath());
    }
}
