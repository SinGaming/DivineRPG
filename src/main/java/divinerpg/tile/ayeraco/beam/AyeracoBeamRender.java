package divinerpg.tile.ayeraco.beam;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;

public class AyeracoBeamRender extends TileEntityRenderer<AyeracoBeam> {
    private float[] white = DyeColor.WHITE.getColorComponentValues();

    public AyeracoBeamRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void func_225616_a_(AyeracoBeam tileEntityIn, float partialTicks, MatrixStack stack,
                               IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        ResourceLocation texture = CachedTexture.BLOCKS.getTexture(String.format("beam_%s", tileEntityIn.getColor()));
        long worldTime = tileEntityIn.getWorld().getGameTime();

        BeaconTileEntityRenderer.func_228842_a_(stack, bufferIn, texture, partialTicks,
                // texturescale, same as Beacon
                1,
                worldTime,
                // yOffset
                0,
                // beamHeight
                1024,
                // colors
                white,
                // beamRadius
                0.2F,
                // glowRadius
                0.25F);

    }

    @Override
    public boolean isGlobalRenderer(AyeracoBeam te) {
        return true;
    }
}
