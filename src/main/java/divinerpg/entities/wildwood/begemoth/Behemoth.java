package divinerpg.entities.wildwood.begemoth;

import divinerpg.entities.base.DivineMonster;
import divinerpg.entities.goal.EatWoodGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.world.World;

public class Behemoth extends DivineMonster {
    public Behemoth(World world) {
        super(EntitiesRegistry.behemoth, world, SoundRegistry.ENDIKU_HURT, SoundRegistry.ENDIKU, 0.7F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(180, 9);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new EatWoodGoal(this));
    }
}
