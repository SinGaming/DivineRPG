package divinerpg.entities.vanilla.spider.hell;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.spider.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class HellSpiderRender extends DivineRender<HellSpider, SpiderModel<HellSpider>> {
    public HellSpiderRender(EntityRendererManager manager) {
        super(manager, new SpiderModel<>(1), 0, "hell_spider");
    }
}
