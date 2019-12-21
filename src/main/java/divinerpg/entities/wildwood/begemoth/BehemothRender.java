package divinerpg.entities.wildwood.begemoth;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BehemothRender extends DivineRender<Behemoth, BehemothModel> {
    public BehemothRender(EntityRendererManager manager) {
        super(manager, new BehemothModel(), 0, "behemoth");
    }
}
