package divinerpg.entities.mortum.sorcerer;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SorcererRender extends DivineRender<Sorcerer, SorcererModel> {
    public SorcererRender(EntityRendererManager manager) {
        super(manager, new SorcererModel(), 0, "sorcerer");
    }
}
