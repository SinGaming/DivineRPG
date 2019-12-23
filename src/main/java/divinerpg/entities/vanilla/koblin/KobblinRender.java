package divinerpg.entities.vanilla.koblin;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class KobblinRender extends DivineRender<Kobblin, KobblinModel> {
    public KobblinRender(EntityRendererManager manager) {
        super(manager, new KobblinModel(), 0, "kobblin");
    }
}
