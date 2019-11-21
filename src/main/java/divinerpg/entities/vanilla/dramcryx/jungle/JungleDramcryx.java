package divinerpg.entities.vanilla.dramcryx.jungle;

import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.world.World;

public class JungleDramcryx extends DivineMonster {

    public JungleDramcryx(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public JungleDramcryx(World world) {
        super(EntitiesRegistry.jungle_dramcryx, world, SoundRegistry.DRAMCRYX_HURT, SoundRegistry.DRAMCRYX, 1.25F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn) || ArmorEvents.tryPoison(entityIn, 1);
    }
}
