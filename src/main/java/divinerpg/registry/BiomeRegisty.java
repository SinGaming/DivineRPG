package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.iceika.IceikaBiome;
import divinerpg.world.mortum.DivineCaveSurfaceBuilder;
import divinerpg.world.twilight.DivineBiome;
import divinerpg.world.twilight.feature.DivineSmallTreeFeature;
import divinerpg.world.twilight.feature.config.CustomFillerBlockType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class BiomeRegisty {
    @ObjectHolder("eden")
    public static Biome EDEN;

    @ObjectHolder("wildwood")
    public static Biome WILDWOOD;

    @ObjectHolder("apalachia")
    public static Biome APALACHIA;

    @ObjectHolder("skythern")
    public static Biome SKYTHERN;

    @ObjectHolder("mortum")
    public static Biome MORTUM;

    @ObjectHolder("iceika")
    public static Biome ICEIKA;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        // TODO balance ore gen
        registry.register(new DivineBiome(
                BlockRegistry.edenGrass,
                BlockRegistry.edenDirt,
                Color.YELLOW,
                CustomFillerBlockType.EDEN_DIRT,
                new DivineSmallTreeFeature(7, () -> BlockRegistry.edenSapling, () -> BlockRegistry.edenLog, () -> BlockRegistry.edenLeaves),
                BlockRegistry.sunbloom,
                BlockRegistry.sunBlossom,
                BlockRegistry.edenBrush,
                BlockRegistry.edenOre)
//                .addSpawns(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntitiesRegistry.eden_tomo, 20, 4, 4))
//                .addSpawns(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntitiesRegistry.eden_tomo, 20, 4, 4))
                .setRegistryName(DivineRPG.MODID, "eden"));

        registry.register(new DivineBiome(
                BlockRegistry.wildwoodGrass,
                BlockRegistry.wildwoodDirt,
                Color.BLUE,
                CustomFillerBlockType.WILDWOOD_DIRT,
                new DivineSmallTreeFeature(7, () -> BlockRegistry.wildwoodSapling, () -> BlockRegistry.wildwoodLog, () -> BlockRegistry.wildwoodLeaves),
                BlockRegistry.wildwoodTallgrass,
                BlockRegistry.moonlightFern,
                BlockRegistry.moonBud,
                BlockRegistry.wildwoodOre).setRegistryName(DivineRPG.MODID, "wildwood"));

        registry.register(new DivineBiome(
                BlockRegistry.apalachiaGrass,
                BlockRegistry.apalachiaDirt,
                Color.PINK,
                CustomFillerBlockType.APALACHIA_DIRT,
                new DivineSmallTreeFeature(7, () -> BlockRegistry.apalachiaSapling, () -> BlockRegistry.apalachiaLog, () -> BlockRegistry.apalachiaLeaves),
                BlockRegistry.apalachiaTallgrass,
                BlockRegistry.dusk_flower,
                BlockRegistry.dusk_bloom,
                BlockRegistry.apalachiaOre).setRegistryName(DivineRPG.MODID, "apalachia"));

        registry.register(new DivineBiome(
                BlockRegistry.skythernGrass,
                BlockRegistry.skythernDirt,
                Color.GRAY,
                CustomFillerBlockType.SKYTHERN_DIRT,
                new DivineSmallTreeFeature(7, () -> BlockRegistry.skythernSapling, () -> BlockRegistry.skythernLog, () -> BlockRegistry.skythernLeaves),
                BlockRegistry.dust_lily,
                BlockRegistry.dust_brambles,
                BlockRegistry.skythern_brush,
                BlockRegistry.skythernOre).setRegistryName(DivineRPG.MODID, "skythern"));

        // TODO spawn trees
        registry.register(new DivineBiome(
                new Biome.Builder()
                        .surfaceBuilder(new DivineCaveSurfaceBuilder(SurfaceBuilderConfig::deserialize),
                                new SurfaceBuilderConfig(BlockRegistry.mortumGrass.getDefaultState(), BlockRegistry.mortumDirt.getDefaultState(),
                                        BlockRegistry.twilightStone.getDefaultState())),
                Color.BLACK,
                CustomFillerBlockType.MORTUM_DIRT,
                new DivineSmallTreeFeature(7, () -> BlockRegistry.mortumSapling, () -> BlockRegistry.mortumLog, () -> BlockRegistry.mortumLeaves),
                BlockRegistry.eye_plant,
                BlockRegistry.mortum_brush,
                BlockRegistry.demon_brambles,
                BlockRegistry.mortumOre).setRegistryName(DivineRPG.MODID, "mortum"));

        registry.register(new IceikaBiome().setRegistryName(DivineRPG.MODID, "iceika"));

    }
}
