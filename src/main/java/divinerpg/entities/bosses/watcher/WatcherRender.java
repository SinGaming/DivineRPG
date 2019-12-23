package divinerpg.entities.bosses.watcher;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WatcherRender extends DivineRender<DivineBoss, WatcherModel> {
    public WatcherRender(EntityRendererManager manager) {
        super(manager, new WatcherModel(), 0, "the_watcher", 4.5F);
    }
}
