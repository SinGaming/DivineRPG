package divinerpg.blocks.twilight;

import net.minecraft.block.GlassBlock;
import net.minecraft.util.BlockRenderLayer;

public class ImprovedGlass extends GlassBlock {
    public ImprovedGlass(Properties properties) {
        super(properties);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
