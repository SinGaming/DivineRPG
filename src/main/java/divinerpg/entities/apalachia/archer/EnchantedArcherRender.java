package divinerpg.entities.apalachia.archer;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EnchantedArcherRender extends DivineRender<EnchantedArcher, EnchantedArcherModel> {
    public EnchantedArcherRender(EntityRendererManager manager) {
        super(manager, new EnchantedArcherModel(), 0, "enchanted_archer");
    }
}
