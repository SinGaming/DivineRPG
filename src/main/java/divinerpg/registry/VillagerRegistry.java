package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.utils.VillagerBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class VillagerRegistry {
    @ObjectHolder("workshop_merchant")
    public static VillagerProfession workshop_merchant;
    @ObjectHolder("workshop_tinkerer")
    public static VillagerProfession workshop_tinkerer;
    @ObjectHolder("jack_o_man")
    public static VillagerProfession jack_o_man;

    public static IVillagerType ICEIKA;
    public static IVillagerType ARCANA;
    public static IVillagerType OVERWORLD;


    @SubscribeEvent
    public static void initVillager(final RegistryEvent.Register<VillagerProfession> e) {
        IForgeRegistry<VillagerProfession> registry = e.getRegistry();

        ICEIKA = VillagerBuilder.getOrCreate("iceika");
        ARCANA = VillagerBuilder.getOrCreate("arcana");
        OVERWORLD = VillagerBuilder.getOrCreate("overworld");

        // todo add biome
        new VillagerBuilder(registry, "workshop_merchant", ICEIKA)
                .withTrades(
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_cap), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_tunic), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_pants), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 3), new ItemStack(ItemRegistry.santa_boots), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.egg_nog, 2), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.chocolate_log, 5), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.peppermints, 15), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.fruit_cake, 3), 5),

                        infinite(new ItemStack(ItemRegistry.snowFlake, 20), new ItemStack(ItemRegistry.icicle_bane), 20),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.red_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.green_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.blue_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.yellow_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.purple_christmas_lights, 16), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 9), new ItemStack(BlockRegistry.present_box), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.red_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.green_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.blue_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.yellow_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(BlockRegistry.purple_candy_cane, 4), 5)
                )
                .build(VillagerInterestRegistry.iceika);

        new VillagerBuilder(registry, "workshop_tinkerer", ICEIKA)
                .withTrades(
                        infinite(new ItemStack(ItemRegistry.snowFlake), new ItemStack(ItemRegistry.shuriken, 16), new ItemStack(ItemRegistry.snowflake_shuriken, 16), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 4), new ItemStack(ItemRegistry.serenade_striker), new ItemStack(ItemRegistry.serenade_of_ice), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 7), new ItemStack(ItemRegistry.slime_sword), new ItemStack(ItemRegistry.glacier_sword), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 12), new ItemStack(ItemRegistry.shadow_bow), new ItemStack(ItemRegistry.icicle_bow), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.massivence), new ItemStack(ItemRegistry.frossivence), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 12), new ItemStack(ItemRegistry.crabclaw_cannon), new ItemStack(ItemRegistry.frostclaw_cannon), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 12), new ItemStack(ItemRegistry.frost_cannon), new ItemStack(ItemRegistry.fractite_cannon), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 15), new ItemStack(ItemRegistry.frost_sword), new ItemStack(ItemRegistry.frostking_sword), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 18), new ItemStack(ItemRegistry.sound_of_music), new ItemStack(ItemRegistry.sound_of_carols, 1), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 25), new ItemStack(ItemRegistry.ender_sword), new ItemStack(ItemRegistry.enderice), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 30), new ItemStack(ItemRegistry.bluefire_bow), new ItemStack(ItemRegistry.snowstorm_bow), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 45), new ItemStack(ItemRegistry.bedrock_maul), new ItemStack(ItemRegistry.frozen_maul), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 40), new ItemStack(ItemRegistry.divine_sword), new ItemStack(ItemRegistry.icine_sword), 1),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 15), new ItemStack(ItemRegistry.sandslash), new ItemStack(ItemRegistry.snowslash), 1)
                )
                .build(VillagerInterestRegistry.iceika);

        new VillagerBuilder(registry, "jack_o_man", OVERWORLD)
                .withTrades(
                        infinite(new ItemStack(Items.SPIDER_EYE, 60), new ItemStack(Items.BONE, 60), new ItemStack(ItemRegistry.skeleman_helmet), 1),
                        infinite(new ItemStack(Items.SPIDER_EYE, 60), new ItemStack(Items.BONE, 60), new ItemStack(ItemRegistry.skeleman_chestplate), 1),
                        infinite(new ItemStack(Items.SPIDER_EYE, 60), new ItemStack(Items.BONE, 60), new ItemStack(ItemRegistry.skeleman_leggings), 1),
                        infinite(new ItemStack(Items.SPIDER_EYE, 60), new ItemStack(Items.BONE, 60), new ItemStack(ItemRegistry.skeleman_boots), 1),

                        infinite(new ItemStack(Items.ENDER_EYE, 10), new ItemStack(Blocks.PUMPKIN, 50), new ItemStack(ItemRegistry.jack_o_man_helmet), 1),
                        infinite(new ItemStack(Items.ENDER_EYE, 10), new ItemStack(Blocks.PUMPKIN, 50), new ItemStack(ItemRegistry.jack_o_man_chestplate), 1),
                        infinite(new ItemStack(Items.ENDER_EYE, 10), new ItemStack(Blocks.PUMPKIN, 50), new ItemStack(ItemRegistry.jack_o_man_leggings), 1),
                        infinite(new ItemStack(Items.ENDER_EYE, 10), new ItemStack(Blocks.PUMPKIN, 50), new ItemStack(ItemRegistry.jack_o_man_boots), 1),

                        infinite(new ItemStack(Items.WITHER_SKELETON_SKULL, 3), new ItemStack(ItemRegistry.wither_reaper_helmet), 1),
                        infinite(new ItemStack(Items.WITHER_SKELETON_SKULL, 5), new ItemStack(ItemRegistry.wither_reaper_chestplate), 1),
                        infinite(new ItemStack(Items.WITHER_SKELETON_SKULL, 4), new ItemStack(ItemRegistry.wither_reaper_leggings), 1),
                        infinite(new ItemStack(Items.WITHER_SKELETON_SKULL, 2), new ItemStack(ItemRegistry.wither_reaper_boots), 1),

                        infinite(new ItemStack(Items.WITHER_SKELETON_SKULL, 6), new ItemStack(ItemRegistry.scythe), 1)
                )
                .build(VillagerInterestRegistry.overworld);
    }

    private static VillagerTrades.ITrade infinite(ItemStack price, ItemStack sale, int xp) {
        return infinite(price, ItemStack.EMPTY, sale, xp);
    }

    private static VillagerTrades.ITrade infinite(ItemStack price, ItemStack second, ItemStack sale, int xp) {
        // priceMult makes recepies a little bit cheaper for regular customers
        return new BasicTrade(price, second, sale, Integer.MAX_VALUE, xp, 0.05F);
    }

}
