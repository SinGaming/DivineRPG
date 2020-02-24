package divinerpg.entities.vanilla.cyclop.cave;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class CavelopsRender extends DivineRender<Cavelops, BipedModel<Cavelops>> {
    public CavelopsRender(EntityRendererManager manager) {
        super(manager, new BipedModel<>(0), 0, "caveclops", 2F);
    }

}
