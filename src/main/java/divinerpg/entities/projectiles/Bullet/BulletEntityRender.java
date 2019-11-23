package divinerpg.entities.projectiles.Bullet;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * Renders entity bullet
 */
@OnlyIn(Dist.CLIENT)
public class BulletEntityRender extends EntityRenderer<Entity> {

    public BulletEntityRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    /**
     * Copied from dragon fireball
     */
    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float scale = 0.5F;

        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translatef((float) x, (float) y, (float) z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scalef(scale, scale, scale);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        tessellator.draw();
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof ITextured) {
            return ((ITextured) entity).getTexture();
        }

        // using this as default
        return CachedTexture.PROJECTILES.getTexture("halite_blitz");
    }

}
