package divinerpg.entities.twilight.golem.entities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ApalachiaGolem extends WildwoodGolem {
    public ApalachiaGolem(World world) {
        this(EntitiesRegistry.apalachia_golem, world);
    }

    public ApalachiaGolem(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected float getGolemHealth() {
        return super.getGolemHealth() + 50;
    }
}
