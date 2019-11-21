package divinerpg.entities.vanilla.crab;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Crab extends DivineMonster {

    public Crab(EntityType<? extends Entity> type, World world) {
        super(world);
    }

    public Crab(World world) {
        super(EntitiesRegistry.crab, world, SoundRegistry.CRAB_HURT, SoundRegistry.CRAB, 1);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
    }
}
