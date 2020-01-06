package divinerpg.entities.iceika.fractile;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class FractiteRender extends DivineRender<Fractite, FractiteModel> {
    public FractiteRender(EntityRendererManager manager) {
        super(manager, new FractiteModel(), 0.5F, "fractite", 2F);
    }
}
