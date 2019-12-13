package divinerpg.entities.twilight.cori.entities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.world.World;

public class AdvancedCori extends WeakCori {
    public AdvancedCori(World world) {
        this(EntitiesRegistry.advanced_cori, world, 100, "cori_shot");
    }

    public AdvancedCori(EntityType<? extends GhastEntity> type, World world, float attack, String bulletName) {
        super(type, world, attack, bulletName);
    }

    @Override
    protected float getCoriHealth() {
        return super.getCoriHealth() + 25;
    }
}
