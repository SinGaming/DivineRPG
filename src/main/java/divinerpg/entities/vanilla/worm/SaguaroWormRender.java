package divinerpg.entities.vanilla.worm;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SaguaroWormRender extends DivineRender<SaguaroWorm, SaguaroWormModel> {
    public SaguaroWormRender(EntityRendererManager manager) {
        super(manager, new SaguaroWormModel(), 0, "saguaro_worm");
    }
}
