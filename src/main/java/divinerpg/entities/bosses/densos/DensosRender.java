package divinerpg.entities.bosses.densos;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DensosRender extends DivineRender<DivineBoss, DensosModel> {
    public DensosRender(EntityRendererManager manager) {
        super(manager, new DensosModel(), 0.5F, "densos");
    }
}
