package divinerpg.entities.bosses.fiend;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SoulFiendRender extends DivineRender<DivineBoss, SoulFiendModel> {
    public SoulFiendRender(EntityRendererManager manager) {
        super(manager, new SoulFiendModel(), 0, "soul_fiend");
    }
}
