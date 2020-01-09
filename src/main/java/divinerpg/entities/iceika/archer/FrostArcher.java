package divinerpg.entities.iceika.archer;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.DivineArrow.DivineArrow;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FrostArcher extends DivineArcher {
    public FrostArcher(World world) {
        super(EntitiesRegistry.frost_archer, world, SoundEvents.ENTITY_ZOMBIE_HURT, SoundEvents.ENTITY_ZOMBIE_AMBIENT, 1.725F, ItemRegistry.icicle_bow);

        setPathPriority(PathNodeType.WATER, -1);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(40, 0);
    }

    @Override
    protected void registerGoals() {
        registerAttackAI(0.27D, 2, 10);

        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new DivineArrow(world, this, "snowstorm_arrow", 1.5F, null);
    }
}
