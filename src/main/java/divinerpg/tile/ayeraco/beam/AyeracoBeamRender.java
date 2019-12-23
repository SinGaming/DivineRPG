package divinerpg.tile.ayeraco.beam;

import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;

public class AyeracoBeamRender extends TileEntityRenderer<AyeracoBeam> {
    private float[] white = DyeColor.WHITE.getColorComponentValues();

    @Override
    public void render(AyeracoBeam tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        this.bindTexture(tileEntityIn);
        // TODO black beam
        BeaconTileEntityRenderer.renderBeamSegment(x, y, z, partialTicks, 1, tileEntityIn.getWorld().getGameTime(),
                0, 1024, white, 0.2D, 0.25D);
    }


    private void bindTexture(AyeracoBeam te) {
        ResourceLocation texture = CachedTexture.BLOCKS.getTexture(String.format("beam_%s", te.getColor()));
        this.bindTexture(texture);
    }

    @Override
    public boolean isGlobalRenderer(AyeracoBeam te) {
        return true;
    }
}
