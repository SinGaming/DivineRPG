package divinerpg.tile.statue;

import divinerpg.blocks.twilight.StatueBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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
                RenderHelper.enableStandardItemLighting();
                TileEntityRendererDispatcher.instance.renderAsItem(tileEntity);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                return;
            }
        }

        super.renderByItem(itemStackIn);
    }
}
