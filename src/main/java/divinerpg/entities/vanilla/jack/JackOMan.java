package divinerpg.entities.vanilla.jack;

import divinerpg.entities.base.villager.DivineVillager;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.VillagerRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class JackOMan extends DivineVillager {
    public JackOMan(World world) {
        super(EntitiesRegistry.jack_o_man, world, VillagerRegistry.OVERWORLD, VillagerRegistry.jack_o_man);

        putItem(EquipmentSlotType.MAINHAND, ItemRegistry.scythe);
        putItem(EquipmentSlotType.HEAD, Items.CARVED_PUMPKIN);
    }
}
