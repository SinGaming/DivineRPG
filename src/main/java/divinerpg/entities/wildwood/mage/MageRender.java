package divinerpg.entities.wildwood.mage;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class MageRender extends DivineRender<Mage, MageModel> {
    public MageRender(EntityRendererManager manager) {
        super(manager, new MageModel(), 0, "mage");
    }
}
