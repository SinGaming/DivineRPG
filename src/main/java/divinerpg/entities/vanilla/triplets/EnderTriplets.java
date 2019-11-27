package divinerpg.entities.vanilla.triplets;

import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.base.DivineFlying;
import divinerpg.entities.goal.GhastAttackGoal;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EnderTriplets extends DivineFlying {

    public EnderTriplets(EntityType<? extends GhastEntity> type, World world) {
        this(world);
    }

    public EnderTriplets(World world) {
        super(EntitiesRegistry.ender_triplets, world, SoundEvents.ENTITY_GHAST_SCREAM, SoundEvents.ENTITY_GHAST_AMBIENT, 1);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(7, new GhastAttackGoal(this,
                (worldIn, shooter, accelX, accelY, accelZ)
                        -> new DivineFireball(EntitiesRegistry.divine_fireball, worldIn, shooter, accelX, accelY, accelZ,
                        ParticleTypes.PORTAL, "ender_triplets_fireball"),
                SoundEvents.ENTITY_GHAST_SHOOT));
    }
}
