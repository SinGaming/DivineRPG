package divinerpg.entities.vanilla.crawler;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.vanilla.crawler.desert.DesertCrawler;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CrawlerRender<T extends DesertCrawler> extends DivineRender<T, CrawlerModel<T>> {
    public CrawlerRender(EntityRendererManager manager) {
        super(manager, new CrawlerModel<>(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return CachedTexture.ENTITIES.getTexture(entity.getType().getRegistryName().getPath());
    }
}
