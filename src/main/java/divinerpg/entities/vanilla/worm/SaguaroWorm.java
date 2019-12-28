package divinerpg.entities.vanilla.worm;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class SaguaroWorm extends DivineArcher {
    public SaguaroWorm(World world) {
        super(EntitiesRegistry.saguaro_worm, world, SoundRegistry.SAGUARO_WORM, SoundRegistry.SAGUARO_WORM, 2.5F, 0, "", null, ItemStack.EMPTY);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
    }

    @Override
    protected void registerGoals() {
        registerAttackAI(1, 50, 10);

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        for (int i = 0; i < 25; i++) {
            super.attackEntityWithRangedAttack(target, distanceFactor);
        }
    }

    @Override
    protected void lunch(IProjectile bullet, double x, double y, double z, double xzVec) {
        super.lunch(bullet, x + rand(), y + rand(), z + rand(), xzVec);
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 4, "saguaro_worm_shot", null);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return worldIn.getBlockState(pos.down()).isIn(BlockTags.SAND)
                ? super.getBlockPathWeight(pos, worldIn)
                : -1;
    }

    private float rand() {
        float scale = 1F / 2;

        return rand.nextFloat() * 2 - (scale / 2);
    }
}
