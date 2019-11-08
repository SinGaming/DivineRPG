package divinerpg.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ITextured {
    /**
     * Returns the texture of object
     */
    @OnlyIn(Dist.CLIENT)
    ResourceLocation getTexture();
}
