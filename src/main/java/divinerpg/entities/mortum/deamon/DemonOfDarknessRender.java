package divinerpg.entities.mortum.deamon;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DemonOfDarknessRender extends DivineRender<DemonOfDarkness, DemonOfDarknessModel> {
    public DemonOfDarknessRender(EntityRendererManager manager) {
        super(manager, new DemonOfDarknessModel(), 0, "demon_of_darkness");
    }
}
