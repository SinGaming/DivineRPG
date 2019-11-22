package divinerpg.entities.vanilla.dramcryx.jungle;

import divinerpg.entities.base.DivineRender;
import divinerpg.entities.vanilla.dramcryx.DramcryxModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class JungleDramcryxRender extends DivineRender<JungleDramcryx, DramcryxModel<JungleDramcryx>> {
    public JungleDramcryxRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DramcryxModel<>(), 0.5F, "jungle_dramcryx");
    }
}
