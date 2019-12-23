package divinerpg.entities.mortum.stealer;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SoulStealerRender extends DivineRender<SoulStealer, SoulStealerModel> {
    public SoulStealerRender(EntityRendererManager manager) {
        super(manager, new SoulStealerModel(), 0, "soul_stealer");
    }
}
