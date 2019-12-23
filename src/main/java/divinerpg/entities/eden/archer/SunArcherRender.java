package divinerpg.entities.eden.archer;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SunArcherRender extends DivineRender<SunArcher, SunArcherModel> {
    public SunArcherRender(EntityRendererManager manager) {
        super(manager, new SunArcherModel(), 0, "sun_archer");
    }
}
