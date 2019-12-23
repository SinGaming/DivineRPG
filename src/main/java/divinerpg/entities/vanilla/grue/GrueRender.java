package divinerpg.entities.vanilla.grue;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GrueRender extends DivineRender<Grue, GrueModel> {
    public GrueRender(EntityRendererManager manager) {
        super(manager, new GrueModel(), 0, "the_grue");
    }
}
