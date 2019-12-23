package divinerpg.entities.vanilla.spider.hell;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SpiderModel;

public class HellSpiderRender extends DivineRender<HellSpider, SpiderModel<HellSpider>> {
    public HellSpiderRender(EntityRendererManager manager) {
        super(manager, new SpiderModel<>(), 0, "hell_spider");
    }
}
