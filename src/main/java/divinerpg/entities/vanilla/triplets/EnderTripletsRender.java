package divinerpg.entities.vanilla.triplets;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EnderTripletsRender extends DivineRender<EnderTriplets, EnderTripletsModel> {

    public EnderTripletsRender(EntityRendererManager manager) {
        super(manager, new EnderTripletsModel(), 0, "ender_triplets");
    }
}
