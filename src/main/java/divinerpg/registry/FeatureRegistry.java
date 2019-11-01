package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.feature.DivineFlowersFeature;
import divinerpg.world.feature.DivineTreeFeature;
import divinerpg.world.feature.IslandFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(DivineRPG.MODID)
public class FeatureRegistry {
    @ObjectHolder("eden_tree_feature")
    private static DivineTreeFeature eden_tree_feature = null;
    @ObjectHolder("twilight_stone_islands")
    public static IslandFeature twilight_stone_islands = null;
    @ObjectHolder("eden_flowers_feature")
    public static DivineFlowersFeature eden_flowers_feature = null;

    /**
     * Injecting features to worldgen
     */
    public static void registerWorldGen() {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            for (BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(type)) {
                Biome biome = entry.biome;

                if (DivineRPG.CONFIG.worlgen.genTrees.get()) {
                    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                            Biome.createDecoratedFeature(eden_tree_feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP,
                                    new ChanceConfig(10)));
                }
            }
        }
    }

    /**
     * Registering features
     */
    public static void registerFeatures() {
        IForgeRegistry<Feature<?>> registry = ForgeRegistries.FEATURES;

        registry.register(new DivineTreeFeature(false, 7, () -> BlockRegistry.edenSapling,
                () -> BlockRegistry.edenLog, () -> BlockRegistry.edenLeaves).setRegistryName(DivineRPG.MODID, "eden_tree_feature"));

        registry.register(new IslandFeature(() -> BlockRegistry.twilightStone.getDefaultState())
                .setRegistryName(DivineRPG.MODID, "twilight_stone_islands"));

        registry.register(new DivineFlowersFeature(((random, pos) -> {
            int i = random.nextInt(100);
            if (i > 90) {
                return BlockRegistry.sunbloom.getDefaultState();
            }

            if (i > 50) {
                return BlockRegistry.sunBlossom.getDefaultState();
            }

            return BlockRegistry.edenBrush.getDefaultState();
        })).setRegistryName(DivineRPG.MODID, "eden_flowers_feature"));
    }
}
