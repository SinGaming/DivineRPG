package divinerpg.entities.iceika.glacide;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GlacideRender extends DivineRender<Glacide, GlacideModel> {
    public GlacideRender(EntityRendererManager manager) {
        super(manager, new GlacideModel(), 0, "glacon");
    }
}
