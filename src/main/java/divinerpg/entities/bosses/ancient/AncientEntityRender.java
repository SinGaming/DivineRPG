package divinerpg.entities.bosses.ancient;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AncientEntityRender extends DivineRender<DivineBoss, AncientEntityModel> {
    public AncientEntityRender(EntityRendererManager manager) {
        super(manager, new AncientEntityModel(), 0, "ancient_entity", 5F);
    }
}
