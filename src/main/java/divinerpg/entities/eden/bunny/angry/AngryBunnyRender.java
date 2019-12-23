package divinerpg.entities.eden.bunny.angry;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AngryBunnyRender extends DivineRender<AngryBunny, AngryBunnyModel> {
    public AngryBunnyRender(EntityRendererManager manager) {
        super(manager, new AngryBunnyModel(), 0, "angry_bunny", 2F);
    }
}
