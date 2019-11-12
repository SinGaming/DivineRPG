package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.biomes.ApalachiaBiome;
import divinerpg.world.biomes.EdenBiome;
import divinerpg.world.biomes.WildwoodBiome;
import net.minecraft.world.biome.Biome;
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

    @ObjectHolder("biome_wildwood")
    public static Biome WILDWOOD;

    @ObjectHolder("biome_apalachia")
    public static Biome APALACHIA;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        // TODO no twilight stone in that biomes!
        registry.register(new EdenBiome().setRegistryName(DivineRPG.MODID, "biome_eden"));
        registry.register(new WildwoodBiome().setRegistryName(DivineRPG.MODID, "biome_wildwood"));
        registry.register(new ApalachiaBiome().setRegistryName(DivineRPG.MODID, "biome_apalachia"));

    }
}
