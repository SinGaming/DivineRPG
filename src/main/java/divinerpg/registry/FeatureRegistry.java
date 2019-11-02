package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.config.OreGen;
import divinerpg.world.feature.DivineFlowersFeature;
import divinerpg.world.feature.DivineOreFeature;
import divinerpg.world.feature.DivineTreeFeature;
import divinerpg.world.feature.IslandFeature;
import divinerpg.world.feature.config.CustomFillerBlockType;
import divinerpg.world.feature.config.DivineCountRangeConfig;
import divinerpg.world.feature.config.DivineOreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(DivineRPG.MODID)
public class FeatureRegistry {
    public static DivineTreeFeature eden_tree_feature;
    public static IslandFeature twilight_stone_islands;
    public static DivineFlowersFeature eden_flowers_feature;

    @ObjectHolder("ore_feature")
    public static DivineOreFeature ORE;

    static {
        eden_tree_feature = new DivineTreeFeature(false, 7, () -> BlockRegistry.edenSapling,
                () -> BlockRegistry.edenLog, () -> BlockRegistry.edenLeaves);
        twilight_stone_islands = new IslandFeature(() -> BlockRegistry.twilightStone.getDefaultState());
        eden_flowers_feature = new DivineFlowersFeature(((random, pos) -> {
            int i = random.nextInt(100);
            if (i > 90) {
                return BlockRegistry.sunbloom.getDefaultState();
            }

            if (i > 50) {
                return BlockRegistry.sunBlossom.getDefaultState();
            }

            return BlockRegistry.edenBrush.getDefaultState();
        }));

        ORE = new DivineOreFeature(DivineOreFeatureConfig::deserialize);
    }

    /**
     * Injecting features to worldgen
     */
    public static void registerWorldGen() {
        // TODO inject features in chunk gen, not in biomes

        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            for (BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(type)) {
                Biome biome = entry.biome;

                if (DivineRPG.CONFIG.worlgen.genTrees.get()) {
                    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                            Biome.createDecoratedFeature(eden_tree_feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP,
                                    new ChanceConfig(10)));
                }

                // TODO balancing
                registerOre(biome, DivineRPG.CONFIG.worlgen.realmit, BlockRegistry.realmiteOre, new CustomFillerBlockType(OreFeatureConfig.FillerBlockType.NATURAL_STONE));
                registerOre(biome, DivineRPG.CONFIG.worlgen.arlemit, BlockRegistry.arlemiteOre, new CustomFillerBlockType(OreFeatureConfig.FillerBlockType.NATURAL_STONE));
                registerOre(biome, DivineRPG.CONFIG.worlgen.rupee, BlockRegistry.rupeeOre, new CustomFillerBlockType(OreFeatureConfig.FillerBlockType.NATURAL_STONE));

                registerOre(biome, DivineRPG.CONFIG.worlgen.nethers, BlockRegistry.bloodgemOre, new CustomFillerBlockType(OreFeatureConfig.FillerBlockType.NETHERRACK));
                registerOre(biome, DivineRPG.CONFIG.worlgen.nethers, BlockRegistry.netheriteOre, new CustomFillerBlockType(OreFeatureConfig.FillerBlockType.NETHERRACK));
            }
        }
    }

    private static void registerOre(Biome biome, OreGen config, Block block, CustomFillerBlockType type) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(FeatureRegistry.ORE,
                new DivineOreFeatureConfig(type, block.getDefaultState(),
                        config.maxInChunk.get()),
                Placement.COUNT_RANGE,
                new DivineCountRangeConfig(config)));
    }

    /**
     * Registering features
     */
    public static void registerFeatures() {
        IForgeRegistry<Feature<?>> registry = ForgeRegistries.FEATURES;

        registry.register(eden_tree_feature.setRegistryName(DivineRPG.MODID, "eden_tree_feature"));
        registry.register(twilight_stone_islands.setRegistryName(DivineRPG.MODID, "twilight_stone_islands"));
        registry.register(eden_flowers_feature.setRegistryName(DivineRPG.MODID, "eden_flowers_feature"));
        registry.register(ORE.setRegistryName(DivineRPG.MODID, "ore_feature"));
    }
}
