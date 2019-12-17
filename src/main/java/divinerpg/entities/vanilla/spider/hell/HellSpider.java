package divinerpg.entities.vanilla.spider.hell;

import divinerpg.entities.base.DivineSpider;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class HellSpider extends DivineSpider {

    public HellSpider(World world) {
        super(EntitiesRegistry.hell_spider, world, SoundEvents.ENTITY_SPIDER_HURT, SoundRegistry.HELL_SPIDER, 0.6F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result) {
            entityIn.setFire(3);
        }

        return result;
    }

}
