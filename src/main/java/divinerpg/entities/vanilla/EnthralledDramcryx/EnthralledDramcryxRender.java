package divinerpg.entities.vanilla.EnthralledDramcryx;

import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class EnthralledDramcryxRender extends MobRenderer<EnthralledDramcryx, EnthralledDramcryxModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/enthralled_dramcryx.png");

    public EnthralledDramcryxRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EnthralledDramcryxModel(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EnthralledDramcryx entity) {
        return TEXTURE;
    }
}