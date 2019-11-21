package divinerpg.entities.vanilla.dramcryx.enthralled;

import divinerpg.DivineRPG;
import divinerpg.entities.vanilla.dramcryx.DramcryxModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class EnthralledDramcryxRender extends MobRenderer<EnthralledDramcryx, DramcryxModel<EnthralledDramcryx>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/enthralled_dramcryx.png");

    public EnthralledDramcryxRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DramcryxModel<>(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EnthralledDramcryx entity) {
        return TEXTURE;
    }
}