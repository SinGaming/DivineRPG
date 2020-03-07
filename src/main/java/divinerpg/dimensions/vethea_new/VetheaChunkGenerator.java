package divinerpg.dimensions.vethea_new;

import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.registry.ModBlocks;
import divinerpg.structure.DivineLargeStructure;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;

import javax.annotation.Nullable;
import java.util.*;

public class VetheaChunkGenerator implements IChunkGenerator {
    private final World world;
    private final Random rand;

    /**
     * Floor height
     */
    private final int floorHeight = 48;

    /**
     * Floors count
     */
    private final int totalFloors;

    /**
     * Height of blocksbeetween the levels
     */
    private final int roofHeight = 16;

    /**
     * Structures by level. Begginig with zero
     */
    private final Map<Integer, List<MapGenStructure>> structureByLevelMap = new HashMap<>();

    public VetheaChunkGenerator(World world) {
        this.world = world;
        this.rand = new Random(world.rand.nextLong());

        int floor = 0;

        for (int i = 0; i < world.getHeight(); i += floorHeight) {
            ArrayList<MapGenStructure> generators = new ArrayList<>();
            fillFloors(world, floor, generators);
            structureByLevelMap.put(floor, generators);

            floor++;
        }

        totalFloors = floor;
    }

    private void fillFloors(World world, int floor, List<MapGenStructure> floorGenerators) {
        switch (floor) {
            case 1:
                floorGenerators.add(new DivineLargeStructure(world,
                        "Hive",
                        new ResourceLocation(Reference.MODID, "hive"),
                        floor * floorHeight + roofHeight,
                        48));

                floorGenerators.add(new DivineLargeStructure(world,
                        "Pyramid1",
                        new ResourceLocation(Reference.MODID, "pyramid"),
                        floor * floorHeight + roofHeight,
                        48));
                break;
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed(rand.nextLong() ^ world.getSeed());
        ChunkPrimer chunkPrimer = new ChunkPrimer();

        for (int i = 0; i < totalFloors; i++) {
            genFloor(chunkPrimer, i * floorHeight);

            List<MapGenStructure> structures = structureByLevelMap.get(i);
            if (structures == null || structures.isEmpty())
                continue;

            structures.forEach(gen -> gen.generate(world, x, z, chunkPrimer));
        }

        Chunk chunk = new Chunk(this.world, chunkPrimer, x, z);
        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * Generating floor 17 blocks height. two top layers is grass and dirt
     *
     * @param chunkPrimer - chunk primer
     * @param y           - height of beginning of level
     */
    private void genFloor(ChunkPrimer chunkPrimer, int y) {
        int earthLevel = y + roofHeight;
        int dirtLevel = earthLevel - 1;
        int maxStoneLevel = dirtLevel - 1;

        int maxWidth = 15;


        // stone level (13)
        BlockPos.getAllInBoxMutable(0, y, 0, maxWidth, maxStoneLevel, maxWidth)
                .forEach(x -> chunkPrimer.setBlockState(x.getX(), x.getY(), x.getZ(), ModBlocks.dreamStone.getDefaultState()));

        // dirt level (1)
        BlockPos.getAllInBoxMutable(0, dirtLevel, 0, maxWidth, dirtLevel, maxWidth)
                .forEach(x -> chunkPrimer.setBlockState(x.getX(), x.getY(), x.getZ(), ModBlocks.dreamDirt.getDefaultState()));

        // earth level (1)
        BlockPos.getAllInBoxMutable(0, earthLevel, 0, maxWidth, earthLevel, maxWidth)
                .forEach(x -> chunkPrimer.setBlockState(x.getX(), x.getY(), x.getZ(), ModBlocks.dreamGrass.getDefaultState()));
    }

    @Override
    public void populate(int x, int z) {
        // togo some pretty regen
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);

        for (int i = 0; i < totalFloors; i++) {
            structureByLevelMap.get(i).forEach(gen -> gen.generateStructure(world, rand, new ChunkPos(x, z)));
        }
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        // do not need this
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiomeProvider().getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        // ignored
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        // todo replace?..
        return false;
    }
}
