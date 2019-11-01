package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.eden.EdenBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class BiomeRegisty {
    @ObjectHolder("biome_eden")
    public static Biome EDEN;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        registry.register(EDEN = new EdenBiome().setRegistryName(DivineRPG.MODID, "biome_eden"));
        OverworldBiomeProvider.BIOMES_TO_SPAWN_IN.add(EDEN);
    }
}
