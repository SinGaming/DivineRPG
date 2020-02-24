package divinerpg.entities.iceika.archer;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class FrostArcherRender extends DivineRender<FrostArcher, BipedModel<FrostArcher>> {
    public FrostArcherRender(EntityRendererManager manager) {
        super(manager, new BipedModel<>(0), 1, "frost_archer");
    }
}
