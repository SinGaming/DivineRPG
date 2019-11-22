package divinerpg.entities.vanilla.spider.jungle;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.spider.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class JungleSpiderRender extends DivineRender<JungleSpider, SpiderModel<JungleSpider>> {
    public JungleSpiderRender(EntityRendererManager manager) {
        super(manager, new SpiderModel<>(1), 0, "jungle_spider");
    }
}
