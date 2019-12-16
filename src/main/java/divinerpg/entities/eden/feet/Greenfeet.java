package divinerpg.entities.eden.feet;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.World;

public class Greenfeet extends DivineMonster {
    public Greenfeet(World world) {
        super(EntitiesRegistry.greenfeet, world, SoundRegistry.NESRO_HURT, SoundRegistry.NESRO, 1.75F);
    }

    public Greenfeet(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(200, 14);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
    }
}
