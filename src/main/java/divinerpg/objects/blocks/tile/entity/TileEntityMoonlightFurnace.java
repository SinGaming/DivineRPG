package divinerpg.objects.blocks.tile.entity;

import divinerpg.objects.blocks.arcana.BlockMoonlightFurnace;

public class TileEntityMoonlightFurnace extends TileEntityModFurnace {

    public String getFuranceName() {
        return "tile.moonlight_furnace.name";
    }

    int getFurnaceSpeed() {
        return 100;
    }

    public boolean needsFuel() {
        return true;
    }

    public void updateBlockState(boolean isBurning) {
        BlockMoonlightFurnace.setState(isBurning, world, pos);
    }
}