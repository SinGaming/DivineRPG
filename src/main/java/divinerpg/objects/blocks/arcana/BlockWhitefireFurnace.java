package divinerpg.objects.blocks.arcana;

import divinerpg.objects.blocks.BlockModFurnace;
import divinerpg.objects.blocks.tile.entity.TileEntityWhitefireFurnace;
import divinerpg.proxy.GUIHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWhitefireFurnace extends BlockModFurnace implements ITileEntityProvider {

    public BlockWhitefireFurnace(String name, boolean isBurning) {
        super(name, isBurning);
    }

    public int getGuiID() {
        return GUIHandler.WHITEFIRE_FURNACE_GUI_ID;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWhitefireFurnace();
    }
}
