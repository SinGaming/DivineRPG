package divinerpg.dimensions.vethea_new;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.dimensions.vethea.WorldGenVetheanFlower;
import divinerpg.dimensions.vethea.village.WorldGenVillageIsland;
import divinerpg.registry.ModBlocks;
import divinerpg.structure.DivineLargeStructure;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

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

    /**
     * Custom generators by level.
     * Contains map with own gen possibility
     */
    private final Map<Integer, Multimap<Integer, WorldGenerator>> worldGenByLevelMap = new HashMap<>();


    private final WorldGenVillageIsland village = new WorldGenVillageIsland();
    private final List<Template> floatingTrees = new ArrayList<>();
    private final WorldGenVetheanPillarNew pillar = new WorldGenVetheanPillarNew();


    public VetheaChunkGenerator(World world) {
        this.world = world;
        this.rand = new Random(world.getSeed());

        int floor = 0;

        for (int i = 0; i < world.getHeight(); i += floorHeight) {
            ArrayList<MapGenStructure> structures = new ArrayList<>();
            Multimap<Integer, WorldGenerator> generators = LinkedHashMultimap.create();
            fillFloors(world, floor, structures, generators);
            structureByLevelMap.put(floor, structures);
            worldGenByLevelMap.put(floor, generators);

            floor++;
        }

        totalFloors = floor;

        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();

        for (int i = 0; i < 7; i++) {
            floatingTrees.add(manager.getTemplate(null, new ResourceLocation(Reference.MODID,
                    String.format("vethea/floating_tree/floatingtree%s", i + 1))));
        }
    }

    private void fillFloors(World world, int floor, ArrayList<MapGenStructure> structures,
                            Multimap<Integer, WorldGenerator> generators) {

        // todo InfusionOutpost for all levels
        // TODO add decorations

        generators.put(7 * 6, new WorldGenMinable(ModBlocks.fireCrystal.getDefaultState(),
                50,
                BlockStateMatcher.forBlock(ModBlocks.dreamGrass)));

        generators.put(2 * 6, new WorldGenMinable(ModBlocks.fireCrystal.getDefaultState(),
                45,
                BlockStateMatcher.forBlock(ModBlocks.dreamGrass)));

        generators.put(6, new WorldGenMinable(ModBlocks.fireCrystal.getDefaultState(),
                20,
                BlockStateMatcher.forBlock(ModBlocks.dreamGrass)));

        switch (floor) {
            case 0:
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.greenGemtop));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.purpleGemtop));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.yellowDulah));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.greenDulah));

                structures.add(new DivineLargeStructure(world,
                        "Crypt1",
                        createForVethea(floor, "crypt1"),
                        floor * floorHeight + 13,
                        40));

                structures.add(new DivineLargeStructure(world,
                        "Crypt2",
                        createForVethea(floor, "crypt2"),
                        floor * floorHeight + 13,
                        40));

                break;

            case 1:
                structures.add(new DivineLargeStructure(world,
                        "Hive",
                        createForVethea(floor, "hive"),
                        floor * floorHeight + roofHeight,
                        25));

                structures.add(new DivineLargeStructure(world,
                        "Pyramid1",
                        createForVethea(floor, "pyramid"),
                        floor * floorHeight + roofHeight,
                        40));

                structures.add(new DivineLargeStructure(world,
                        "Pyramid2",
                        createForVethea(floor, "pyramid2"),
                        floor * floorHeight + roofHeight,
                        40));

                generators.put(5, new WorldGenVetheanFlower(ModBlocks.fernite));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.dreamglow));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.shimmer));

                break;

            case 2:
                // todo fix
//                structures.add(new DivineLargeStructure(world,
//                        "QuadroticPost",
//                        createForVethea(floor, "quadroticpost"),
//                        floor * floorHeight + roofHeight,
//                        40));


//                structures.add(new DivineLargeStructure(world,
//                        "KarosMadhouse",
//                        createForVethea(floor, "karosmadhouse"),
//                        floor * floorHeight + roofHeight,
//                        40));

                generators.put(5, new WorldGenVetheanFlower(ModBlocks.shineGrass));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.cracklespike));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.bulbatobe));

                break;

            case 3:
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.shimmer));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.shineGrass));
                generators.put(5, new WorldGenVetheanFlower(ModBlocks.dreamglow));


                structures.add(new DivineLargeStructure(world,
                        "Evergarden",
                        createForVethea(floor, "evergarden"),
                        floor * floorHeight + roofHeight,
                        30));

                structures.add(new DivineLargeStructure(world,
                        "4Tree1",
                        createForVethea(floor, "layer4tree1"),
                        floor * floorHeight + roofHeight,
                        25));

                structures.add(new DivineLargeStructure(world,
                        "4Tree2",
                        createForVethea(floor, "layer4tree2"),
                        floor * floorHeight + roofHeight,
                        25));

                structures.add(new DivineLargeStructure(world,
                        "RaglokChamber",
                        createForVethea(floor, "raglokchamber"),
                        floor * floorHeight + roofHeight,
                        30));

                structures.add(new DivineLargeStructure(world,
                        "WreckHall",
                        createForVethea(floor, "wreckhall"),
                        floor * floorHeight + roofHeight,
                        30));

                break;
        }
    }

    private ResourceLocation createForVethea(int level, String structureFolder) {
        return new ResourceLocation(Reference.MODID, String.format("vethea/%slevel/%s", level, structureFolder));
    }

    @Override
    public Chunk generateChunk(int x, int z) {
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
     * Generating floor betwen floors. two top layers is grass and dirt
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

        // rand.setSeed(x * 10387319 + z * 10387319);

        ChunkPos chunkPos = new ChunkPos(x, z);

        for (int i = 0; i < totalFloors; i++) {
            final int finalI = i;
            final int surfaceLevel = finalI * floorHeight + roofHeight;

            if (rand.nextInt(16) == 0) {
                pillar.generate(world, rand, chunkPos.getBlock(0, surfaceLevel, 0));
            } else {
                Stream<Template> floatingTrees = this.floatingTrees.stream()
                        .filter(tree -> rand.nextInt(this.floatingTrees.size() * 5) == 0);

                if (floatingTrees.count() > 0) {
                    floatingTrees.forEach(tree -> {
                        PlacementSettings settings = new PlacementSettings();

                        double height = (finalI + 1) * floorHeight - roofHeight
                                + rand.nextInt(13 * 2) - 13;

                        BlockPos pos = chunkPos.getBlock(
                                rand.nextInt(11) + 8,
                                (int) height,
                                rand.nextInt(11) + 8);

                        tree.addBlocksToWorldChunk(world, pos, settings);
                    });
                }
            }

            structureByLevelMap.get(i).forEach(gen -> gen.generateStructure(world, rand, chunkPos));

            worldGenByLevelMap.get(i).entries().stream()
                    // Filter if can spawn
                    .filter(key -> rand.nextInt(key.getKey()) == 0)
                    .forEach(entry ->
                            entry.getValue()
                                    // generating every world gen on floor surface
                                    .generate(world, rand, chunkPos.getBlock(0, surfaceLevel, 0)));


            // Special world gen
            if (i == 0) {

                // TODO Too luggy!!!
                if (rand.nextInt(500) == 0) {
                    village.generate(world, rand, chunkPos.getBlock(0, 30, 0));
                }
            }
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
