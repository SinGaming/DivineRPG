package divinerpg.entities.iceika.frosty;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class FrostyRender extends DivineRender<Frosty, FrostyModel> {
    public FrostyRender(EntityRendererManager manager) {
        super(manager, new FrostyModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(Frosty entity) {
        String name = "frosty";
        if (entity.isAggressive()) {
            name = "angry_" + name;
        }

        return CachedTexture.ENTITIES.getTexture(name);
    }
}
