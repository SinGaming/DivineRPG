package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.utils.VillagerBuilder;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.item.ItemStack;
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

    public static IVillagerType ICEIKA;
    public static IVillagerType ARCANA;


    @SubscribeEvent
    public static void initVillager(final RegistryEvent.Register<VillagerProfession> e) {
        IForgeRegistry<VillagerProfession> registry = e.getRegistry();

        ICEIKA = VillagerBuilder.getOrCreate("iceika");
        ARCANA = VillagerBuilder.getOrCreate("arcana");


        new VillagerBuilder(registry, "workshop_merchant", ICEIKA)
                .withTrades(
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_cap), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_tunic), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 6), new ItemStack(ItemRegistry.santa_pants), 5),
                        infinite(new ItemStack(ItemRegistry.snowFlake, 3), new ItemStack(ItemRegistry.santa_boots, 2), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.egg_nog, 2), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.chocolate_log, 5), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.peppermints, 15), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.fruit_cake, 3), 5),

                        infinite(new ItemStack(ItemRegistry.snowFlake, 20), new ItemStack(ItemRegistry.icicle_bane), 20),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.red_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.green_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.blue_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.yellow_christmas_lights, 16), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.purple_christmas_lights, 16), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 9), new ItemStack(ItemRegistry.present_box), 5),

                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.red_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.green_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.blue_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.yellow_candy_cane, 4), 5),
                        infinite(new ItemStack(ItemRegistry.iceStone, 3), new ItemStack(ItemRegistry.purple_candy_cane, 4), 5)
                )
                // todo add biome
                .inBiomes(null)
                .build(VillagerInterestRegistry.workshop_merchant);
    }

    private static VillagerTrades.ITrade infinite(ItemStack price, ItemStack sale, int xp) {
        return new BasicTrade(price, ItemStack.EMPTY, sale, Integer.MAX_VALUE, xp, 1);
    }
}
