package divinerpg.blocks.base;

import divinerpg.utils.properties.block.ExtendedBlockProperties;
import divinerpg.utils.properties.block.IExpDrop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.Consumer;

/**
 * Custom ore block. Have customizable exp drop and entity collision callback
 */
public class DivineOre extends OreBlock {
    protected static final VoxelShape small = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape taller = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    private final IExpDrop drop;
    private final Consumer<Entity> onCollision;

    public DivineOre(ExtendedBlockProperties props) {
        super(props.props.harvestTool(ToolType.PICKAXE));
        drop = props.drop;
        onCollision = props.onCollision;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return drop == null
                ? super.getExpDrop(state, reader, pos, fortune, silktouch)
                : drop.getExp(this.RANDOM, fortune, silktouch);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (onCollision != null) {
            onCollision.accept(entityIn);
        }
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return onCollision == null
                ? super.getCollisionShape(state, worldIn, pos, context)
                : small;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return onCollision == null
                ? super.getShape(state, worldIn, pos, context)
                : taller;
    }
}
