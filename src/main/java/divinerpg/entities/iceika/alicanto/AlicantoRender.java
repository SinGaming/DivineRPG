package divinerpg.entities.iceika.alicanto;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AlicantoRender extends DivineRender<Alicanto, AlicantoModel> {
    public AlicantoRender(EntityRendererManager manager) {
        super(manager, new AlicantoModel(), 0, "alicanto");
    }
}
