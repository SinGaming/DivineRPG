package divinerpg.entities.base.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.LivingEntity;

public interface IRenderDeobf<T extends LivingEntity> {

    void preRenderCallback(T entitylivingbaseIn, MatrixStack stack, float partialTickTime);
}
