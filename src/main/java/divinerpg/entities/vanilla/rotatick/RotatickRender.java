package divinerpg.entities.vanilla.rotatick;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class RotatickRender extends DivineRender<Rotatick, RotatickModel> {
    public RotatickRender(EntityRendererManager manager) {
        super(manager, new RotatickModel(), 0, "rotatick");
    }
}
