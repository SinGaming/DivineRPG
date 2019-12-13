package divinerpg.entities.twilight.cadilion.enitities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class MortumCadilion extends ApalachiaCadilion {
    public MortumCadilion(World world) {
        this(EntitiesRegistry.mortum_cadillion, world);
    }

    public MortumCadilion(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected float getCadilionDamage() {
        return super.getCadilionDamage() + 8;
    }

    @Override
    protected float getCadilionHealth() {
        return super.getCadilionHealth() + 40;
    }
}
