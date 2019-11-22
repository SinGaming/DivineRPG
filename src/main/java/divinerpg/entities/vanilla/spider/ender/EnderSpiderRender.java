package divinerpg.entities.vanilla.spider.ender;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EnderSpiderRender extends DivineRender<EnderSpider, EnderSpiderModel> {

    public EnderSpiderRender(EntityRendererManager manager) {
        super(manager, new EnderSpiderModel(), 0, "ender_spider");
    }
}
