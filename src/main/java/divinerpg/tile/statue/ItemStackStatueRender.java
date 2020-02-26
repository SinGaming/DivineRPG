package divinerpg.tile.statue;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.blocks.twilight.StatueBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
    public void func_228364_a_(ItemStack itemStackIn, MatrixStack stack, IRenderTypeBuffer buffer,
                               int combinedLightIn, int combinedOverlayIn) {
        Item item = itemStackIn.getItem();

        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();

            if (block instanceof StatueBlock) {
                // creating tileentity and caching it
                TileEntity tileEntity = cache.computeIfAbsent(((StatueBlock) block).getName(), TileEntityStatue::new);
                TileEntityRendererDispatcher.instance.func_228852_a_(tileEntity, stack, buffer,
                        combinedLightIn, combinedOverlayIn);

                return;
            }
        }

        super.func_228364_a_(itemStackIn, stack, buffer, combinedLightIn, combinedOverlayIn);
    }
}
