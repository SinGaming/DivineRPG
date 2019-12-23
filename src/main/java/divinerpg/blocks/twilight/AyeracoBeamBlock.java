package divinerpg.blocks.twilight;

import divinerpg.tile.ayeraco.beam.AyeracoBeam;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.BossInfo;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class AyeracoBeamBlock extends ContainerBlock {
    private BossInfo.Color color;

    public AyeracoBeamBlock(BossInfo.Color color) {
        super(Block.Properties.create(Material.FIRE)
                .hardnessAndResistance(60000)
                .lightValue(0)
                .variableOpacity()
                .noDrops());

        this.color = color;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new AyeracoBeam(color);
    }
}
