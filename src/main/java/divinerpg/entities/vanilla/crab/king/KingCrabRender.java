package divinerpg.entities.vanilla.crab.king;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.vanilla.crab.CrabModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class KingCrabRender extends DivineRender<KingCrab, CrabModel<KingCrab>> {
    public KingCrabRender(EntityRendererManager manager) {
        super(manager, new CrabModel<>(), 0, "crab", 2F);
    }
}
