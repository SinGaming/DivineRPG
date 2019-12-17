package divinerpg.entities.mortum.basilisk;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BasiliskRender extends DivineRender<Basilisk, BasiliskModel> {
    public BasiliskRender(EntityRendererManager manager) {
        super(manager, new BasiliskModel(), 0, "basilisk");
    }
}
