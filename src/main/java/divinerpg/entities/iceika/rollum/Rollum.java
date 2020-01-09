package divinerpg.entities.iceika.rollum;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Rollum extends DivineMonster {
    public Rollum(World world) {
        super(EntitiesRegistry.rollum, world, SoundRegistry.ROLLUM_HURT, SoundRegistry.ROLLUM, 1.4F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(180, 5);

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3 / 1.2);
        this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1.5);
    }
}
