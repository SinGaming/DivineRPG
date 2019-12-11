package divinerpg.entities.vanilla.spider.pumpkin;

import divinerpg.entities.base.DivineSpider;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.GoalHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class PumpkinSpider extends DivineSpider {
    public PumpkinSpider(World world) {
        super(EntitiesRegistry.pumpkin_spider, world, SoundEvents.ENTITY_SPIDER_HURT, SoundEvents.ENTITY_SPIDER_AMBIENT, 0.5F);
    }

    public PumpkinSpider(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        GoalHelper.removeAll(goalSelector, WaterAvoidingRandomWalkingGoal.class, LookAtGoal.class, LookRandomlyGoal.class);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS
                ? super.getBlockPathWeight(pos, worldIn)
                : -1;
    }
}
