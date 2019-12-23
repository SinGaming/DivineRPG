package divinerpg.entities.bosses.etheral;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EternalArcherRender extends DivineRender<DivineBoss, EternalArcherModel> {
    public EternalArcherRender(EntityRendererManager manager) {
        super(manager, new EternalArcherModel(), 0, "eternal_archer", 2.5F);
    }
}
