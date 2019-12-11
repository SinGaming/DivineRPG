package divinerpg.entities.twilight.tomo;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EdenTomo extends PeacefullDivineMonster {

    public EdenTomo(World world) {
        this(EntitiesRegistry.eden_tomo, world);
    }

    public EdenTomo(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world, SoundRegistry.GROWL_HURT, SoundRegistry.CROAK, 0.6F);
        this.experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        registerInner(100, 8);
    }

    protected void registerInner(float health, float attack) {
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attack);
    }
}
