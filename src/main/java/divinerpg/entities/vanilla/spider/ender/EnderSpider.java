package divinerpg.entities.vanilla.spider.ender;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EnderSpider extends EndermanEntity {
    public EnderSpider(EntityType<? extends EndermanEntity> type, World world) {
        this(world);
    }

    public EnderSpider(World world) {
        super(EntitiesRegistry.ender_spider, world);
        this.experienceValue = 20;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.HELL_SPIDER;
    }
}
