package divinerpg.entities.skythern.samek;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SamekRender extends DivineRender<Samek, SamekModel> {
    public SamekRender(EntityRendererManager manager) {
        super(manager, new SamekModel(), 0, "samek");
    }
}
