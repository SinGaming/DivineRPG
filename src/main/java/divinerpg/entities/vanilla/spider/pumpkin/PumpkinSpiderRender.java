package divinerpg.entities.vanilla.spider.pumpkin;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class PumpkinSpiderRender extends DivineRender<PumpkinSpider, PumpkinSpiderModel> {
    public PumpkinSpiderRender(EntityRendererManager manager) {
        super(manager, new PumpkinSpiderModel(), 0, "pumpkin_spider");
    }
}
