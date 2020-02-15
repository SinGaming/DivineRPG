package divinerpg.entities.projectiles.DivineArrow;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DivineArrowRender extends ArrowRenderer<DivineArrow> {

    public DivineArrowRender(EntityRendererManager manager) {
        super(manager);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(DivineArrow entity) {
        return entity.getTexture();
    }
}
