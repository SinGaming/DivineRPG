package divinerpg.entities.eden.madivel;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class MadivelRender extends DivineRender<Madivel, MadivelModel> {
    public MadivelRender(EntityRendererManager manager) {
        super(manager, new MadivelModel(), 0, "madivel");
    }
}
