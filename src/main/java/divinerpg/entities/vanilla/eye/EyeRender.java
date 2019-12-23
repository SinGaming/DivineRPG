package divinerpg.entities.vanilla.eye;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EyeRender extends DivineRender<Eye, EyeModel> {
    public EyeRender(EntityRendererManager manager) {
        super(manager, new EyeModel(), 0, "the_eye");
    }
}
