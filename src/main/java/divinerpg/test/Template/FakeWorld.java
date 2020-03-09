package divinerpg.test.Template;

import divinerpg.dimensions.vethea.IVetheaChunk;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class FakeWorld extends World implements IVetheaChunk {
    /**
     * Blocks position
     */
    private final Map<BlockPos, IBlockState> blockMap = new HashMap<>();

    /**
     * Tile entities position
     */
    private final Map<BlockPos, TileEntity> tileMap = new HashMap<>();

    /**
     * Entities position
     */
    private final Map<BlockPos, Entity> entityMap = new HashMap<>();

    /**
     * List of chunks
     */
    private final HashSet<ChunkPos> chunks = new HashSet<>();

    /**
     * Mock object using for WordGenerator to remeber actual changes
     */
    public FakeWorld() {
        super(null, null, new FakeWorldProvider(), null, false);

        // random seed value
        rand.setSeed(rand.hashCode());
    }

    /**
     * Creates new Iterator for chunk size 16*16 with current offet
     *
     * @return
     */
    public Iterator<BlockPos> getPosIterator(ChunkPos chunkPos) {

        int offsetX = chunkPos.getXStart();
        int offsetZ = chunkPos.getZStart();

        int xMax = chunkPos.getXEnd();
        int zMax = chunkPos.getZEnd();

        Stream<BlockPos> blockPosStream = blockMap.keySet().stream().filter(pos -> isMatch(pos, offsetX, offsetZ, xMax, zMax));
        Stream<BlockPos> tilePosStream = tileMap.keySet().stream().filter(pos -> isMatch(pos, offsetX, offsetZ, xMax, zMax));
        Stream<BlockPos> entityPosStream = entityMap.keySet().stream().filter(pos -> isMatch(pos, offsetX, offsetZ, xMax, zMax));

        return Stream.concat(Stream.concat(blockPosStream, tilePosStream), entityPosStream).iterator();
    }

    /**
     * Returns possible chunk iterators
     *
     * @return
     */
    public Iterator<ChunkPos> getChunkIterator() {
        return chunks.iterator();
    }

    public Entity getEntity(BlockPos pos) {
        return entityMap.get(pos);
    }

    @Override
    public boolean setBlockState(BlockPos pos, IBlockState newState, int flags) {
        pos = floor(pos);

        if (this.isOutsideBuildHeight(pos)) {
            System.out.println("Wrong block position");
            return false;
        }

        blockMap.put(pos, newState);

        if (newState.getBlock() instanceof ITileEntityProvider) {
            TileEntity entity = ((ITileEntityProvider) newState.getBlock()).createNewTileEntity(this, 0);
            entity.setPos(pos);

            addTileEntity(entity);
        }

        chunks.add(new ChunkPos(pos));
        return true;
    }

    @Override
    public IBlockState getBlockState(BlockPos pos) {
        pos = floor(pos);

        if (this.isOutsideBuildHeight(pos) || !blockMap.containsKey(pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return blockMap.get(pos);
        }
    }

    @Override
    public boolean addTileEntity(TileEntity tile) {
        tileMap.put(floor(tile.getPos()), tile);
        return true;
    }

    @Nullable
    @Override
    public TileEntity getTileEntity(BlockPos pos) {
        if (this.isOutsideBuildHeight(pos)) {
            return null;
        }

        return tileMap.get(floor(pos));
    }

    @Override
    public boolean spawnEntity(Entity entityIn) {
        entityMap.put(floor(entityIn.getPosition()), entityIn);
        return true;
    }

    @Override
    protected IChunkProvider createChunkProvider() {
        return null;
    }

    @Override
    protected boolean isChunkLoaded(int i, int i1, boolean b) {
        return false;
    }

    private BlockPos floor(BlockPos pos) {
        return new BlockPos(
                Math.floor(pos.getX()),
                Math.floor(pos.getY()),
                Math.floor(pos.getZ())
        );
    }

    private boolean isMatch(BlockPos pos, int x, int z, int maxX, int maxZ) {
        return x <= pos.getX() && pos.getX() <= maxX
                &&
                z <= pos.getZ() && pos.getZ() <= maxZ;
    }

    @Override
    public void setBlock(int x, int y, int z, Block b) {
        setBlockState(new BlockPos(x, y, z), b.getDefaultState());
    }
}
