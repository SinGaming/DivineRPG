package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.projectiles.BulletEntity;
import divinerpg.entities.projectiles.ItemBulletEntity;
import divinerpg.items.*;
import divinerpg.utils.DivineItemTier;
import divinerpg.utils.DivineParticleTypes;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.SpawnHelper;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

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
    @ObjectHolder("snow_flake")
    public static Item snowFlake;

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
    public static BowItem shadow_bow;
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

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties itemTabProperty = new Item.Properties().group(DivineRPGTabs.DivineItems);


        // CORRUPTED
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> corruptedBullet, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.spawnRange(world, player, power, 4, (w, p1, p2) -> new ItemBulletEntity(world, player, ItemRegistry.corruptedBullet, 10)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "corrupted_cannon"));
        registry.register(new PickaxeItem(DivineItemTier.CORRUPTED, 1, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_pickaxe"));
        registry.register(new AxeItem(DivineItemTier.CORRUPTED, 2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_axe"));
        registry.register(new ShovelItem(DivineItemTier.CORRUPTED, 0, -3F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_shovel"));
        registry.register(new SwordItem(DivineItemTier.CORRUPTED, 8, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "corrupted_maul"));
        // TODO add corrupted hoe?

        // REALMIT
        registry.register(new PickaxeItem(DivineItemTier.REALMIT, 1, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_pickaxe"));
        registry.register(new AxeItem(DivineItemTier.REALMIT, 2, -2.8F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_axe"));
        registry.register(new ShovelItem(DivineItemTier.REALMIT, 0, -3F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_shovel"));
        registry.register(new HoeItem(DivineItemTier.REALMIT, 0, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_hoe"));
        registry.register(new SwordItem(DivineItemTier.REALMIT, 3, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "realmite_sword"));

        // Bows
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(36000)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(10000), 11, null, SoundEvents.ENTITY_ARROW_SHOOT, "shadow_arrow")
                .setRegistryName(DivineRPG.MODID, "shadow_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(2500), 11, null, SoundEvents.ENTITY_ARROW_SHOOT, "hunter_arrow")
                .withEffects(new EffectInstance(Effects.POISON, 40, 2))
                .setRegistryName(DivineRPG.MODID, "hunter_bow"));

        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 16, "", SoundEvents.ENTITY_ARROW_SHOOT, "ender_arrow")
                .setRegistryName(DivineRPG.MODID, "ender_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 11, "explosion", SoundEvents.ENTITY_ARROW_SHOOT, "bluefire_arrow")
                .setRegistryName(DivineRPG.MODID, "bluefire_bow"));
        registry.register(new DivineBowItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withUseDuration(72000)
                .infiiniteArrows(true)
                .group(DivineRPGTabs.DivineRanged), 11, "fire", SoundEvents.ENTITY_ARROW_SHOOT, "inferno_arrow")
                .setRegistryName(DivineRPG.MODID, "inferno_bow"));

        // Anchors
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 3, "crab_anchor")))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "crab_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 4, "shark_anchor")))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "shark_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 5, "bowhead_anchor")))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "bowhead_anchor"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 6, "liopleurodon_anchor")))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "liopleurodon_anchor"));

        // Ranged
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.GOLD_NUGGET, 1)
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new ItemBulletEntity(world, thrower, Items.GOLD_NUGGET, 30)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "golden_fury"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 16, "ghast_cannon")))
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(100)).setRegistryName(DivineRPG.MODID, "ghast_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.CACTUS, 1)
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 12, "crab_anchor")))
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(1000)).setRegistryName(DivineRPG.MODID, "crabclaw_cannon"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> Items.CACTUS, 1)
                .withShooter((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new BulletEntity(world, thrower, 12, "bowhead_anchor")))
                .group(DivineRPGTabs.DivineRanged)
                .maxDamage(1000)).setRegistryName(DivineRPG.MODID, "bowhead_cannon"));

        // Swords
