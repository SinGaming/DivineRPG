package divinerpg.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.stream.Stream;

public class SpawnRegistry {

    public static void registerSpawn() {
        addOverworldMonster(EntitiesRegistry.entrhralled_dramcryx, 70, 3, 4);
        addOverworldMonster(EntitiesRegistry.rotatick, 70, 3, 4);
        addOverworldMonster(EntitiesRegistry.grue, 30, 1, 4);
        addOverworldMonster(EntitiesRegistry.cavelops, 70, 1, 4);
        addOverworldMonster(EntitiesRegistry.ender_spider, 4, 1, 4);
        addOverworldMonster(EntitiesRegistry.cave_crawler, 70, 2, 3);
        addOverworldMonster(EntitiesRegistry.miner, 5, 1, 1);
        addOverworldMonster(EntitiesRegistry.eye, 30, 1, 4);
        addOverworldMonster(EntitiesRegistry.rainbour, 1, 1, 1);

        addMonterInBiomes(EntitiesRegistry.desert_crawler, 50, 1, 4, BiomeDictionary.Type.SANDY);
        addMonterInBiomes(EntitiesRegistry.saguaro_worm, 20, 1, 4, BiomeDictionary.Type.SANDY);
        addMonterInBiomes(EntitiesRegistry.arid_warrior, 35, 1, 4, BiomeDictionary.Type.SANDY);

        addMonterInBiomes(EntitiesRegistry.crab, 100, 4, 4, BiomeDictionary.Type.BEACH);
        addMonterInBiomes(EntitiesRegistry.king_crab, 10, 4, 4, BiomeDictionary.Type.BEACH);

        addMonterInBiomes(EntitiesRegistry.koblin, 5, 1, 1, BiomeDictionary.Type.PLAINS);

        addMonterInBiomes(EntitiesRegistry.jungle_dramcryx, 80, 1, 4, BiomeDictionary.Type.JUNGLE);
        addMonterInBiomes(EntitiesRegistry.jungle_spider, 80, 1, 4, BiomeDictionary.Type.JUNGLE);
        addMonterInBiomes(EntitiesRegistry.jungle_bat, 50, 1, 4, BiomeDictionary.Type.JUNGLE);

        addMonterInBiomes(EntitiesRegistry.cyclops, 10, 2, 4, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MOUNTAIN);

        addMonterInBiomes(EntitiesRegistry.frost, 50, 1, 4, BiomeDictionary.Type.SNOWY);
        addMonterInBiomes(EntitiesRegistry.glacon, 30, 1, 4, BiomeDictionary.Type.SNOWY);
        addToSpawn(EntitiesRegistry.glacon, EntityClassification.CREATURE, filter(true, BiomeDictionary.Type.SNOWY),
                30, 1, 4);

        addMonterInBiomes(EntitiesRegistry.hell_spider, 50, 1, 1, BiomeDictionary.Type.NETHER);
        addMonterInBiomes(EntitiesRegistry.scorcher, 7, 4, 4, BiomeDictionary.Type.NETHER);
        addMonterInBiomes(EntitiesRegistry.wildfire, 50, 1, 1, BiomeDictionary.Type.NETHER);
        addMonterInBiomes(EntitiesRegistry.hell_bat, 50, 1, 1, BiomeDictionary.Type.NETHER);

        addToSpawn(EntitiesRegistry.ender_spider, EntityClassification.MONSTER, filter(true, BiomeDictionary.Type.END),
                2, 1, 4);
        addMonterInBiomes(EntitiesRegistry.ender_watcher, 10, 4, 4, BiomeDictionary.Type.END);
        addMonterInBiomes(EntitiesRegistry.ender_triplets, 1, 1, 4, BiomeDictionary.Type.END);
    }

    private static void addOverworldMonster(EntityType type, int weight, int min, int max) {
        EntitySpawnPlacementRegistry.register(type, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);

        addToSpawn(type, EntityClassification.MONSTER, filter(false, BiomeDictionary.Type.END, BiomeDictionary.Type.NETHER), weight, min, max);
    }

    private static void addMonterInBiomes(EntityType type, int weight, int min, int max, BiomeDictionary.Type... types) {
        EntitySpawnPlacementRegistry.register(type, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);

        addToSpawn(type, EntityClassification.MONSTER, filter(true, types), weight, min, max);
    }

    private static Stream<Biome> filter(boolean isInclude, BiomeDictionary.Type... types) {
        Stream<Biome> result = ForgeRegistries.BIOMES.getValues().stream();

        if (types != null) {
            result = result.filter(b -> isInclude == Arrays.stream(types).anyMatch(x -> BiomeDictionary.hasType(b, x)));
        }

        return result;
    }


    private static void addToSpawn(EntityType type, EntityClassification clazz, Stream<Biome> biomes, int weight, int min, int max) {
        biomes.forEach(x -> x.getSpawns(clazz).add(new Biome.SpawnListEntry(type, weight, min, max)));
    }
}
