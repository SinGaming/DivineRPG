package divinerpg.entities.bosses.karot;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class KarotRender extends DivineRender<DivineBoss, KarotModel> {
    public KarotRender(EntityRendererManager manager) {
        super(manager, new KarotModel(), 0, "karot", 5F);
    }
}
