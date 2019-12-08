package divinerpg.entities.vanilla.worm;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

// TODO add loot table
public class SaguaroWorm extends DivineArcher {
    public SaguaroWorm(World world) {
        super(EntitiesRegistry.saguaro_worm, world, SoundRegistry.SAGUARO_WORM, SoundRegistry.SAGUARO_WORM, 2.5F, 0, "", "", ItemStack.EMPTY);
    }

    public SaguaroWorm(EntityType<? extends Entity> type, World world) {
        this(world);
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
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1, 60, 10.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        double y = this.posY + 2.7D;

        double tx = target.posX - this.posX;
        double ty = target.posY - y;
        double tz = target.posZ - this.posZ;

        for (double h = -1.5; h < 1.5; h += 0.3) {
            for (double r = 0; r < 1.5 - Math.abs(h); r += 0.3) {
                for (double theta = 0; theta < Math.PI * 2; theta += Math.PI / 8) {
                    BulletEntity shot = new BulletEntity(world, this, 4, "saguaro_worm_shot", null);

                    shot.posX = this.posX + r * Math.cos(theta);
                    shot.posY = this.posY + 5 + h;
                    shot.posZ = this.posZ + r * Math.sin(theta);

                    shot.shoot(tx, ty, tz, 0.9f, 5);
                    world.addEntity(shot);
                }
            }
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return worldIn.getBlockState(pos.down()).isIn(BlockTags.SAND)
                ? super.getBlockPathWeight(pos, worldIn)
                : -1;
    }
}
