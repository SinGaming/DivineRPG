package divinerpg.dimensions.vethea;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class VetheaChunk implements IVetheaChunk {

    public Chunk chunk;

    public VetheaChunk(Chunk chunkIn) {
        this.chunk = chunkIn;
    }

    @Override
    public void setBlock(int x, int y, int z, Block b) {
        chunk.setBlockState(new BlockPos(x, y, z), b.getDefaultState());
    }

    public Block getBlock(int x, int y, int z) {
        return chunk.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public Chunk getChunk() {
        return this.chunk;
	}
}
