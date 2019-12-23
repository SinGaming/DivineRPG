package divinerpg.tile.statue;

import divinerpg.blocks.twilight.StatueBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.HashMap;
import java.util.Map;

public class ItemStackStatueRender extends ItemStackTileEntityRenderer {
    private final Map<String, TileEntity> cache = new HashMap<>();

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        Item item = itemStackIn.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof StatueBlock) {
                TileEntity tileEntity = cache.computeIfAbsent(((StatueBlock) block).getName(), TileEntityStatue::new);
                TileEntityRendererDispatcher.instance.renderAsItem(tileEntity);
                return;
            }
        }

        super.renderByItem(itemStackIn);
    }
}
