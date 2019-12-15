package divinerpg.entities.twilight.golem.entities;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class WildwoodGolem extends DivineMonster {
    public WildwoodGolem(World world) {
        this(EntitiesRegistry.wildwood_golem, world);
    }

    public WildwoodGolem(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world, SoundRegistry.GROWL_HURT, SoundRegistry.GROWL_HURT, 2.7F);
    }

    @Override
    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getGolemHealth());
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getGolemAttack());
    }

    protected float getGolemHealth() {
        return 200;
    }

    protected float getGolemAttack() {
        return 16;
    }
}
