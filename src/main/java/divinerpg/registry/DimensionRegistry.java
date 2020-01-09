package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.DivineDimension;
import divinerpg.world.twilight.CavesDimension;
import divinerpg.world.twilight.FloatingDimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DivineRPG.MODID)
@ObjectHolder(DivineRPG.MODID)
public class DimensionRegistry {
    @ObjectHolder("iceika_dim")
    public static ModDimension ICEIKA;
    @ObjectHolder("eden_dim")
    public static ModDimension EDEN_DIM;
    @ObjectHolder("wildwood_dim")
    public static ModDimension WILDWOOD_DIM;
    @ObjectHolder("apalachia_dim")
    public static ModDimension APALACHIA_DIM;
    @ObjectHolder("skythern_dim")
    public static ModDimension SKYTHERN_DIM;
    @ObjectHolder("mortum_dim")
    public static ModDimension MORTUM_DIM;



    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> e) {
        IForgeRegistry<ModDimension> registry = e.getRegistry();

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.EDEN), BlockRegistry.edenDirt.getDefaultState(), BiomeRegisty.EDEN,
                        Color.YELLOW))
                .setRegistryName(DivineRPG.MODID, "eden_dim"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.WILDWOOD), BlockRegistry.wildwoodDirt.getDefaultState(), BiomeRegisty.WILDWOOD,
                        Color.BLUE))
                .setRegistryName(DivineRPG.MODID, "wildwood_dim"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.APALACHIA), BlockRegistry.apalachiaDirt.getDefaultState(), BiomeRegisty.APALACHIA,
                        Color.PINK))
                .setRegistryName(DivineRPG.MODID, "apalachia_dim"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.SKYTHERN), BlockRegistry.skythernDirt.getDefaultState(), BiomeRegisty.SKYTHERN,
                        Color.GRAY))
                .setRegistryName(DivineRPG.MODID, "skythern_dim"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new CavesDimension(world, DimensionType.byName(DimensionTypeRegistry.MORTUM), BlockRegistry.mortumDirt.getDefaultState(), BiomeRegisty.MORTUM,
                        Color.BLACK))
                .setRegistryName(DivineRPG.MODID, "mortum_dim"));
    }
}
