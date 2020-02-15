package divinerpg.entities.projectiles.Bullet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Renders entity bullet
 */
@OnlyIn(Dist.CLIENT)
public class BulletEntityRender extends EntityRenderer<Entity> {

    public BulletEntityRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof ITextured) {
            return ((ITextured) entity).getTexture();
        }

        // using this as default
        return CachedTexture.PROJECTILES.getTexture("halite_blitz");
    }

    @Override
    public void func_225623_a_(Entity entityIn, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn) {
        stack.func_227860_a_();
        //stack.func_227862_a_(2.0F, 2.0F, 2.0F);
        stack.func_227863_a_(this.renderManager.func_229098_b_());
        stack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
        MatrixStack.Entry entry = stack.func_227866_c_();
        Matrix4f var1 = entry.func_227870_a_();
        Matrix3f var2 = entry.func_227872_b_();
        IVertexBuilder builder = buffer.getBuffer(RenderType.func_228640_c_(getEntityTexture(entityIn)));
        func_229045_a_(builder, var1, var2, packedLightIn, 0.0F, 0, 0, 1);
        func_229045_a_(builder, var1, var2, packedLightIn, 1.0F, 0, 1, 1);
        func_229045_a_(builder, var1, var2, packedLightIn, 1.0F, 1, 1, 0);
        func_229045_a_(builder, var1, var2, packedLightIn, 0.0F, 1, 0, 0);
        stack.func_227865_b_();

        super.func_225623_a_(entityIn, entityYaw, partialTicks, stack, buffer, packedLightIn);
    }

    /**
     * Stolen from dragon fireball
     */
    private void func_229045_a_(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f, int packedLightIn, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
        builder.func_227888_a_(matrix4f, p_229045_4_ - 0.5F, (float) p_229045_5_ - 0.25F, 0.0F).func_225586_a_(255, 255, 255, 255).func_225583_a_((float) p_229045_6_, (float) p_229045_7_).func_227891_b_(OverlayTexture.field_229196_a_).func_227886_a_(packedLightIn).func_227887_a_(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
