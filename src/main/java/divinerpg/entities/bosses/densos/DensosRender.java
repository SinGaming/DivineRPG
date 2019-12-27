package divinerpg.entities.bosses.densos;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DensosRender extends DivineRender<DivineBoss, DensosModel> {
    public DensosRender(EntityRendererManager manager) {
        super(manager, new DensosModel(), 0.5F, "");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(DivineBoss entity) {
        return CachedTexture.ENTITIES.getTexture(entity.getType().getRegistryName().getPath());
    }
}
