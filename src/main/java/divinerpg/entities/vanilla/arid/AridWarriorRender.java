package divinerpg.entities.vanilla.arid;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AridWarriorRender extends DivineRender<AridWarrior, AridWarriorModel> {
    public AridWarriorRender(EntityRendererManager manager) {
        super(manager, new AridWarriorModel(), 0, "arid_warrior");
    }
}
