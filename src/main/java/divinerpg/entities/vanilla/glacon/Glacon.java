package divinerpg.entities.vanilla.glacon;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Glacon extends DivineMonster {
    public Glacon(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public Glacon(World world) {
        super(EntitiesRegistry.glacon, world, SoundRegistry.GLACIDE_HURT, SoundRegistry.GLACIDE, 1.3F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D * 1.6D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
    }
}
