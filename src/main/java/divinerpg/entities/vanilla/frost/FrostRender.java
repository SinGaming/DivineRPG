package divinerpg.entities.vanilla.frost;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class FrostRender extends DivineRender<Frost, FrostModel> {
    public FrostRender(EntityRendererManager manager) {
        super(manager, new FrostModel(), 0, "frost");
    }
}
