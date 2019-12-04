package divinerpg.entities.vanilla.watcher.ender;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.watcher.WatcherModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EnderWatcherRender extends DivineRender<EnderWatcher, WatcherModel<EnderWatcher>> {
    public EnderWatcherRender(EntityRendererManager manager) {
        super(manager, new WatcherModel<>(), 0, "ender_watcher");
    }
}
