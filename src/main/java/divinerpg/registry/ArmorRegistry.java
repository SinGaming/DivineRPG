package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.api.DivineAPI;
import divinerpg.api.armor.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArmorRegistry {
    public static String[] getArmorColors() {
        return new String[]{"", "red_", "yellow_", "green_", "blue_", "gray_"};
    }

    public static void initSuperpowers() {
        IForgeRegistry<Item> registry = ForgeRegistries.ITEMS;

        String[] armorColors = getArmorColors();

        List<Item> helmets = Arrays.stream(armorColors).map(x -> registry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_helmet"))).collect(Collectors.toList());
        List<Item> chests = Arrays.stream(armorColors).map(x -> registry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_chestplate"))).collect(Collectors.toList());
        List<Item> legs = Arrays.stream(armorColors).map(x -> registry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_leggings"))).collect(Collectors.toList());
        List<Item> boots = Arrays.stream(armorColors).map(x -> registry.getValue(new ResourceLocation(DivineRPG.MODID, x + "rupee_boots"))).collect(Collectors.toList());

        IArmorSet armorSet = new ArmorSet().withVariants(helmets.toArray(new Item[0]), chests.toArray(new Item[0]), legs.toArray(new Item[0]),
                boots.toArray(new Item[0]), null);

        IPoweredArmorSet rupeeSet = new PoweredArmorSet(armorSet, null)
                .addAbility(LivingHurtEvent.class, event -> ArmorEvents.onPlayerReceiveDamage(event, ArmorEvents::isMeeleeDamage, x -> x * 0.25F));

        DivineAPI.addPowerHandlers(rupeeSet);
    }
}
