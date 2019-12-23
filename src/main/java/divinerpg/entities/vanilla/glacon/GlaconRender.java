package divinerpg.entities.vanilla.glacon;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GlaconRender extends DivineRender<Glacon, GlaconModel> {
    public GlaconRender(EntityRendererManager manager) {
        super(manager, new GlaconModel(), 0, "glacon");
    }
}
