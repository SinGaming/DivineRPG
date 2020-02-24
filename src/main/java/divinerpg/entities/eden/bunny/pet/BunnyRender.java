package divinerpg.entities.eden.bunny.pet;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.entities.base.render.DeobfHelper;
import divinerpg.entities.base.render.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class BunnyRender extends DivineRender<Bunny, BunnyModel> {
    public BunnyRender(EntityRendererManager manager) {
        super(manager, new BunnyModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(Bunny entity) {
        if (entity.isTamed()) {
            return entity.isAggressive()
                    // texture is jumping from aggressive to regular.
                    // I think it is because of revenge goal
                    ? CachedTexture.ENTITIES.getTexture("tamed_angry_bunny")
                    : CachedTexture.ENTITIES.getTexture("tamed_bunny");
        }

        return CachedTexture.ENTITIES.getTexture("bunny");
    }

    @Override
    public void preRenderCallback(Bunny bunny, MatrixStack stack, float partialTickTime) {
        super.preRenderCallback(bunny, stack, partialTickTime);

        if (bunny.isTamed() && bunny.isAggressive()) {
            DeobfHelper.scale(stack, 1.2F, 1.2F, 1.2F);
        }
    }
}
