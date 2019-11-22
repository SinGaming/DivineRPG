package divinerpg.entities.vanilla.crab.king;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.crab.CrabModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class KingCrabRender extends DivineRender<KingCrab, CrabModel<KingCrab>> {
    public KingCrabRender(EntityRendererManager manager) {
        super(manager, new CrabModel<>(2), 0, "crab");
    }
}
