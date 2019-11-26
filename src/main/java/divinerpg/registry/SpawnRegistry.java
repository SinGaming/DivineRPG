package divinerpg.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class SpawnRegistry {

    public static void registerSpawn() {
        addGroundplacedMonster(EntitiesRegistry.entrhralled_dramcryx, 70, 3, 4);
        addGroundplacedMonster(EntitiesRegistry.rotatick, 70, 3, 4);
        addGroundplacedMonster(EntitiesRegistry.grue, 30, 1, 4);
        addGroundplacedMonster(EntitiesRegistry.cave_crawler, 70, 2, 3);

        addGroundplacedMonster(EntitiesRegistry.crab, BiomeDictionary.Type.BEACH, 100, 4, 4);
        addGroundplacedMonster(EntitiesRegistry.king_crab, BiomeDictionary.Type.BEACH, 10, 4, 4);

        addGroundplacedMonster(EntitiesRegistry.desert_crawler, BiomeDictionary.Type.SANDY, 50, 1, 4);

        addGroundplacedMonster(EntitiesRegistry.jungle_dramcryx, BiomeDictionary.Type.JUNGLE, 80, 1, 4);
        addGroundplacedMonster(EntitiesRegistry.jungle_spider, BiomeDictionary.Type.JUNGLE, 80, 1, 4);

        addGroundplacedMonster(EntitiesRegistry.frost, BiomeDictionary.Type.SNOWY, 50, 1, 4);
        addGroundplacedMonster(EntitiesRegistry.glacon, BiomeDictionary.Type.SNOWY, 30, 1, 4);
        addAsType(EntitiesRegistry.glacon, EntityClassification.CREATURE, BiomeDictionary.Type.SNOWY, 30, 1, 4);

        addGroundplacedMonster(EntitiesRegistry.hell_spider, BiomeDictionary.Type.NETHER, 50, 1, 1);
        addGroundplacedMonster(EntitiesRegistry.scorcher, BiomeDictionary.Type.NETHER, 7, 4, 4);
        addGroundplacedMonster(EntitiesRegistry.wildfire, BiomeDictionary.Type.NETHER, 50, 1, 1);

        addGroundplacedMonster(EntitiesRegistry.ender_spider, BiomeDictionary.Type.END, 2, 1, 4);
        addGroundplacedMonster(EntitiesRegistry.ender_watcher, BiomeDictionary.Type.END, 10, 4, 4);
        addGroundplacedMonster(EntitiesRegistry.ender_triplets, BiomeDictionary.Type.END, 1, 1, 4);

    }

    private static void addGroundplacedMonster(EntityType type, int weight, int min, int max) {
        addGroundplacedMonster(type, null, weight, min, max);
    }

    private static void addGroundplacedMonster(EntityType type, BiomeDictionary.Type biomeType, int weight, int min, int max) {
        EntitySpawnPlacementRegistry.register(type, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);

        addAsType(type, EntityClassification.MONSTER, biomeType, weight, min, max);
    }

    private static void addAsType(EntityType type, EntityClassification clazz, BiomeDictionary.Type biomeType, int weight, int min, int max) {
        Stream<Biome> biomes = ForgeRegistries.BIOMES.getValues().stream();

        if (biomeType != null) {
            biomes = biomes.filter(x -> BiomeDictionary.hasType(x, biomeType));
        }

        biomes.forEach(x -> x.getSpawns(clazz)
                .add(new Biome.SpawnListEntry(type, weight, min, max)));
    }
}
