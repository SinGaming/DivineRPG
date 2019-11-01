package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(DivineRPG.MODID)
@Mod.EventBusSubscriber
public class BiomeRegisty {
    @ObjectHolder("biome_eden")
    public static Biome EDEN;

    public static void registerBiomes() {

        // TODO not working

//        IForgeRegistry<Biome> registry = ForgeRegistries.BIOMES;
//
//        registry.register(EDEN = new EdenBiome().setRegistryName(DivineRPG.MODID, "biome_eden"));
//        OverworldBiomeProvider.BIOMES_TO_SPAWN_IN.add(EDEN);
    }
}
