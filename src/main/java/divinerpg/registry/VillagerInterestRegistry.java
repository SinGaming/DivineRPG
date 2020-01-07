package divinerpg.registry;

import com.google.common.collect.ImmutableSet;
import divinerpg.DivineRPG;
import net.minecraft.block.Block;
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
    @ObjectHolder("workshop_merchant")
    public static PointOfInterestType workshop_merchant;

    @SubscribeEvent
    public static void initPoint(final RegistryEvent.Register<PointOfInterestType> e) {

        IForgeRegistry<PointOfInterestType> registry = e.getRegistry();

        // todo add some iceika blocks
        registry.register(create("workshop_merchant", BlockRegistry.coalstone_furnace));
    }

    private static PointOfInterestType create(String name, Block... blocks) {
        return new PointOfInterestType(name, ImmutableSet.copyOf(Stream.of(blocks).map(Block::getDefaultState).collect(Collectors.toList())),
                1, null, 1).setRegistryName(DivineRPG.MODID, name);
    }
}
