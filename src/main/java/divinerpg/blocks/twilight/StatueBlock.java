package divinerpg.blocks.twilight;

import divinerpg.tile.statue.TileEntityStatue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class StatueBlock extends Block {
    private final String name;

    public StatueBlock(String name) {
        super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(6).harvestTool(ToolType.PICKAXE).harvestLevel(0));
        this.name = name;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getBlock() == this;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityStatue(name);
    }
}
