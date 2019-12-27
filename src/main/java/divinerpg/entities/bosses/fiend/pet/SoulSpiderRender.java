package divinerpg.entities.bosses.fiend.pet;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SoulSpiderRender extends DivineRender<SoulSpider, SoulSpiderModel> {
    public SoulSpiderRender(EntityRendererManager manager) {
        super(manager, new SoulSpiderModel(), 0, "soul_spider");
    }
}
