package divinerpg.entities.twilight.cadilion.enitities;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EdenCadilion extends DivineMonster {
    public EdenCadilion(World world) {
        this(EntitiesRegistry.eden_cadillion, world);
    }

    public EdenCadilion(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world, SoundRegistry.GROWL_HURT, SoundRegistry.CADILLION, 1.3F);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getCadilionHealth());
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getCadilionDamage());
    }

    protected float getCadilionDamage() {
        return 10;
    }

    protected float getCadilionHealth() {
        return 75;
    }
}
