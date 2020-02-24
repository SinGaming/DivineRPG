package divinerpg.registry;

import com.google.common.collect.ImmutableSet;
import divinerpg.DivineRPG;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class VillagerInterestRegistry {
    @ObjectHolder("iceika")
    public static PointOfInterestType iceika;
    @ObjectHolder("overworld")
    public static PointOfInterestType overworld;

    @SubscribeEvent
    public static void initPoint(final RegistryEvent.Register<PointOfInterestType> e) {

        IForgeRegistry<PointOfInterestType> registry = e.getRegistry();

        // todo add some iceika blocks
        registry.register(create(registry, "iceika", BlockRegistry.coalstone_furnace));

        registry.register(create(registry, "overworld", Blocks.GRASS_BLOCK, Blocks.STONE));
    }

    private static PointOfInterestType create(IForgeRegistry<PointOfInterestType> registry, String name, Block... blocks) {
        PointOfInterest

        return new PointOfInterestType(name, ImmutableSet.copyOf(Stream.of(blocks).map(Block::getDefaultState).collect(Collectors.toList())),
                1, null, 1).setRegistryName(DivineRPG.MODID, name);
    }
}
