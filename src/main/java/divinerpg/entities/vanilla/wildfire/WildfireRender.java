package divinerpg.entities.vanilla.wildfire;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WildfireRender extends DivineRender<Wildfire, WildfireModel> {
    public WildfireRender(EntityRendererManager manager) {
        super(manager, new WildfireModel(), 0, "wildfire");
    }
}
