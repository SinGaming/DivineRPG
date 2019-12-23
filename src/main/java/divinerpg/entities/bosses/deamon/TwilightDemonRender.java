package divinerpg.entities.bosses.deamon;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class TwilightDemonRender extends DivineRender<DivineBoss, TwilightDemonModel> {
    public TwilightDemonRender(EntityRendererManager manager) {
        super(manager, new TwilightDemonModel(), 0, "twilight_demon", 2F);
    }
}
