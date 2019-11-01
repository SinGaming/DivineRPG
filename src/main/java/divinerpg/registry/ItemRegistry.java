package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.CorruptedBulletEntity;
import divinerpg.items.RangeWeaponItem;
import divinerpg.items.vanilla.TwilightClock;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.SpawnHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ShootableItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
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
                .withBulletOnLeftClick((world, player, power) -> SpawnHelper.singleSpawn(world, player, new CorruptedBulletEntity(world, player)))
                .group(DivineRPGTabs.DivineItems)).setRegistryName(DivineRPG.MODID, "corrupted_cannon"));
        registry.register(new TwilightClock(itemTabProperty).setRegistryName(DivineRPG.MODID, "twilight_clock"));
    }
}
