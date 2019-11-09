package divinerpg.entities.render;

import divinerpg.entities.projectiles.DivineArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DivineEntityRender extends ArrowRenderer<DivineArrowEntity> {

    public DivineEntityRender(EntityRendererManager manager) {
        super(manager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(DivineArrowEntity entity) {
        return entity.getTexture();
    }
}
