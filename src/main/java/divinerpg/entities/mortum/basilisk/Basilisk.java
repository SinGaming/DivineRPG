package divinerpg.entities.mortum.basilisk;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class Basilisk extends DivineMonster {
    public Basilisk(World world) {
        super(EntitiesRegistry.basilisk, world, SoundRegistry.GROWL_HURT, SoundRegistry.MUCKY, 0.55F);
        setPathPriority(PathNodeType.WATER, -1);
    }

    public Basilisk(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(500,6,10);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
    }
}
