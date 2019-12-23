package divinerpg.entities.bosses.scorcher;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class KingScorcherRender extends DivineRender<DivineBoss, KingScorcherModel> {
    public KingScorcherRender(EntityRendererManager manager) {
        super(manager, new KingScorcherModel(), 0, "king_of_scorchers");
    }
}
