package divinerpg.entities.vanilla.spider.jungle;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.SpiderModel;

public class JungleSpiderRender extends DivineRender<JungleSpider, SpiderModel<JungleSpider>> {
    public JungleSpiderRender(EntityRendererManager manager) {
        super(manager, new SpiderModel<>(), 0, "jungle_spider");
    }
}
