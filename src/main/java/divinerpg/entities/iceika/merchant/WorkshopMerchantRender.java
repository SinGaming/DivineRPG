package divinerpg.entities.iceika.merchant;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WorkshopMerchantRender extends DivineRender<WorkshopMerchant, WorkshopMerchantModel> {
    public WorkshopMerchantRender(EntityRendererManager manager) {
        super(manager, new WorkshopMerchantModel(), 0, "workshop_merchant");
    }
}
