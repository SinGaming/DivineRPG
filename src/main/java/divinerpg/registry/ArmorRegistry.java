package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.armor.*;
import divinerpg.api.armor.interfaces.IArmorSet;
import divinerpg.api.armor.interfaces.IPoweredArmor;
import divinerpg.utils.DivineArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorRegistry {
    public static final ResourceLocation JACKOMAN = new ResourceLocation(DivineRPG.MODID, "jack_o_man_power_set");
    public static final ResourceLocation CORRUPTED = new ResourceLocation(DivineRPG.MODID, "corrupted_set");

    public static String[] getArmorColors() {
        return new String[]{"", "red_", "yellow_", "green_", "blue_", "gray_"};
    }

    @SubscribeEvent
    public static void registerPowers(RegistryEvent.Register<IPoweredArmor> e) {
        IForgeRegistry<IPoweredArmor> registry = e.getRegistry();

        // Rupee sets
        registry.register(new PoweredArmor(
                new ArmorSet()
                        .withVariants(Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_helmet")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_chestplate")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_leggings")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "rupee_boots"))
                        ))
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onPlayerReceiveDamage(event, ArmorEvents::isMeeleeDamage, x -> x * 0.15F))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "rupee_set")));

        // ender sets
        registry.register(new PoweredArmor(
                new ArmorSet()
                        .withVariants(
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_helmet")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_chestplate")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_leggings")),
                                Arrays.stream(getArmorColors()).map(x -> ItemRegistry.find(x + "ender_boots"))
                        ))
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, DamageSource::isExplosion))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "ender_set")));

        // jack-o-man
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.JACK_O_MAN), null).setRegistryName(JACKOMAN));

        // divine
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.DIVINE), null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onAddMeleeDamage(event, x -> x + 6))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::disableFallDamage)
                .addAbility(LivingEvent.LivingJumpEvent.class, event -> ArmorEvents.adjustJumpHeight(event, 2))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "divine_set"))
        );

        // halite
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.HALITE),
                null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onAddMeleeDamage(event, x -> x + 16))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "halite_set"))
        );

        // bedrock
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.BEDROCK), null
        ).addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, s -> s.isFireDamage() || s.isExplosion() || s == DamageSource.LAVA))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::removeFire)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "bedrock_set")));

        // elite realmit
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.ELITE_REALMIT), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::disableFallDamage)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "elite_realmite_set")));

        // arlemite
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.ARLEMITE), null
        ).addAbility(LivingHurtEvent.class, event -> {
            ArmorEvents.onPlayerReceiveDamage(event,
                    source -> source.isProjectile() || source.damageType.equals("thrown"),
                    x -> x * 0.15F);
        })
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "arlemite_set")));

        // kraken
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.KRAKEN), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::breatheUnderwater)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.DROWN)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "kraken_set")));

        // wither_reaper
        registry.register(new PoweredArmor(createFromName("wither_reaper"), null
        ).addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.WITHER)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "wither_reaper_set")));

        // skeleman
        registry.register(new PoweredArmor(createFromName("skeleman"), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::refillHunger)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "skeleman_set")));

        // inferno
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.INFERNO), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::removeFire)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "inferno_set")));

        // aquastrive
        registry.register(new PoweredArmor(createFromName("aquastrive"), (player, isEquipped) -> ArmorEvents.speedUpInWater(player, -1, true)
        ).addAbility(TickEvent.PlayerTickEvent.class, event -> ArmorEvents.speedUpInWater(event.player, 3, false))
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::breatheUnderwater)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, source -> source.equals(DamageSource.DROWN)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "aquastrive_set")));

        // shadow
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.SHADOW), (player, isEquipped) -> ArmorEvents.speedUpPlayer(player, -1, true)
        ).addAbility(TickEvent.PlayerTickEvent.class, event -> ArmorEvents.speedUpPlayer(event.player, 2.4F, false))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "shadow_set")));

        // netherite
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.NETHERITE), null
        ).addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::removeFire)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "netherite_set")));

        // jungle
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.JUNGLE), null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event, DamageSource::isMagicDamage))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "jungle_set")));

        // frozen
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.FROZEN), null)
                .addAbility(TickEvent.PlayerTickEvent.class, event -> ArmorEvents.frozeNearMobs(event, 10, 6))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "frozen_set")));

        // corrupted
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.CORRUPTED), null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onAddRangedDamage(event, CORRUPTED, x -> x * 1.5F))
                .setRegistryName(CORRUPTED));

        // terran
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.TERRAN), null)
                .addAbility(TickEvent.PlayerTickEvent.class, event -> event.player.addPotionEffect(new EffectInstance(Effects.HASTE, 20, 2)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "terran_set")));

        // eden
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.EDEN), null)
                .addAbility(BlockEvent.HarvestDropsEvent.class, ArmorEvents::increaseFragmentDrops)
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "eden_set")));

        // wildwood
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.WILDWOOD), null)
                .addAbility(TickEvent.PlayerTickEvent.class, event -> {
                    if (event.player.isInWater()) {
                        event.player.heal(0.5F);
                    }
                })
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "wildwood_set")));

        // apalachia
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.APALACHIA), null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onCancelPlayerReceiveDamage(event,
                        s -> s.equals(DamageSource.CACTUS)
                                // todo add arcana trap source
                                || s.equals(DamageSource.FALLING_BLOCK)
                                || s.equals(DamageSource.ANVIL)
                                || s.equals(DamageSource.IN_WALL)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "apalachia_set")));

        // skythern
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.SKYTHERN), null)
                .addAbility(TickEvent.PlayerTickEvent.class, ArmorEvents::disableFallDamage)
                .addAbility(LivingEvent.LivingJumpEvent.class, event -> ArmorEvents.adjustJumpHeight(event, 5))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "skythern_set")));

        // mortum
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.MORTUM), null)
                .addAbility(TickEvent.PlayerTickEvent.class, event -> event.player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20, 2)))
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "mortum_set")));

        // angelic
        registry.register(new PoweredArmor(createFromName(DivineArmorMaterial.ANGELIC), ArmorEvents::onCanFlyChanged)
                .addAbility(TickEvent.PlayerTickEvent.class, event -> {
                    ArmorEvents.onCanFlyChanged(event.player, true);
                    ArmorEvents.disableFallDamage(event);
                })
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "angelic_set")));

        registry.register(new PoweredArmor(
                new ArmorSet().withVariant(
                        ItemRegistry.santa_cap,
                        ItemRegistry.santa_tunic,
                        ItemRegistry.santa_pants,
                        ItemRegistry.santa_boots, null),
                (player, isEquipped) -> ArmorEvents.speedUpPlayer(player, -1, true))
                .addAbility(LivingHurtEvent.class, event -> {
                    if (event.getEntity().dimension.getModType() == DimensionRegistry.ICEIKA) {
                        // handle 80% damage
                        ArmorEvents.onPlayerReceiveDamage(event, ArmorEvents::isMeeleeDamage, x -> x * 0.2F);
                        // Adds +6 melee damage
                        ArmorEvents.onAddMeleeDamage(event, x -> x + 6);
                    }
                })
                .addAbility(TickEvent.PlayerTickEvent.class, event -> {
                    if (event.player.dimension.getModType() == DimensionRegistry.ICEIKA) {
                        // removes hunger
                        ArmorEvents.refillHunger(event);
                        // increase speed
                        ArmorEvents.speedUpPlayer(event.player, 2.4F, false);
                    }
                })
                .setRegistryName(new ResourceLocation(DivineRPG.MODID, "santa_set")));
    }

    private static IArmorSet createFromName(DivineArmorMaterial material) {
        return createFromName(material.getArmorName());
    }

    /**
     * Searches armor from name. Will crash game is cant find all 4 or 5 armor parts
     *
     * @param name - name of armor
     */
    private static IArmorSet createFromName(String name) {
        List<Item> items = Stream.of("_helmet", "_chestplate", "_leggings", "_boots").map(x -> ItemRegistry.find(name + x)).collect(Collectors.toList());

        return new ArmorSet().withVariant(items.get(0), items.get(1), items.get(2), items.get(3), null);

    }
}
