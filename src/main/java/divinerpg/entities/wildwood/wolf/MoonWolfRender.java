package divinerpg.entities.wildwood.wolf;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;

public class MoonWolfRender extends MobRenderer<WolfEntity, WolfModel<WolfEntity>> {
    public MoonWolfRender(EntityRendererManager manager) {
        super(manager, new WolfModel<>(), 0.5F);
    }

    @Override
    protected float handleRotationFloat(WolfEntity livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    @Override
    public void doRender(WolfEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isWolfWet()) {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color3f(f, f, f);
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(WolfEntity entity) {
        return CachedTexture.ENTITIES.getTexture("moon_wolf");
    }
}
