package divinerpg.entities.vanilla.spider.ender;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SpiderModel;

public class EnderSpiderRender extends DivineRender<EnderSpider, SpiderModel<EnderSpider>> {

    public EnderSpiderRender(EntityRendererManager manager) {
        super(manager, new SpiderModel<>(), 0, "ender_spider", .5F);
    }
}
