package divinerpg.entities.apalachia.warrior;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class EnchantedWarriorRender extends DivineRender<EnchantedWarrior, EnchantedWarriorModel> {
    public EnchantedWarriorRender(EntityRendererManager manager) {
        super(manager, new EnchantedWarriorModel(), 0, "enchanted_warrior");
    }
}
