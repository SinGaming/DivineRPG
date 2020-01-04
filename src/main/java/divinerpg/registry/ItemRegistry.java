package divinerpg.registry;

import com.google.common.base.Function;
import divinerpg.DivineRPG;
import divinerpg.api.DivineAPI;
import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.entities.projectiles.ItemBulletEntity;
import divinerpg.items.*;
import divinerpg.utils.DivineArmorMaterial;
import divinerpg.utils.DivineItemTier;
import divinerpg.utils.DivineParticleTypes;
import divinerpg.utils.projectile.Powers;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.SpawnHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class ItemRegistry {
    @ObjectHolder("realmite_ingot")
    public static Item realmiteIngot;
    @ObjectHolder("arlemite_ingot")
    public static Item arlemiteIngot;
    @ObjectHolder("rupee_ingot")
    public static Item rupeeIngot;
    @ObjectHolder("netherite_ingot")
    public static Item netheriteIngot;
    @ObjectHolder("bloodgem")
    public static Item bloodgem;

    @ObjectHolder("bacon")
    public static Item bacon;

    @ObjectHolder("shadow_bar")
    public static Item shadowBar;
    @ObjectHolder("aquatic_ingot")
    public static Item aquaticIngot;
    @ObjectHolder("aquatic_pellets")
    public static Item aquaticPellets;
    @ObjectHolder("hellstone_ingot")
    public static Item hellstoneIngot;

    @ObjectHolder("corrupted_shards")
    public static Item corruptedShards;
    @ObjectHolder("divine_shards")
    public static Item divineShards;
    @ObjectHolder("ender_shards")
    public static Item enderShards;
    @ObjectHolder("ice_shards")
    public static Item iceShards;
    @ObjectHolder("jungle_shards")
    public static Item jungleShards;
    @ObjectHolder("molten_shards")
    public static Item moltenShards;
    @ObjectHolder("terran_shards")
    public static Item terranShards;
    @ObjectHolder("snowflake")
    public static Item snowFlake;
    @ObjectHolder("cyclops_eye_shards")
    public static Item cyclops_eye_shards;
    @ObjectHolder("cyclops_eye")
    public static Item cyclops_eye;

    @ObjectHolder("bedrock_chunk")
    public static Item bedrock_chunk;

    @ObjectHolder("corrupted_stone")
    public static Item corruptedStone;
    @ObjectHolder("divine_stone")
    public static Item divineStone;
    @ObjectHolder("ender_stone")
    public static Item enderStone;
    @ObjectHolder("ice_stone")
    public static Item iceStone;
    @ObjectHolder("jungle_stone")
    public static Item jungleStone;
    @ObjectHolder("molten_stone")
    public static Item moltenStone;
    @ObjectHolder("terran_stone")
    public static Item terranStone;
    @ObjectHolder("shadow_stone")
    public static Item shadowStone;
    @ObjectHolder("bluefire_stone")
    public static Item bluefire_stone;


    @ObjectHolder("corrupted_cannon")
    public static ShootableItem corruptedCannon;
    @ObjectHolder("corrupted_bullet")
    public static Item corruptedBullet;
    @ObjectHolder("twilight_clock")
    public static TwilightClock twilightClock;
    @ObjectHolder("rupee_shickaxe")
    public static Shickaxe rupeeShickaxe;
    @ObjectHolder("arlemite_shickaxe")
    public static Shickaxe arlemitShickaxe;

    @ObjectHolder("ender_bow")
    public static BowItem ender_bow;
    @ObjectHolder("hunter_bow")
    public static BowItem hunter_bow;
    @ObjectHolder("shadow_bow")
    public static DivineBowItem shadow_bow;
    @ObjectHolder("bluefire_bow")
    public static BowItem bluefire_bow;
    @ObjectHolder("inferno_bow")
    public static BowItem inferno_bow;

    @ObjectHolder("golden_fury")
    public static RangeWeaponItem golden_fury;
    @ObjectHolder("crab_anchor")
    public static RangeWeaponItem crab_anchor;
    @ObjectHolder("shark_anchor")
    public static RangeWeaponItem shark_anchor;
    @ObjectHolder("bowhead_anchor")
    public static RangeWeaponItem bowhead_anchor;
    @ObjectHolder("liopleurodon_anchor")
    public static RangeWeaponItem liopleurodon_anchor;

    @ObjectHolder("eden_soul")
    public static Item edenSoul;
    @ObjectHolder("wildwood_soul")
    public static Item wildwoodSoul;
    @ObjectHolder("apalachia_soul")
    public static Item apalachiaSoul;
    @ObjectHolder("skythern_soul")
    public static Item skythernSoul;
    @ObjectHolder("mortum_soul")
    public static Item mortumSoul;

    @ObjectHolder("eden_fragments")
    public static Item edenFragments;
    @ObjectHolder("wildwood_fragments")
    public static Item wildwoodFragments;
    @ObjectHolder("apalachia_fragments")
    public static Item apalachiaFragments;
    @ObjectHolder("skythern_fragments")
    public static Item skythernFragments;
    @ObjectHolder("mortum_fragments")
    public static Item mortumFragments;

    @ObjectHolder("eden_gem")
    public static Item edenGem;
    @ObjectHolder("wildwood_gem")
    public static Item wildwoodGem;
    @ObjectHolder("apalachia_gem")
    public static Item apalachiaGem;
    @ObjectHolder("skythern_gem")
    public static Item skythernGem;
    @ObjectHolder("mortum_gem")
    public static Item mortumGem;

    @ObjectHolder("eden_chunk")
    public static Item edenChunk;
    @ObjectHolder("wildwood_chunk")
    public static Item wildwoodChunk;
    @ObjectHolder("apalachia_chunk")
    public static Item apalachiaChunk;
    @ObjectHolder("skythern_chunk")
    public static Item skythernChunk;
    @ObjectHolder("mortum_chunk")
    public static Item mortumChunk;

    @ObjectHolder("eden_dust")
    public static Item edenDust;
    @ObjectHolder("wildwood_dust")
    public static Item wildwoodDust;
    @ObjectHolder("apalachia_dust")
    public static Item apalachiaDust;
    @ObjectHolder("skythern_dust")
    public static Item skythernDust;
    @ObjectHolder("mortum_dust")
    public static Item mortumDust;
    @ObjectHolder("base_spawn_crystal")
    public static Item baseSpawnCrystal;

    @ObjectHolder("eden_slicer")
    public static ThrowableItem edenSlicer;
    @ObjectHolder("wildwood_slicer")
    public static ThrowableItem wildwoodSlicer;
    @ObjectHolder("apalachia_slicer")
    public static ThrowableItem apalachiaSlicer;
    @ObjectHolder("skythern_slicer")
    public static ThrowableItem skythernSlicer;
    @ObjectHolder("mortum_slicer")
    public static ThrowableItem mortumSlicer;
    @ObjectHolder("halite_slicer")
    public static ThrowableItem haliteSlicer;

    @ObjectHolder("corrupted_pickaxe")
    public static PickaxeItem corruptedPickaxe;
    @ObjectHolder("corrupted_axe")
    public static AxeItem corruptedAxe;
    @ObjectHolder("corrupted_shovel")
    public static ShovelItem corrupted_shovel;
    @ObjectHolder("corrupted_maul")
    public static SwordItem corrupted_maul;

    @ObjectHolder("realmite_pickaxe")
    public static PickaxeItem realmitePickaxe;
    @ObjectHolder("realmite_axe")
    public static AxeItem realmiteAxe;
    @ObjectHolder("realmite_shovel")
    public static ShovelItem realmiteShovel;
    @ObjectHolder("realmite_hoe")
    public static HoeItem realmiteHoe;
    @ObjectHolder("realmite_sword")
    public static SwordItem realmite_sword;

    @ObjectHolder("rupee_pickaxe")
    public static PickaxeItem rupeePickaxe;
    @ObjectHolder("rupee_axe")
    public static AxeItem rupeeAxe;
    @ObjectHolder("rupee_shovel")
    public static ShovelItem rupeeShovel;
    @ObjectHolder("rupee_hoe")
    public static HoeItem rupeeHoe;
    @ObjectHolder("rupee_rapier")
    public static SwordItem rupee_rapier;

    @ObjectHolder("palavence")
    public static SpecialSwordItem palavence;
    @ObjectHolder("massivence")
    public static SpecialSwordItem massivence;

    @ObjectHolder("eden_blade")
    public static SwordItem edenBlade;
    @ObjectHolder("wildwood_blade")
    public static SwordItem wildwoodBlade;
    @ObjectHolder("apalachia_blade")
    public static SwordItem apalachiaBlade;
    @ObjectHolder("skythern_blade")
    public static SwordItem skythernBlade;
    @ObjectHolder("mortum_blade")
    public static SwordItem mortumBlade;
    @ObjectHolder("halite_blade")
    public static SwordItem haliteBlade;

    @ObjectHolder("eden_blitz")
    public static RangeWeaponItem edenBlitz;
    @ObjectHolder("wildwood_blitz")
    public static RangeWeaponItem wildwoodBlitz;
    @ObjectHolder("apalachia_blitz")
    public static RangeWeaponItem apalachiaBlitz;
    @ObjectHolder("skythern_blitz")
    public static RangeWeaponItem skythernBlitz;
    @ObjectHolder("mortum_blitz")
    public static RangeWeaponItem mortumBlitz;
    @ObjectHolder("halite_blitz")
    public static RangeWeaponItem haliteBlitz;

    @ObjectHolder("eden_phaser")
    public static RangeWeaponItem eden_phaser;
    @ObjectHolder("wildwood_phaser")
    public static RangeWeaponItem wildwood_phaser;
    @ObjectHolder("apalachia_phaser")
    public static RangeWeaponItem apalachia_phaser;
    @ObjectHolder("skythern_phaser")
    public static RangeWeaponItem skythern_phaser;
    @ObjectHolder("mortum_phaser")
    public static RangeWeaponItem mortum_phaser;
    @ObjectHolder("halite_phaser")
    public static RangeWeaponItem halite_phaser;

    @ObjectHolder("white_mushroom_seeds")
    public static BlockItem white_mushroom_seeds;
    @ObjectHolder("tomato_seeds")
    public static BlockItem tomato_seeds;
    @ObjectHolder("purple_glowbone_seeds")
    public static BlockItem purple_glowbone_seeds;
    @ObjectHolder("pink_glowbone_seeds")
    public static BlockItem pink_glowbone_seeds;
    @ObjectHolder("sky_plant_seeds")
    public static BlockItem sky_plant_seeds;

    @ObjectHolder("liopleurodon_skull")
    public static Item liopleurodonSkull;
    @ObjectHolder("crab_claw")
    public static Item crabClaw;
    @ObjectHolder("purple_blaze")
    public static Item purpleBlaze;


    @ObjectHolder("healing_stone")
    public static Item healing_stone;
    @ObjectHolder("eden_sparkles")
    public static Item eden_sparkles;
    @ObjectHolder("eden_bow")
    public static DivineBowItem eden_bow;
    @ObjectHolder("eden_arrow")
    public static Item eden_arrow;
    @ObjectHolder("apalachia_bow")
    public static DivineBowItem apalachia_bow;
    @ObjectHolder("apalachia_arrow")
    public static Item apalachia_arrow;
    @ObjectHolder("skythern_bow")
    public static DivineBowItem skythern_bow;
    @ObjectHolder("skythern_arrow")
    public static Item skythern_arrow;
    @ObjectHolder("twilight_bow")
    public static DivineBowItem twilight_bow;
    @ObjectHolder("fury_arrow")
    public static Item fury_arrow;

    @ObjectHolder("halite_bow")
    public static DivineBowItem halite_bow;

    @ObjectHolder("kraken_skin")
    public static Item kraken_skin;
    @ObjectHolder("kraken_scale")
    public static Item kraken_scale;

    @ObjectHolder("kraken_scale")
    public static Item cheese;
    @ObjectHolder("kraken_helmet")
    public static ArmorItem kraken_helmet;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties itemTabProperty = new Item.Properties().group(DivineRPGTabs.DivineItems);

        String[] colors = ArmorRegistry.getArmorColors();
        Item.Properties armorGroup = new Item.Properties().group(DivineRPGTabs.DivineArmor);

        // Seeds
        Item.Properties foodGroup = new Item.Properties().group(DivineRPGTabs.DivineFood);
        registry.register(new BlockItem(BlockRegistry.white_mushroom_plant, foodGroup).setRegistryName(DivineRPG.MODID, "white_mushroom_seeds"));
        registry.register(new BlockItem(BlockRegistry.tomato_plant, foodGroup).setRegistryName(DivineRPG.MODID, "tomato_seeds"));

//        registry.register(new BlockItem(foodGroup).setRegistryName(DivineRPG.MODID, "purple_glowbone_seeds"));
//        registry.register(new BlockItem(foodGroup).setRegistryName(DivineRPG.MODID, "pink_glowbone_seeds"));
//        registry.register(new BlockItem(foodGroup).setRegistryName(DivineRPG.MODID, "sky_plant_seeds"));

        // Food
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().meat().hunger(2).saturation(3).build())
                ).setRegistryName(DivineRPG.MODID, "bacon")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(1).saturation(0.3F).build())
                ).setRegistryName(DivineRPG.MODID, "peppermints")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(4).saturation(1).build())
                ).setRegistryName(DivineRPG.MODID, "chocolate_log")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(2).saturation(0.3F).build())
                ).setRegistryName(DivineRPG.MODID, "snow_cones")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(16).saturation(2).build())
                ).setRegistryName(DivineRPG.MODID, "fruit_cake")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(4).saturation(1).build())
                ).setRegistryName(DivineRPG.MODID, "winterberry")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(5).saturation(7).build())
                ).setRegistryName(DivineRPG.MODID, "hot_pumpkin_pie")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(4).saturation(0.5F).build())
                ).setRegistryName(DivineRPG.MODID, "boiled_egg")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(2).saturation(0.2F).build())
                ).setRegistryName(DivineRPG.MODID, "cheese")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(1).saturation(0.1F).build())
                ).setRegistryName(DivineRPG.MODID, "white_mushroom")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(10).saturation(10).build())
                ).setRegistryName(DivineRPG.MODID, "advanced_mushroom_stew")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(20).saturation(20).build())
                ).setRegistryName(DivineRPG.MODID, "chicken_dinner")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(4).saturation(0.3F).build())
                ).setRegistryName(DivineRPG.MODID, "tomato")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(16).saturation(0.3F).build())
                ).setRegistryName(DivineRPG.MODID, "donut")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(5).saturation(2).fastToEat().meat().build())
                ).setRegistryName(DivineRPG.MODID, "raw_empowered_meat")
        );
        registry.register(
                new Item(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(10).saturation(4).fastToEat().meat().build())
                ).setRegistryName(DivineRPG.MODID, "empowered_meat")
        );
        registry.register(
                new FoodItem(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(5).saturation(1).meat().build()),
                        true).setRegistryName(DivineRPG.MODID, "magic_meat")
        );
        registry.register(
                new FoodItem(new Item.Properties().group(DivineRPGTabs.DivineFood)
                        .food(new Food.Builder().hunger(7).saturation(2.5F).meat().build()),
                        true).setRegistryName(DivineRPG.MODID, "enriched_magic_meat")
        );

        ///////////
        // Swords
        ///////////
        registry.register(new SwordItem(DivineItemTier.CYCLOP, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "cyclopsian_sword"));
        registry.register(new SwordItem(DivineItemTier.ARLEMIT, 6, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "arlemite_stabber"));
        registry.register(new SwordItem(DivineItemTier.RUPEE, 5, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "rupee_rapier"));
        registry.register(new SwordItem(DivineItemTier.SLIME, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "slime_sword"));
        registry.register(new SwordItem(DivineItemTier.BEDROCK, 4, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(4000)).setRegistryName(DivineRPG.MODID, "fury_maul"));
        registry.register(new SwordItem(DivineItemTier.BEDROCK, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(-1)).setRegistryName(DivineRPG.MODID, "bedrock_maul"));
        registry.register(new SwordItem(DivineItemTier.REALMIT, 3, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "realmite_sword"));
        registry.register(new SwordItem(DivineItemTier.BEDROCK, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "bedrock_sword"));
        registry.register(new SpecialSwordItem(DivineItemTier.UNREPAIRABLE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> ArmorEvents.tryHeal(player, 0.5F))
                        .disableSword(true).group(DivineRPGTabs.DivineSwords).maxDamage(60)
        ).setRegistryName(DivineRPG.MODID, "palavence"));
        registry.register(new SpecialSwordItem(DivineItemTier.JUNGLE, 4, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onHit((stack, player, entity) -> ArmorEvents.tryPoison(entity, 5))
                        .group(DivineRPGTabs.DivineSwords).maxDamage(5000)
        ).setRegistryName(DivineRPG.MODID, "poison_saber"));
        registerColors(registry, color -> new SwordItem(DivineItemTier.DIVINE, 9, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, color + "divine_sword"));
        registry.register(new SwordItem(DivineItemTier.CORRUPTED, 8, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "corrupted_maul"));
        registry.register(new SwordItem(DivineItemTier.CORRUPTED, 7, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "death_bringer"));
        registry.register(new SwordItem(DivineItemTier.FROST, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "frost_sword"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 6, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(5000)).setRegistryName(DivineRPG.MODID, "aquaton"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 4, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(7000)).setRegistryName(DivineRPG.MODID, "aquatic_trident"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 4, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "aquatic_dagger"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(4000)).setRegistryName(DivineRPG.MODID, "aqua_maul"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 4, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(4000)).setRegistryName(DivineRPG.MODID, "shark_sword"));
        registry.register(new SwordItem(DivineItemTier.OCEAN, 4, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "ocean_knife"));
        registry.register(new SwordItem(DivineItemTier.CRAB_CLAW, 1, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "dual_claw"));
        registry.register(new SwordItem(DivineItemTier.CRAB_CLAW, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "crabclaw_maul"));
        registry.register(new SwordItem(DivineItemTier.AQUATOOTH, 3, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "aquatooth_sword"));
        registry.register(new SwordItem(DivineItemTier.AQUATOOTH, 3, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "aquatooth_maul"));
        registry.register(new SpecialSwordItem(DivineItemTier.UNREPAIRABLE, 12, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onHit((stack, player, entity) -> entity.setFire(12))
                        .group(DivineRPGTabs.DivineSwords).maxDamage(-1)
        ).setRegistryName(DivineRPG.MODID, "inferno_sword"));
        registry.register(new SwordItem(DivineItemTier.UNREPAIRABLE, 9, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(-1)).setRegistryName(DivineRPG.MODID, "bloodgem_sword"));
        registry.register(new SwordItem(DivineItemTier.SHADOWBAR, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "bluefire_sword"));
        registry.register(new SwordItem(DivineItemTier.SCORCHING, 0, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords))
                .setRegistryName(DivineRPG.MODID, "scorching_sword"));
        registry.register(new SpecialSwordItem(DivineItemTier.MOLTEN, 16, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onHit((stack, player, entity) -> entity.setFire(15))
                        .group(DivineRPGTabs.DivineSwords).maxDamage(6000)
        ).setRegistryName(DivineRPG.MODID, "flaming_fury"));
        registry.register(new SpecialSwordItem(DivineItemTier.MOLTEN, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onHit((stack, player, entity) -> entity.setFire(5))
                        .group(DivineRPGTabs.DivineSwords)
        ).setRegistryName(DivineRPG.MODID, "molten_sword"));
        registry.register(new SpecialSwordItem(DivineItemTier.UNREPAIRABLE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> ArmorEvents.tryHeal(player, 1))
                        .disableSword(true).group(DivineRPGTabs.DivineSwords).maxDamage(60)
        ).setRegistryName(DivineRPG.MODID, "massivence"));
        registerColors(registry, color -> new SwordItem(DivineItemTier.ENDER, 0, -2.4F,
                        new Item.Properties().group(DivineRPGTabs.DivineSwords)).setRegistryName(DivineRPG.MODID, color + "ender_sword"),
                Stream.of(colors).filter(x -> x != "gray_").toArray(String[]::new));
        registry.register(new SwordItem(DivineItemTier.SHADOWBAR, 12, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords)
                .maxDamage(100)).setRegistryName(DivineRPG.MODID, "sandslash"));


        // Axes
        registry.register(new AxeItem(DivineItemTier.CORRUPTED, 1, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_axe"));
        registry.register(new AxeItem(DivineItemTier.REALMIT, 2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_axe"));
        registry.register(new AxeItem(DivineItemTier.RUPEE, 2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "rupee_axe"));

        // Hoes
        // TODO add corrupted hoe?
        registry.register(new HoeItem(DivineItemTier.REALMIT, 0, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_hoe"));
        registry.register(new HoeItem(DivineItemTier.RUPEE, 0, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "rupee_hoe"));

        // Shovels
        registry.register(new ShovelItem(DivineItemTier.CORRUPTED, 0, -3F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_shovel"));
        registry.register(new ShovelItem(DivineItemTier.REALMIT, -2, -3F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_shovel"));
        registry.register(new ShovelItem(DivineItemTier.RUPEE, -2, -3F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "rupee_shovel"));

        // Pickaxes
        registry.register(new PickaxeItem(DivineItemTier.CORRUPTED, 1, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_pickaxe"));
        registry.register(new PickaxeItem(DivineItemTier.REALMIT, -2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_pickaxe"));
        registry.register(new PickaxeItem(DivineItemTier.RUPEE, -2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "rupee_pickaxe"));


        // ARMOR
        for (String color : colors) {
            registerArmor(registry, DivineArmorMaterial.forRupee(color));
            registerArmor(registry, DivineArmorMaterial.forEnder(color));
        }

        registerArmor(registry, DivineArmorMaterial.JACK_O_MAN);
        registerArmor(registry, DivineArmorMaterial.WITHER_REAPER);
        registerArmor(registry, DivineArmorMaterial.SKELEMAN);
        registerArmor(registry, DivineArmorMaterial.DIVINE);
        registerArmor(registry, DivineArmorMaterial.HALITE);
        registerArmor(registry, DivineArmorMaterial.BEDROCK);
        registerArmor(registry, DivineArmorMaterial.REALMIT);
        registerArmor(registry, DivineArmorMaterial.ELITE_REALMIT);
        registerArmor(registry, DivineArmorMaterial.AQUASTRIVE);
        registerArmor(registry, DivineArmorMaterial.ARLEMITE);
        registerArmor(registry, DivineArmorMaterial.KRAKEN);
        registerArmor(registry, DivineArmorMaterial.INFERNO);
        registerArmor(registry, DivineArmorMaterial.SHADOW);
        registerArmor(registry, DivineArmorMaterial.NETHERITE);
        registerArmor(registry, DivineArmorMaterial.JUNGLE);
        registerArmor(registry, DivineArmorMaterial.FROZEN);
        registerArmor(registry, DivineArmorMaterial.CORRUPTED);
        registerArmor(registry, DivineArmorMaterial.TERRAN);
        registerArmor(registry, DivineArmorMaterial.EDEN);
        registerArmor(registry, DivineArmorMaterial.WILDWOOD);
        registerArmor(registry, DivineArmorMaterial.APALACHIA);
        registerArmor(registry, DivineArmorMaterial.SKYTHERN);
        registerArmor(registry, DivineArmorMaterial.MORTUM);


        // Bows
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(36000)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(10000), 3, null, SoundEvents.ENTITY_ARROW_SHOOT, "shadow_arrow")
                .setRegistryName(DivineRPG.MODID, "shadow_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(2500), 3, null, SoundEvents.ENTITY_ARROW_SHOOT, "hunter_arrow")
                .withEffects(new EffectInstance(Effects.POISON, 40, 2))
                .setRegistryName(DivineRPG.MODID, "hunter_bow"));

        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 5, null, SoundEvents.ENTITY_ARROW_SHOOT, "ender_arrow")
                .setRegistryName(DivineRPG.MODID, "ender_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 3, new Powers().withExplosion(3, Explosion.Mode.NONE),
                SoundEvents.ENTITY_ARROW_SHOOT, "bluefire_arrow").setRegistryName(DivineRPG.MODID, "bluefire_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 3, new Powers().withFire(12), SoundEvents.ENTITY_ARROW_SHOOT, "inferno_arrow")
                .setRegistryName(DivineRPG.MODID, "inferno_bow"));


        // Anchors
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "crab_anchor")), 3)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "crab_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "shark_anchor")), 4)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "shark_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "bowhead_anchor")), 5)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "bowhead_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "liopleurodon_anchor")), 6)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "liopleurodon_anchor"));

        // Ranged
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.GOLD_NUGGET, 1)
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new ItemBulletEntity(world, thrower, Items.GOLD_NUGGET, damage)), 30)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "golden_fury"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "ghast_cannon")), 16)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(100)).setRegistryName(DivineRPG.MODID, "ghast_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.CACTUS, 1)
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "crab_anchor")), 12)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(1000)).setRegistryName(DivineRPG.MODID, "crabclaw_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.CACTUS, 1)
                .withShooter((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "bowhead_anchor")), 12)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(1000)).setRegistryName(DivineRPG.MODID, "bowhead_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> corruptedBullet, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.spawnRange(world, player, power, 4, (w, p1, p2) -> new ItemBulletEntity(world, player, ItemRegistry.corruptedBullet, damage)), 10)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "corrupted_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower, damage) -> {
                    IParticleData particle = null;

                    if (DivineAPI.isOn(thrower, ArmorRegistry.JACKOMAN)) {
                        damage += 10;
                        particle = DivineParticleTypes.MORTUM;
                    }

                    SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, damage, "scythe", particle));
                }, 6)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "scythe"));


        // Shards
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_bullet"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "divine_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ender_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ice_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "jungle_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "molten_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "terran_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "snow_flake"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "aquatic_pellets"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "cyclops_eye_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "bedrock_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "liopleurodon_skull"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "crab_claw"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "purple_blaze"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "watching_eye"));

        // STONES
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "divine_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ender_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ice_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "jungle_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "molten_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "terran_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "shadow_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "bluefire_stone"));

        // INGOTS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "realmite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "rupee_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "netherite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "bloodgem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "hellstone_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "aquatic_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "shadow_bar"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "cyclops_eye"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "kraken_skin"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "kraken_scale"));

        // SHICKAXES
        registry.register(new Shickaxe(-2.8F, DivineItemTier.RUPEE.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools).maxDamage(-1)).setRegistryName(DivineRPG.MODID, "rupee_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.ARLEMIT.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "arlemite_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.EDEN.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "eden_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.WILDWOOD.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "wildwood_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.APALACHIA.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "apalachia_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.SKYTHERN.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "skythern_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.MORTUM.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "mortum_shickaxe"));

        registry.register(new TwilightClock(itemTabProperty).setRegistryName(DivineRPG.MODID, "twilight_clock"));


        // SOULS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_soul"));

        // FRAGMENTS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_fragments"));

        // GEMS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_gem"));

        // CHUNKS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_chunk"));

        // DUSTS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_dust"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "fury_fire"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "base_spawn_crystal"));

        // SLICERS
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.edenSlicer, damage))), 8)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "eden_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.wildwoodSlicer, damage))), 10)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "wildwood_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.apalachiaSlicer, damage))), 12)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "apalachia_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.skythernSlicer, damage))), 14)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "skythern_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.mortumSlicer, damage))), 18)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "mortum_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower, damage) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.haliteSlicer, damage))), 20)
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "halite_slicer"));

        // BLITZERS
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> edenDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "eden_blitz", DivineParticleTypes.EDEN)), 10)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "eden_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> wildwoodDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "wildwood_blitz", DivineParticleTypes.WILDWOOD)), 12)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "wildwood_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> apalachiaDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "apalachia_blitz", DivineParticleTypes.APALACHIA)), 14)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "apalachia_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> skythernDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "skythern_blitz", DivineParticleTypes.SKYTHERN)), 16)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "skythern_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> mortumDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "mortum_blitz", DivineParticleTypes.MORTUM)), 18)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "mortum_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> mortumDust, 1).withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "halite_blitz", DivineParticleTypes.HALITE)), 20)
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "halite_blitz"));

        // BLADES
        registry.register(new SwordItem(DivineItemTier.EDEN, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords).maxDamage(2000))
                .setRegistryName(DivineRPG.MODID, "eden_blade"));
        registry.register(new SwordItem(DivineItemTier.WILDWOOD, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords).maxDamage(2100))
                .setRegistryName(DivineRPG.MODID, "wildwood_blade"));
        registry.register(new SwordItem(DivineItemTier.APALACHIA, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords).maxDamage(2200))
                .setRegistryName(DivineRPG.MODID, "apalachia_blade"));
        registry.register(new SwordItem(DivineItemTier.SKYTHERN, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords).maxDamage(2300))
                .setRegistryName(DivineRPG.MODID, "skythern_blade"));
        registry.register(new SwordItem(DivineItemTier.MORTUM, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineSwords).maxDamage(2400))
                .setRegistryName(DivineRPG.MODID, "mortum_blade"));

        registry.register(new SpecialSwordItem(DivineItemTier.HALITE, 10, -2.4F, (ExtendedItemProperties) new ExtendedItemProperties()
                // TODO REMOVE
                .onHit((s, p, t) -> t.attackEntityFrom(DamageSource.causePlayerDamage(p), Float.MAX_VALUE))
                .group(DivineRPGTabs.DivineSwords).maxDamage(2500))
                .setRegistryName(DivineRPG.MODID, "halite_blade"));

        // PHASERS
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "eden_phaser", DivineParticleTypes.EDEN)), 14)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "eden_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "wildwood_phaser", DivineParticleTypes.WILDWOOD)), 17)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "wildwood_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "apalachia_phaser", DivineParticleTypes.APALACHIA)), 20)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "apalachia_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "skythern_phaser", DivineParticleTypes.SKYTHERN)), 23)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "skythern_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "mortum_phaser", DivineParticleTypes.MORTUM)), 26)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "mortum_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power, damage) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, damage, "halite_phaser", DivineParticleTypes.HALITE)), 29)
                .group(DivineRPGTabs.DivineRanged).maxDamage(3000)).setRegistryName(DivineRPG.MODID, "halite_phaser"));


        // Serenades
        registry.register(new ScepterItem(DivineItemTier.UNREPAIRABLE, (ExtendedItemProperties) new ExtendedItemProperties()
                .onBlockHit((world, entity, weapon, hit) -> {
                    if (entity instanceof PlayerEntity) {
                        for (int i = 0; i < 3; i++) {
                            ArmorEvents.spawnLightning(((PlayerEntity) entity), hit.getPos());
                        }
                    }
                }).maxDamage(100).group(DivineRPGTabs.DivineTools), 64, null).setRegistryName(DivineRPG.MODID, "serenade_striker"));
        registry.register(new ScepterItem(DivineItemTier.UNREPAIRABLE, (ExtendedItemProperties) new ExtendedItemProperties()
                .onHit((stack, player, entity) -> {

                    entity.attackEntityFrom(DamageSource.causeThrownDamage(player, player), 14);

                    if (entity instanceof LivingEntity) {
                        ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, 45 * 20, 3));
                    }

                    Vec3d pos = entity.getPositionVec();
                    player.getEntityWorld().addParticle(DivineParticleTypes.MORTUM, pos.x, pos.y, pos.z, 0, 0, 0);
                }).maxDamage(500).group(DivineRPGTabs.DivineTools), 64, ParticleTypes.SMOKE).setRegistryName(DivineRPG.MODID, "serenade_of_death"));
        registry.register(new SpecialSwordItem(DivineItemTier.UNREPAIRABLE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) ->
                        player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 80 * 20, 2)))
                        .disableSword(true).group(DivineRPGTabs.DivineTools).maxDamage(15)).setRegistryName(DivineRPG.MODID, "serenade_of_infusion"));
        registry.register(new SpecialSwordItem(DivineItemTier.UNREPAIRABLE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> {
                            if (player.getHealth() < player.getMaxHealth()) {
                                player.setHealth(player.getMaxHealth());
                                return true;
                            }

                            return false;
                        }
                ).disableSword(true).group(DivineRPGTabs.DivineTools).maxDamage(15)).setRegistryName(DivineRPG.MODID, "serenade_of_health"));

        registry.register(new HealingStone(itemTabProperty).setRegistryName(DivineRPG.MODID, "healing_stone"));
        registry.register(new HealingStone(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_sparkles"));

        registry.register(new ArrowItem(new Item.Properties().group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "eden_arrow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .withArrows(() -> eden_arrow)
                .group(DivineRPGTabs.DivineRanged), 16, null, SoundEvents.ENTITY_ARROW_SHOOT, "eden_arrow")
                .setRegistryName(DivineRPG.MODID, "eden_bow"));

        registry.register(new ArrowItem(new Item.Properties().group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "apalachia_arrow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .withArrows(() -> apalachia_arrow)
                .group(DivineRPGTabs.DivineRanged), 20, null, SoundEvents.ENTITY_ARROW_SHOOT, "apalachia_arrow")
                .setRegistryName(DivineRPG.MODID, "apalachia_bow"));

        registry.register(new ArrowItem(new Item.Properties().group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "skythern_arrow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .withArrows(() -> skythern_arrow)
                .group(DivineRPGTabs.DivineRanged), 20, null, SoundEvents.ENTITY_ARROW_SHOOT, "apalachia_arrow")
                .setRegistryName(DivineRPG.MODID, "skythern_bow"));

        registry.register(new ArrowItem(new Item.Properties().group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "fury_arrow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(14400)
                .withArrows(() -> fury_arrow)
                .group(DivineRPGTabs.DivineRanged), 25, null, SoundEvents.ENTITY_ARROW_SHOOT, "twilight_bow")
                .setRegistryName(DivineRPG.MODID, "twilight_bow"));

        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .withArrows(() -> fury_arrow)
                .group(DivineRPGTabs.DivineRanged), 8, null, SoundEvents.ENTITY_ARROW_SHOOT, "fury_arrow")
                .setRegistryName(DivineRPG.MODID, "halite_bow"));

        registry.register(new TeleportCrystal(itemTabProperty, -1).setRegistryName(DivineRPG.MODID, "teleportation_crystal"));
    }


    /**
     * Search items in divinerpg allias
     *
     * @param name - name of item
     */
    public static Item find(String name) {
        ResourceLocation key = new ResourceLocation(DivineRPG.MODID, name);
        return ForgeRegistries.ITEMS.getValue(key);
    }

    private static void registerColors(IForgeRegistry<Item> registry, Function<String, Item> createFunc, String... colors) {
        for (String color : colors) {
            Item item = createFunc.apply(color);
            registry.register(item);
        }
    }

    private static void registerArmor(IForgeRegistry<Item> registry, DivineArmorMaterial material) {
        registerArmor(registry, material, material.getArmorName());
    }

    private static void registerArmor(IForgeRegistry<Item> registry, IArmorMaterial material, String name) {
        Item.Properties armorGroup = new Item.Properties().group(DivineRPGTabs.DivineArmor);
        registry.register(new ArmorItem(material, EquipmentSlotType.HEAD, armorGroup).setRegistryName(DivineRPG.MODID, name + "_helmet"));
        registry.register(new ArmorItem(material, EquipmentSlotType.CHEST, armorGroup).setRegistryName(DivineRPG.MODID, name + "_chestplate"));
        registry.register(new ArmorItem(material, EquipmentSlotType.LEGS, armorGroup).setRegistryName(DivineRPG.MODID, name + "_leggings"));
        registry.register(new ArmorItem(material, EquipmentSlotType.FEET, armorGroup).setRegistryName(DivineRPG.MODID, name + "_boots"));
    }
}
