package divinerpg.entities.twilight.archer.twilight;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class TwilightArcherRender extends DivineRender<TwilightArcher, TwilightArcherModel> {
    public TwilightArcherRender(EntityRendererManager manager) {
        super(manager, new TwilightArcherModel(), 0, "twilight_archer");
    }
}