//        registry.register(new SpecialSwordItem(DivineItemTier.PALAVENCE, 0, -2.4F,
//                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> ArmorEvents.tryHeal(player, 0.5F))
//                        .disableSword(true).group(DivineRPGTabs.DivineItems)


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

        // STONES
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "divine_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ender_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ice_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "jungle_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "molten_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "terran_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "shadow_stone"));

        // INGOTS
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "realmite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "rupee_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "netherite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "bloodgem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "hellstone_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "aquatic_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "shadow_bar"));

        // SHICKAXES
        registry.register(new Shickaxe(-2.8F, DivineItemTier.RUPEE.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools).maxDamage(-1)).setRegistryName(DivineRPG.MODID, "rupee_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.ARLEMIT.forShickaxe(), new Item.Properties().group(DivineRPGTabs.DivineTools))
                .setRegistryName(DivineRPG.MODID, "arlemite_shickaxe"));

        registry.register(new TwilightClock(itemTabProperty).setRegistryName(DivineRPG.MODID, "twilight_clock"));

        // HEALING SWORDS
        registry.register(new SpecialSwordItem(DivineItemTier.PALAVENCE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> ArmorEvents.tryHeal(player, 0.5F))
                        .disableSword(true).group(DivineRPGTabs.DivineItems)
        ).setRegistryName(DivineRPG.MODID, "palavence"));
        registry.register(new SpecialSwordItem(DivineItemTier.PALAVENCE, 0, -2.4F,
                (ExtendedItemProperties) new ExtendedItemProperties().onRightClick((world, player, hand) -> ArmorEvents.tryHeal(player, 1))
                        .disableSword(true).group(DivineRPGTabs.DivineItems)
        ).setRegistryName(DivineRPG.MODID, "massivence"));

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

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "base_spawn_crystal"));

        // SLICERS
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.edenSlicer, 8))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "eden_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.wildwoodSlicer, 10))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "wildwood_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.apalachiaSlicer, 12))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "apalachia_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.skythernSlicer, 14))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "skythern_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.mortumSlicer, 16))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "mortum_slicer"));
        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower,
                        new ItemBulletEntity(world, thrower, ItemRegistry.haliteSlicer, 18))))
                .group(DivineRPGTabs.DivineRanged)
        ).setRegistryName(DivineRPG.MODID, "halite_slicer"));

        // BLITZERS
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> edenDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 10, "eden_blitz", DivineParticleTypes.EDEN)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "eden_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> wildwoodDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 12, "wildwood_blitz", DivineParticleTypes.WILDWOOD)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "wildwood_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> apalachiaDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 14, "apalachia_blitz", DivineParticleTypes.APALACHIA)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "apalachia_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> skythernDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 16, "skythern_blitz", DivineParticleTypes.SKYTHERN)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "skythern_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> mortumDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 18, "mortum_blitz", DivineParticleTypes.MORTUM)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "mortum_blitz"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> mortumDust, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 20, "halite_blitz", DivineParticleTypes.HALITE)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "halite_blitz"));

        // BLADES
        registry.register(new SwordItem(DivineItemTier.EDEN, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2000))
                .setRegistryName(DivineRPG.MODID, "eden_blade"));
        registry.register(new SwordItem(DivineItemTier.WILDWOOD, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2100))
                .setRegistryName(DivineRPG.MODID, "wildwood_blade"));
        registry.register(new SwordItem(DivineItemTier.APALACHIA, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2200))
                .setRegistryName(DivineRPG.MODID, "apalachia_blade"));
        registry.register(new SwordItem(DivineItemTier.SKYTHERN, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2300))
                .setRegistryName(DivineRPG.MODID, "skythern_blade"));
        registry.register(new SwordItem(DivineItemTier.MORTUM, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2400))
                .setRegistryName(DivineRPG.MODID, "mortum_blade"));
        registry.register(new SwordItem(DivineItemTier.HALITE, 10, -2.4F, new Item.Properties().group(DivineRPGTabs.DivineItems).maxDamage(2500))
                .setRegistryName(DivineRPG.MODID, "halite_blade"));

        // PHASERS
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 14, "eden_phaser", DivineParticleTypes.EDEN)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "eden_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 17, "wildwood_phaser", DivineParticleTypes.WILDWOOD)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "wildwood_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 20, "apalachia_phaser", DivineParticleTypes.APALACHIA)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "apalachia_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 23, "skythern_phaser", DivineParticleTypes.SKYTHERN)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "skythern_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 26, "mortum_phaser", DivineParticleTypes.MORTUM)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "mortum_phaser"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties().withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player,
                        new BulletEntity(world, player, 29, "halite_phaser", DivineParticleTypes.HALITE)))
                .group(DivineRPGTabs.DivineRanged)).setRegistryName(DivineRPG.MODID, "halite_phaser"));

    }
}
