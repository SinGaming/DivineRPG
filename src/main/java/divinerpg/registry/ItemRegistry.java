package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.CorruptedBulletEntity;
import divinerpg.entities.projectiles.EdenSlicerEntity;
import divinerpg.items.RangeWeaponItem;
import divinerpg.items.Shickaxe;
import divinerpg.items.ThrowableItem;
import divinerpg.items.TwilightClock;
import divinerpg.utils.DivineItemTier;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.SpawnHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ShootableItem;
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

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties itemTabProperty = new Item.Properties().group(DivineRPGTabs.DivineItems);

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "realmite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "rupee_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "netherite_ingot"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "bloodgem"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_bullet"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "divine_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ender_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ice_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "jungle_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "molten_shards"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "terran_shards"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "corrupted_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "divine_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ender_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "ice_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "jungle_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "molten_stone"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "terran_stone"));
        registry.register(new RangeWeaponItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withAmmo(() -> corruptedBullet, 1).withDelay(15)
                .withShooter((world, player, power) -> SpawnHelper.singleSpawn(world, player, new CorruptedBulletEntity(world, player)))
                .group(DivineRPGTabs.DivineItems)).setRegistryName(DivineRPG.MODID, "corrupted_cannon"));
        registry.register(new TwilightClock(itemTabProperty).setRegistryName(DivineRPG.MODID, "twilight_clock"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.RUPEE.forShickaxe(), itemTabProperty.maxDamage(-1)).setRegistryName(DivineRPG.MODID, "rupee_shickaxe"));
        registry.register(new Shickaxe(-2.8F, DivineItemTier.ARLEMIT.forShickaxe(), itemTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_shickaxe"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_soul"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_soul"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_fragments"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_fragments"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_gem"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_gem"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_chunk"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_chunk"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "eden_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "wildwood_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "apalachia_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "skythern_dust"));
        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "mortum_dust"));

        registry.register(new Item(itemTabProperty).setRegistryName(DivineRPG.MODID, "base_spawn_crystal"));

        registry.register(new ThrowableItem((ExtendedItemProperties) new ExtendedItemProperties()
                .withShooter(((world, thrower, percentagePower) -> SpawnHelper.singleSpawn(world, thrower, new EdenSlicerEntity(world, thrower))))
                .group(DivineRPGTabs.DivineItems)
        ).setRegistryName(DivineRPG.MODID, "eden_slicer"));
    }
}
