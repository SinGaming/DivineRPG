package divinerpg.entities.eden.feet;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GreenfeetRender extends DivineRender<Greenfeet, GreenfeetModel> {
    public GreenfeetRender(EntityRendererManager manager) {
        super(manager, new GreenfeetModel(), 0, "greenfeet");
    }
}
