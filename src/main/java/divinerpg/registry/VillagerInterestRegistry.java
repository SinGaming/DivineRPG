package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        registry.register(create("iceika", BlockRegistry.coalstone_furnace));

        registry.register(create("overworld", Blocks.GRASS_BLOCK, Blocks.STONE));
    }

    private static PointOfInterestType create(String name, Block... blocks) {
        try {
            Method func_226359_a_ = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_226359_a_",
                    String.class, Set.class, Integer.class, Integer.class);
            return (PointOfInterestType) func_226359_a_.invoke(null, name, new HashSet<>(Arrays.asList(blocks)), 1, 1);
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Can't find method creating PointOfInterestType");
        }
    }
}
