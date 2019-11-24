package divinerpg.entities.vanilla.frost;

import divinerpg.entities.base.DivineBlaze;
import divinerpg.entities.fireball.FrostFireball;
import divinerpg.entities.goal.BlazeAttackGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Frost extends DivineBlaze {
    public Frost(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public Frost(World world) {
        super(EntitiesRegistry.frost, world, SoundEvents.ENTITY_BLAZE_HURT, SoundRegistry.FROST, 0.6F);
        experienceValue = 20;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new BlazeAttackGoal(this, FrostFireball::new));
    }
}
