package divinerpg.tile.ayeraco.spawn;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.tile.base.DivineTileEntityRender;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class AyeracoSpawnRender extends DivineTileEntityRender<AyeracoSpawn> {

    public AyeracoSpawnRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(AyeracoSpawn entity, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        super.render(entity, partialTicks, stack, buffer, combinedLightIn, combinedOverlayIn);

        // TODO we need designer here
        // It's very difficult
        // The old code is below
    }

    //
//    @Override
//    public void render(AyeracoSpawn tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
//        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);
//
//        long ticks = getWorld().getGameTime();
//
//        GL11.glPushMatrix();
//        bindTexture(tileEntityIn);
//        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.01f, (float) z + 1.0625f);
//        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//        GL11.glScalef(2, 2, 2);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder bufferbuilder = tessellator.getBuffer();
//        float minU = 0;
//        float maxU = 1;
//        float minV = 0;
//        float maxV = 1;
//        float f7 = 1.0F;
//        float f8 = 0.5F;
//        float f9 = 0.25F;
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0, 0, -0.28125f);
//        GL11.glRotatef(ticks * 4, 0F, 1F, 0F);
//        GL11.glTranslatef(0, 0, 0.28125f);
//        GL11.glRotatef(270, 1F, 0F, 0F);
//        com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, 0, 65536);
//        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
//        bufferbuilder.pos(0.0F - f8, 0.0F - f9, 0.0D).tex(minU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(f7 - f8, 0.0F - f9, 0.0D).tex(maxU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(f7 - f8, 1.0F - f9, 0.0D).tex(maxU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(0.0F - f8, 1.0F - f9, 0.0D).tex(minU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        tessellator.draw();
//        GL11.glPopMatrix();
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0, 0.001f, -0.28125f);
//        GL11.glRotatef(ticks * -4, 0F, 1F, 0F);
//        GL11.glTranslatef(0, 0, 0.28125f);
//        GL11.glRotatef(270, 1F, 0F, 0F);
//
//        com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, 0, 65536);
//        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
//        bufferbuilder.pos(0.0F - f8, 0.0F - f9, 0.0D).tex(minU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(f7 - f8, 0.0F - f9, 0.0D).tex(maxU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(f7 - f8, 1.0F - f9, 0.0D).tex(maxU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        bufferbuilder.pos(0.0F - f8, 1.0F - f9, 0.0D).tex(minU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
//        tessellator.draw();
//
//        GL11.glPopMatrix();
//        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//        GL11.glPopMatrix();
//    }
//
//
//    private void bindTexture(AyeracoSpawn tile) {
//        ResourceLocation texture = CachedTexture.MODEL.getTexture(tile.genSummoningColorName() + "_ayeraco_symbol");
//        bindTexture(texture);
//    }
}
