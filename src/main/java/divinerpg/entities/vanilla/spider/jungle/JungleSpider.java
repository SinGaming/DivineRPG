package divinerpg.entities.vanilla.spider.jungle;

import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.base.DivineSpider;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class JungleSpider extends DivineSpider {
    public JungleSpider(World world) {
        super(EntitiesRegistry.jungle_spider, world, SoundEvents.ENTITY_SPIDER_HURT, SoundRegistry.JUNGLE_SPIDER, 0.6F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            int duration;

            switch (this.world.getDifficultyForLocation(entityIn.getPosition()).getDifficulty()) {
                case NORMAL:
                    duration = 7;
                    break;

                case HARD:
                    duration = 15;
                    break;

                default:
                    duration = -1;
                    break;
            }

            if (duration > 0) {
                ArmorEvents.tryPoison(entityIn, duration);
            }
        }

        return result;
    }
}
