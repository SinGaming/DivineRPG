package divinerpg.entities.eden.archer;

import divinerpg.entities.base.DivineArcher;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SunArcher extends DivineArcher {
    public SunArcher(World world) {
        super(EntitiesRegistry.sun_archer, world, SoundEvents.ENTITY_ZOMBIE_HURT, SoundEvents.ENTITY_ZOMBIE_AMBIENT, 2, ItemRegistry.eden_bow);
    }

    public SunArcher(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        registerAttackAI(0.27F, 50, 10);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9);
    }
}
