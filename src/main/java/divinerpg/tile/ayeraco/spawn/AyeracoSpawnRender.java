package divinerpg.tile.ayeraco.spawn;

import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class AyeracoSpawnRender extends TileEntityRenderer<AyeracoSpawn> {

    @Override
    public void render(AyeracoSpawn tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);

        long ticks = getWorld().getGameTime();

        GL11.glPushMatrix();
        bindTexture(tileEntityIn);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.01f, (float) z + 1.0625f);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(2, 2, 2);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        float minU = 0;
        float maxU = 1;
        float minV = 0;
        float maxV = 1;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, -0.28125f);
        GL11.glRotatef(ticks * 4, 0F, 1F, 0F);
        GL11.glTranslatef(0, 0, 0.28125f);
        GL11.glRotatef(270, 1F, 0F, 0F);
        com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, 0, 65536);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(0.0F - f8, 0.0F - f9, 0.0D).tex(minU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(f7 - f8, 0.0F - f9, 0.0D).tex(maxU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(f7 - f8, 1.0F - f9, 0.0D).tex(maxU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.0F - f8, 1.0F - f9, 0.0D).tex(minU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.001f, -0.28125f);
        GL11.glRotatef(ticks * -4, 0F, 1F, 0F);
        GL11.glTranslatef(0, 0, 0.28125f);
        GL11.glRotatef(270, 1F, 0F, 0F);

        com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, 0, 65536);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(0.0F - f8, 0.0F - f9, 0.0D).tex(minU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(f7 - f8, 0.0F - f9, 0.0D).tex(maxU, maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(f7 - f8, 1.0F - f9, 0.0D).tex(maxU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.0F - f8, 1.0F - f9, 0.0D).tex(minU, minV).normal(0.0F, 1.0F, 0.0F).endVertex();
        tessellator.draw();

        GL11.glPopMatrix();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }


    private void bindTexture(AyeracoSpawn tile) {
        ResourceLocation texture = CachedTexture.MODEL.getTexture(tile.genSummoningColorName() + "_ayeraco_symbol");
        bindTexture(texture);
    }
}
