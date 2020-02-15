package divinerpg.entities.iceika.merchant;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.iceika.merchant.entities.WorkshopMerchant;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class WorkshopMerchantRender extends DivineRender<WorkshopMerchant, WorkshopMerchantModel> {
    public WorkshopMerchantRender(EntityRendererManager manager) {
        super(manager, new WorkshopMerchantModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(WorkshopMerchant entity) {
        return textureBasedOnType(entity);
    }
}
