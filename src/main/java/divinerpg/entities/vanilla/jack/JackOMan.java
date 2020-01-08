package divinerpg.entities.vanilla.jack;

import divinerpg.entities.base.villager.DivineVillager;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.VillagerRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class JackOMan extends DivineVillager {
    public JackOMan(World world) {
        super(EntitiesRegistry.jack_o_man, world, VillagerRegistry.OVERWORLD, VillagerRegistry.jack_o_man);

        setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.scythe));
        setDropChance(EquipmentSlotType.MAINHAND, -1);

        setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.JACK_O_LANTERN));
    }
}
