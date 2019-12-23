package divinerpg.entities.wildwood.epiphite;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EpiphiteRender extends DivineRender<Epiphite, EpiphiteModel> {
    public EpiphiteRender(EntityRendererManager manager) {
        super(manager, new EpiphiteModel(), 0, "epiphite", 1.25f);
    }
}
