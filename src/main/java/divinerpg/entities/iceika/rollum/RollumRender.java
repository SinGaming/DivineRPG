package divinerpg.entities.iceika.rollum;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class RollumRender extends DivineRender<Rollum, RollumModel> {
    public RollumRender(EntityRendererManager manager) {
        super(manager, new RollumModel(), 0, "rollum");
    }
}
