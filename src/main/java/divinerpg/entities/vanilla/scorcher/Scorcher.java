package divinerpg.entities.vanilla.scorcher;

import divinerpg.entities.base.DivineBlaze;
import divinerpg.entities.fireball.ScorcherFireball;
import divinerpg.entities.goal.BlazeAttackGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Scorcher extends DivineBlaze {
    public Scorcher(EntityType<? extends MonsterEntity> type, World world) {
        this(world);
    }

    public Scorcher(World world) {
        super(EntitiesRegistry.scorcher, world, SoundEvents.ENTITY_BLAZE_HURT, SoundRegistry.SCORCHER, 1.6F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new BlazeAttackGoal(this, ScorcherFireball::new));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(75.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }
}
