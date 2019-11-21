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
        addGroundplacedMonster(EntitiesRegistry.crab, BiomeDictionary.Type.BEACH, 100, 4, 4);
        addGroundplacedMonster(EntitiesRegistry.entrhralled_dramcryx, 70, 3, 4);
        addGroundplacedMonster(EntitiesRegistry.jungle_dramcryx, BiomeDictionary.Type.JUNGLE, 80, 1, 4);
    }

    private static void addGroundplacedMonster(EntityType type, int weight, int min, int max) {
        addGroundplacedMonster(type, null, weight, min, max);
    }

    private static void addGroundplacedMonster(EntityType type, BiomeDictionary.Type biomeType, int weight, int min, int max) {
        EntitySpawnPlacementRegistry.register(type, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);

        Stream<Biome> biomes = ForgeRegistries.BIOMES.getValues().stream();

        if (biomeType != null) {
            biomes = biomes.filter(x -> BiomeDictionary.hasType(x, biomeType));
        }

        biomes.forEach(x -> x.getSpawns(EntityClassification.MONSTER)
                .add(new Biome.SpawnListEntry(type, weight, min, max)));
    }
}
