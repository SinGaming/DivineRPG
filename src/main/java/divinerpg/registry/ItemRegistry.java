package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    @ObjectHolder("divinerpg:realmite_ingot") public static Item realmiteIngot;
    @ObjectHolder("divinerpg:arlemite_ingot") public static Item arlemiteIngot;
    @ObjectHolder("divinerpg:rupee_ingot") public static Item rupeeIngot;
    @ObjectHolder("divinerpg:netherite_ingot") public static Item netheriteIngot;
    @ObjectHolder("divinerpg:bloodgem") public static Item bloodgem;

    @ObjectHolder("divinerpg:corrupted_shards") public static Item corruptedShards;
    @ObjectHolder("divinerpg:divine_shards") public static Item divineShards;
    @ObjectHolder("divinerpg:ender_shards") public static Item enderShards;
    @ObjectHolder("divinerpg:ice_shards") public static Item iceShards;
    @ObjectHolder("divinerpg:jungle_shards") public static Item jungleShards;
    @ObjectHolder("divinerpg:molten_shards") public static Item moltenShards;
    @ObjectHolder("divinerpg:terran_shards") public static Item terranShards;
    @ObjectHolder("divinerpg:corrupted_stone") public static Item corruptedStone;
    @ObjectHolder("divinerpg:divine_stone") public static Item divineStone;
    @ObjectHolder("divinerpg:ender_stone") public static Item enderStone;
    @ObjectHolder("divinerpg:ice_stone") public static Item iceStone;
    @ObjectHolder("divinerpg:jungle_stone") public static Item jungleStone;
    @ObjectHolder("divinerpg:molten_stone") public static Item moltenStone;
    @ObjectHolder("divinerpg:terran_stone") public static Item terranStone;

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
    }
}
