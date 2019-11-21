package divinerpg.entities.vanilla.dramcryx.jungle;

import divinerpg.DivineRPG;
import divinerpg.entities.vanilla.dramcryx.DramcryxModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class JungleDramcryxRender extends MobRenderer<JungleDramcryx, DramcryxModel<JungleDramcryx>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/jungle_dramcryx.png");

    public JungleDramcryxRender(EntityRendererManager manager) {
        super(manager, new DramcryxModel<>(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(JungleDramcryx entity) {
        return TEXTURE;
    }
}
