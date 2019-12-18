package divinerpg.entities.bosses.vamacheron;

import divinerpg.entities.base.DivineBoss;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Vamacheron extends DivineBoss {
    public Vamacheron(World world) {
        super(EntitiesRegistry.vamacheron, world, randomColor(), 2000);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1350, 34, 0);

        // fast enough
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3 * 1.5);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 2;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
