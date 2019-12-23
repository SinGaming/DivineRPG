package divinerpg.entities.vanilla.bat;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BatModel;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class DivineBatRender extends DivineRender<BatEntity, BatModel> {
    public DivineBatRender(EntityRendererManager manager) {
        super(manager, new BatModel(), 0, "", 0.7F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(BatEntity entity) {
        return textureBasedOnType(entity);
    }

    protected void applyRotations(BatEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.getIsBatHanging()) {
            GlStateManager.translatef(0.0F, -0.1F, 0.0F);
        } else {
            GlStateManager.translatef(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}
