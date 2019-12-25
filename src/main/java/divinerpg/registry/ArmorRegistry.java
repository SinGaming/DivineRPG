package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.armor.ArmorEvents;
import divinerpg.api.armor.ArmorSet;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.api.armor.PoweredArmorSet;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorRegistry {
    public static final ResourceLocation JACKOMAN = new ResourceLocation(DivineRPG.MODID, "jack_o_man_power_set");

    public static String[] getArmorColors() {
        return new String[]{"", "red_", "yellow_", "green_", "blue_", "gray_"};
    }

    @SubscribeEvent
    public static void registerPowers(RegistryEvent.Register<IPoweredArmorSet> e) {
        IForgeRegistry<IPoweredArmorSet> registry = e.getRegistry();

        // Rupee set
        registry.register(new PoweredArmorSet(
                new ArmorSet()
                        .withVariants(
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_helmet")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_chestplate")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_leggings")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_boots")).toArray(Item[]::new),
                                null
                        ), null
        )
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onPlayerReceiveDamage(event, ArmorEvents::isMeeleeDamage, x -> x * 0.2F))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "rupee_set")));

        // jack-o-man
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(ItemRegistry.jack_o_man_helmet, ItemRegistry.jack_o_man_chestplate,
                        ItemRegistry.jack_o_man_leggings, ItemRegistry.jack_o_man_boots, null),
                null).setRegistryName(JACKOMAN)
        );

        // divine
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(ItemRegistry.divine_helmet, ItemRegistry.divine_chestplate, ItemRegistry.divine_leggings,
                        ItemRegistry.divine_boots, null),
                null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onAddMeleeDamage(event, x -> x + 6))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::disableFallDamage)
                .addAbility(LivingEvent.LivingJumpEvent.class, event -> ArmorEvents.adjustJumpHeight(event, 2))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "divine_set"))
        );
    }
}
