package divinerpg.blocks.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class DivineOre extends OreBlock {
    protected final boolean isSmelted;

    public DivineOre(Properties properties, boolean isSmelted) {
        super(properties);
        this.isSmelted = isSmelted;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return (silktouch == 0 && !isSmelted) ? MathHelper.nextInt(this.RANDOM, 2, 6) : 0;
    }
}
