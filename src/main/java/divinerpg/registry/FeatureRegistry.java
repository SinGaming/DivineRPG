package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.feature.DivineTreeFeature;
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
    }
}
