package divinerpg.tile.ayeraco.beam;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;

public class AyeracoBeamRender extends TileEntityRendererFast<AyeracoBeam> {

    @Override
    public void renderTileEntityFast(AyeracoBeam te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer) {
        float textureScale = te.getScale();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.disableFog();
        bindTexture(te);
        if (textureScale > 0.0F) {
            GlStateManager.texParameter(3553, 10242, 10497);
            GlStateManager.texParameter(3553, 10243, 10497);
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            double height = 256.0D;
            double scaledHeight = height * (double) textureScale;
            double beamRadius = 0.2D;
            double glowRadius = 0.3D;
            double d0 = (double) te.getWorld().getGameTime() + partialTicks;
            double d1 = height < 0 ? d0 : -d0;
            double d2 = MathHelper.frac(d1 * 0.2D - (double) MathHelper.floor(d1 * 0.1D));
            float f = 1.0F; // colors[0];
            float f1 = 1.0F; // colors[1];
            float f2 = 1.0F; // colors[2];
            double d3 = d0 * 0.025D * -1.5D;
            double d4 = 0.5D + Math.cos(d3 + 2.356194490192345D) * beamRadius;
            double d5 = 0.5D + Math.sin(d3 + 2.356194490192345D) * beamRadius;
            double d6 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * beamRadius;
            double d7 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * beamRadius;
            double d8 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * beamRadius;
            double d9 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * beamRadius;
            double d10 = 0.5D + Math.cos(d3 + 5.497787143782138D) * beamRadius;
            double d11 = 0.5D + Math.sin(d3 + 5.497787143782138D) * beamRadius;
            double d12 = 0.0D;
            double d13 = 1.0D;
            double d14 = -1.0D + d2;
            double d15 = scaledHeight * (0.5D / beamRadius) + d14;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos(x + d4, y + scaledHeight, z + d5).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d4, y, z + d5).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d6, y, z + d7).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d6, y + scaledHeight, z + d7).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d10, y + scaledHeight, z + d11).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d10, y, z + d11).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d8, y, z + d9).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d8, y + scaledHeight, z + d9).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d6, y + scaledHeight, z + d7).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d6, y, z + d7).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d10, y, z + d11).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d10, y + scaledHeight, z + d11).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d8, y + scaledHeight, z + d9).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d8, y, z + d9).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d4, y, z + d5).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
            bufferbuilder.pos(x + d4, y + scaledHeight, z + d5).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
            tessellator.draw();
            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                    GlStateManager.DestFactor.ZERO);
            GlStateManager.depthMask(false);
            d3 = 0.5D - glowRadius;
            d4 = 0.5D - glowRadius;
            d5 = 0.5D + glowRadius;
            d6 = 0.5D - glowRadius;
            d7 = 0.5D - glowRadius;
            d8 = 0.5D + glowRadius;
            d9 = 0.5D + glowRadius;
            d10 = 0.5D + glowRadius;
            d11 = 0.0D;
            d12 = 1.0D;
            d13 = -1.0D + d2;
            d14 = height * textureScale + d13;
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos(x + d3, y + scaledHeight, z + d4).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d3, y, z + d4).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d5, y, z + d6).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d5, y + scaledHeight, z + d6).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d9, y + scaledHeight, z + d10).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d9, y, z + d10).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d7, y, z + d8).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d7, y + scaledHeight, z + d8).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d5, y + scaledHeight, z + d6).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d5, y, z + d6).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d9, y, z + d10).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d9, y + scaledHeight, z + d10).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d7, y + scaledHeight, z + d8).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d7, y, z + d8).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d3, y, z + d4).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
            bufferbuilder.pos(x + d3, y + scaledHeight, z + d4).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture();
            GlStateManager.depthMask(true);
        }
        GlStateManager.enableFog();
    }

    private void bindTexture(AyeracoBeam te) {
        ResourceLocation texture = CachedTexture.BLOCKS.getTexture(String.format("beam_%s", te.getColor()));
        this.bindTexture(texture);
    }
}
