package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.armor.ArmorEvents;
import divinerpg.api.armor.ArmorSet;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.api.armor.PoweredArmorSet;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
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

        // Rupee sets
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

        // ender sets
        registry.register(new PoweredArmorSet(
                new ArmorSet()
                        .withVariants(
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_helmet")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_chestplate")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_leggings")).toArray(Item[]::new),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_boots")).toArray(Item[]::new),
                                null
                        ), null
        )
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, DamageSource::isExplosion))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "ender_set")));

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

        // halite
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.halite_helmet,
                        ItemRegistry.halite_chestplate,
                        ItemRegistry.halite_leggings,
                        ItemRegistry.halite_boots, null),
                null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onAddMeleeDamage(event, x -> x + 16))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "halite_set"))
        );

        // bedrock
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.bedrock_helmet,
                        ItemRegistry.bedrock_chestplate,
                        ItemRegistry.bedrock_leggings,
                        ItemRegistry.bedrock_boots, null), null
        ).addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, s -> s.isFireDamage() || s.isExplosion() || s == DamageSource.LAVA))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::removeFire)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "bedrock_set")));

        // elite realmit
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.elite_realmit_helmet,
                        ItemRegistry.elite_realmit_chestplate,
                        ItemRegistry.elite_realmit_leggings,
                        ItemRegistry.elite_realmit_boots, null), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::disableFallDamage)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "elite_realmit_set")));

        // arlemite
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.arlemite_helmet,
                        ItemRegistry.arlemite_chestplate,
                        ItemRegistry.arlemite_leggings,
                        ItemRegistry.arlemite_boots, null), null
        ).addAbility(LivingHurtEvent.class, event -> {
            ArmorEvents.onPlayerReceiveDamage(event,
                    source -> source.isProjectile() || source.damageType.equals("thrown"),
                    x -> x * 0.2F);
        })
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "arlemite_set")));

        // kraken
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.kraken_helmet,
                        ItemRegistry.kraken_chestplate,
                        ItemRegistry.kraken_leggings,
                        ItemRegistry.kraken_boots, null), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::breatheUnderwater)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.DROWN)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "kraken_set")));

        // wither_reaper
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.wither_reaper_helmet,
                        ItemRegistry.wither_reaper_chestplate,
                        ItemRegistry.wither_reaper_leggings,
                        ItemRegistry.wither_reaper_boots, null), null
        ).addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.WITHER)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "wither_reaper_set")));

        // skeleman
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.skeleman_helmet,
                        ItemRegistry.skeleman_chestplate,
                        ItemRegistry.skeleman_leggings,
                        ItemRegistry.skeleman_boots, null), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::refillHunger)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "skeleman_set")));

        // inferno
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.inferno_helmet,
                        ItemRegistry.inferno_chestplate,
                        ItemRegistry.inferno_leggings,
                        ItemRegistry.inferno_boots, null), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::removeFire)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "inferno_set")));

        // aquastrive
        registry.register(new PoweredArmorSet(
                new ArmorSet().withVariant(
                        ItemRegistry.aquastrive_helmet,
                        ItemRegistry.aquastrive_chestplate,
                        ItemRegistry.aquastrive_leggings,
                        ItemRegistry.aquastrive_boots, null), (player, isEquipped) -> {
            if (!isEquipped)
                ArmorEvents.removeSpeed(player);
        }
        ).addAbility(TickEvent.PlayerTickEvent.class, event -> ArmorEvents.speedUpInWater(event.player, 1.2F))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::breatheUnderwater)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.DROWN)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "aquastrive_set")));

    }
}
