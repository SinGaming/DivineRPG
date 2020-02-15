package divinerpg.entities.base.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class DivineRender<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> implements IRenderDeobf<T> {
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
            this.addLayer(new HeldItemLayer<T, M>(this));
        }
    }

    protected ResourceLocation createFromName(String name) {
        return CachedTexture.ENTITIES.getTexture(name);
    }

    @Override
    protected void func_225620_a_(T p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        preRenderCallback(p_225620_1_, p_225620_2_, p_225620_3_);
    }

    @Override
    public void preRenderCallback(T entitylivingbaseIn, MatrixStack stack, float partialTickTime) {
        if (scale != null) {
            DeobfHelper.scale(stack, this.scale, this.scale, this.scale);
        }

        super.func_225620_a_(entitylivingbaseIn, stack, partialTickTime);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(T entity) {
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
