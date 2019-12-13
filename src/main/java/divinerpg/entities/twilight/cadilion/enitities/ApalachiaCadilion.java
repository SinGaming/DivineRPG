package divinerpg.entities.twilight.cadilion.enitities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ApalachiaCadilion extends WildwoodCadilion {
    public ApalachiaCadilion(World world) {
        this(EntitiesRegistry.apalachia_cadillion, world);
    }

    public ApalachiaCadilion(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected float getCadilionHealth() {
        return super.getCadilionHealth() + 5;
    }

    @Override
    protected float getCadilionDamage() {
        return super.getCadilionDamage();
    }
}
