package divinerpg.entities.base.render;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DivineRender<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation location;
    private final Float scale;

    public DivineRender(EntityRendererManager manager, M model, float shadow, String name) {
        this(manager, model, shadow, name, null);
    }

    public DivineRender(EntityRendererManager manager, M model, float shadow, String name, Float scale) {
        super(manager, model, shadow);
        location = createFromName(name);
        this.scale = scale;

        if (model instanceof IHasArm)
            this.addLayer(new HeldItemLayer(this));

        if (model instanceof IHasArms) {
            this.addLayer(new HeldItemsLayer(this));
        }
    }

    protected ResourceLocation createFromName(String name) {
        return CachedTexture.ENTITIES.getTexture(name);
    }

    @Override
    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
        if (scale != null) {
            GlStateManager.scalef(this.scale, this.scale, this.scale);
        }

        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return location;
    }

    /**
     * Uses registry name to search entity texture
     *
     * @param e - entity
     * @return finded texture or default (from ctor)
     */
    protected ResourceLocation textureBasedOnType(Entity e) {
        if (e != null) {
            ResourceLocation registryName = e.getType().getRegistryName();
            if (registryName != null) {
                return CachedTexture.ENTITIES.getTexture(registryName.getPath());
            }
        }

        return location;
    }
}
