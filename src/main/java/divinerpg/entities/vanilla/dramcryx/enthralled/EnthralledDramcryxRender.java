package divinerpg.entities.vanilla.dramcryx.enthralled;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.vanilla.dramcryx.DramcryxModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnthralledDramcryxRender extends DivineRender<EnthralledDramcryx, DramcryxModel<EnthralledDramcryx>> {
    public EnthralledDramcryxRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DramcryxModel<>(), 0.5F, "enthralled_dramcryx");
    }
}