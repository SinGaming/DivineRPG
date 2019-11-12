package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.biomes.DivineBiome;
import divinerpg.world.feature.DivineTreeFeature;
import divinerpg.world.feature.config.CustomFillerBlockType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class BiomeRegisty {
    @ObjectHolder("biome_eden")
    public static Biome EDEN;

    @ObjectHolder("biome_wildwood")
    public static Biome WILDWOOD;

    @ObjectHolder("biome_apalachia")
    public static Biome APALACHIA;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        // TODO balance ore gen
        registry.register(new DivineBiome(
                BlockRegistry.edenGrass,
                BlockRegistry.edenDirt,
                Color.YELLOW,
                CustomFillerBlockType.EDEN_DIRT,
                new DivineTreeFeature(false, 7, () -> BlockRegistry.edenSapling, () -> BlockRegistry.edenLog, () -> BlockRegistry.edenLeaves),
                BlockRegistry.sunbloom,
                BlockRegistry.sunBlossom,
                BlockRegistry.edenBrush,
                BlockRegistry.edenOre).setRegistryName(DivineRPG.MODID, "biome_eden"));

        registry.register(new DivineBiome(
                BlockRegistry.wildwoodGrass,
                BlockRegistry.wildwoodDirt,
                Color.BLUE,
                CustomFillerBlockType.WILDWOOD_DIRT,
                new DivineTreeFeature(false, 7, () -> BlockRegistry.wildwoodSapling, () -> BlockRegistry.wildwoodLog, () -> BlockRegistry.wildwoodLeaves),
                BlockRegistry.wildwoodTallgrass,
                BlockRegistry.moonlightFern,
                BlockRegistry.moonBud,
                BlockRegistry.wildwoodOre).setRegistryName(DivineRPG.MODID, "biome_wildwood"));

        registry.register(new DivineBiome(
                BlockRegistry.apalachiaGrass,
                BlockRegistry.apalachiaDirt,
                Color.PINK,
                CustomFillerBlockType.APALACHIA_DIRT,
                new DivineTreeFeature(false, 7, () -> BlockRegistry.apalachiaSapling, () -> BlockRegistry.apalachiaLog, () -> BlockRegistry.apalachiaLeaves),
                BlockRegistry.apalachiaTallgrass,
                BlockRegistry.dusk_flower,
                BlockRegistry.dusk_bloom,
                BlockRegistry.apalachiaOre).setRegistryName(DivineRPG.MODID, "biome_apalachia"));

    }
}
