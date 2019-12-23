package divinerpg.entities.vanilla.scorcher;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class ScorcherRender extends DivineRender<Scorcher, ScorcherModel> {
    public ScorcherRender(EntityRendererManager manager) {
        super(manager, new ScorcherModel(), 0, "scorcher", 1.8F);
    }
}
