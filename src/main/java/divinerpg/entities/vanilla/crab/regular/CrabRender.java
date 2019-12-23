package divinerpg.entities.vanilla.crab.regular;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.vanilla.crab.CrabModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class CrabRender extends DivineRender<Crab, CrabModel<Crab>> {
    public CrabRender(EntityRendererManager manager) {
        super(manager, new CrabModel<>(), 0, "crab");
    }
}
