package divinerpg.entities.vanilla.crab;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class CrabRender extends DivineRender<Crab, CrabModel> {
    public CrabRender(EntityRendererManager manager) {
        super(manager, new CrabModel(), 0, "crab");
    }
}
