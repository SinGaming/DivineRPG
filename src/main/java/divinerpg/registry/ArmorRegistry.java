package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.armor.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorRegistry {
    public static final ResourceLocation JACKOMAN = new ResourceLocation(DivineRPG.MODID, "jack_o_man_power_set");

    public static String[] getArmorColors() {
        return new String[]{"", "red_", "yellow_", "green_", "blue_", "gray_"};
    }

    @SubscribeEvent
    public static void registerPowers(RegistryEvent.Register<IPoweredArmorSet> e) {
        IForgeRegistry<IPoweredArmorSet> registry = e.getRegistry();
        IForgeRegistry<Item> itemRegistry = ForgeRegistries.ITEMS;

        String[] armorColors = getArmorColors();

        List<Item> helmets = Arrays.stream(armorColors).map(x -> itemRegistry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_helmet"))).collect(Collectors.toList());
        List<Item> chests = Arrays.stream(armorColors).map(x -> itemRegistry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_chestplate"))).collect(Collectors.toList());
        List<Item> legs = Arrays.stream(armorColors).map(x -> itemRegistry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_leggings"))).collect(Collectors.toList());
        List<Item> boots = Arrays.stream(armorColors).map(x -> itemRegistry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_boots"))).collect(Collectors.toList());

        IArmorSet armorSet = new ArmorSet().withVariants(helmets.toArray(new Item[0]), chests.toArray(new Item[0]), legs.toArray(new Item[0]),
                boots.toArray(new Item[0]), null);

        IPoweredArmorSet rupeeSet = new PoweredArmorSet(armorSet, null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onPlayerReceiveDamage(event, ArmorEvents::isMeeleeDamage, x -> x * 0.25F));

        registry.register(rupeeSet.setRegistryName(new ResourceLocation(DivineRPG.MODID, "rupee_set")));

        armorSet = new ArmorSet().withVariant(ItemRegistry.jack_o_man_helmet, ItemRegistry.jack_o_man_chestplate,
                ItemRegistry.jack_o_man_leggings, ItemRegistry.jack_o_man_boots, null);

        registry.register(new PoweredArmorSet(armorSet, null).setRegistryName(JACKOMAN));
    }
}
