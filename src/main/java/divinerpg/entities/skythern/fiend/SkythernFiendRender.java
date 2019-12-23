package divinerpg.entities.skythern.fiend;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SkythernFiendRender extends DivineRender<SkythernFiend, SkythernFiendModel> {
    public SkythernFiendRender(EntityRendererManager manager) {
        super(manager, new SkythernFiendModel(), 0, "skythern_fiend");
    }
}
