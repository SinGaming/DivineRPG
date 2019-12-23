package divinerpg.entities.skythern.megalith;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class MegalithRender extends DivineRender<Megalith, MegalithModel> {
    public MegalithRender(EntityRendererManager manager) {
        super(manager, new MegalithModel(), 0, "megalith");
    }
}
