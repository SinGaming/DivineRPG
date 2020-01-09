package divinerpg.entities.iceika.hastreus;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class HastreusRender extends DivineRender<Hastreus,HastreusModel> {
    public HastreusRender(EntityRendererManager manager) {
        super(manager, new HastreusModel(), 0, "hastreus");
    }
}
