package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.DivineDimension;
import divinerpg.world.iceika.IceikaDimension;
import divinerpg.world.mortum.CavesDimension;
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
    @ObjectHolder("eden")
    public static ModDimension EDEN_DIM;
    @ObjectHolder("wildwood")
    public static ModDimension WILDWOOD_DIM;
    @ObjectHolder("apalachia")
    public static ModDimension APALACHIA_DIM;
    @ObjectHolder("skythern")
    public static ModDimension SKYTHERN_DIM;
    @ObjectHolder("mortum")
    public static ModDimension MORTUM_DIM;
    @ObjectHolder("iceika")
    public static ModDimension ICEIKA;


    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> e) {
        IForgeRegistry<ModDimension> registry = e.getRegistry();

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.EDEN), BlockRegistry.edenDirt.getDefaultState(), BiomeRegisty.EDEN,
                        Color.YELLOW))
                .setRegistryName(DivineRPG.MODID, "eden"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.WILDWOOD), BlockRegistry.wildwoodDirt.getDefaultState(), BiomeRegisty.WILDWOOD,
                        Color.BLUE))
                .setRegistryName(DivineRPG.MODID, "wildwood"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.APALACHIA), BlockRegistry.apalachiaDirt.getDefaultState(), BiomeRegisty.APALACHIA,
                        Color.PINK))
                .setRegistryName(DivineRPG.MODID, "apalachia"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, DimensionType.byName(DimensionTypeRegistry.SKYTHERN), BlockRegistry.skythernDirt.getDefaultState(), BiomeRegisty.SKYTHERN,
                        Color.GRAY))
                .setRegistryName(DivineRPG.MODID, "skythern"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new CavesDimension(world, DimensionType.byName(DimensionTypeRegistry.MORTUM), BlockRegistry.mortumDirt.getDefaultState(), BiomeRegisty.MORTUM,
                        Color.BLACK))
                .setRegistryName(DivineRPG.MODID, "mortum"));

        registry.register(new DivineDimension((world, type) -> new IceikaDimension(world, DimensionType.byName(DimensionTypeRegistry.ICEIKA)))
                .setRegistryName(DivineRPG.MODID, "iceika"));
    }
}
