package divinerpg.entities.bosses.vamacheron;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class VamacheronRender extends DivineRender<Vamacheron, VamacheronModel> {
    public VamacheronRender(EntityRendererManager manager) {
        super(manager, new VamacheronModel(), 0, "vamacheron");
    }
}
