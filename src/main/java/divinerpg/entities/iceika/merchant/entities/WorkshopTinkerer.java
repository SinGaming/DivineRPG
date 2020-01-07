package divinerpg.entities.iceika.merchant.entities;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.VillagerRegistry;
import net.minecraft.world.World;

public class WorkshopTinkerer extends WorkshopMerchant {
    public WorkshopTinkerer(World world) {
        super(EntitiesRegistry.workshop_tinkerer, world, VillagerRegistry.ICEIKA, VillagerRegistry.workshop_tinkerer);
    }
}
