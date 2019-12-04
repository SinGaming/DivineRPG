package divinerpg.entities.vanilla.crawler;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.crawler.desert.DesertCrawler;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class CrawlerRender<T extends DesertCrawler> extends DivineRender<T, CrawlerModel<T>> {
    public CrawlerRender(EntityRendererManager manager, String name) {
        super(manager, new CrawlerModel<>(), 0, name);
    }
}
