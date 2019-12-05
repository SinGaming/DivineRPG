package divinerpg.entities.vanilla.bat;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BatModel;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class DivineBatRender extends DivineRender<BatEntity, BatModel> {
    private ResourceLocation location;

    public DivineBatRender(EntityRendererManager manager) {
        super(manager, new BatModel(), 0, "", 0.7F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(BatEntity entity) {
        if (location == null) {
            location = createFromName(entity.getType().getRegistryName().getPath());
        }

        return location;
    }

//    @Override
//    protected void preRenderCallback(BatEntity bat, float partialTickTime) {
//        // Base values are taken from super class
//
//        float scale = 0.35F;
//        float batWidth = 0.5F;
//        float batHeight = 0.9F;
//
//        // Get active
//        EntitySize size = bat.getSize(Pose.STANDING);
//
//        float xScale = size.width / batWidth * baseBatScale;
//        float yScale = size.height / batHeight * baseBatScale;
//
//        GlStateManager.scalef(xScale, yScale, xScale);
//    }

    protected void applyRotations(BatEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.getIsBatHanging()) {
            GlStateManager.translatef(0.0F, -0.1F, 0.0F);
        } else {
            GlStateManager.translatef(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}
