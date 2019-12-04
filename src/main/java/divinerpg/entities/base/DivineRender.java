package divinerpg.entities.base;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DivineRender<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private final ResourceLocation location;
    private final Float scale;

    public DivineRender(EntityRendererManager manager, M model, float shadow, String name) {
        this(manager, model, shadow, name, null);

        if (model instanceof IHasArm)
            this.addLayer(new HeldItemLayer(this));
    }

    public DivineRender(EntityRendererManager manager, M model, float shadow, String name, Float scale) {
        super(manager, model, shadow);
        location = createFromName(name);
        this.scale = scale;
    }

    protected ResourceLocation createFromName(String name) {
        return new ResourceLocation(DivineRPG.MODID, String.format("textures/entity/%s.png", name));
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
}
