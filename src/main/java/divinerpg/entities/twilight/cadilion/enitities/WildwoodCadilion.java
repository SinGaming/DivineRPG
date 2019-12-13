package divinerpg.entities.twilight.cadilion.enitities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class WildwoodCadilion extends EdenCadilion {
    public WildwoodCadilion(World world) {
        this(EntitiesRegistry.wildwood_cadillion, world);
    }

    public WildwoodCadilion(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected float getCadilionDamage() {
        return super.getCadilionDamage() + 2;
    }
}
