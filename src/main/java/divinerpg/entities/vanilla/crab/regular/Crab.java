package divinerpg.entities.vanilla.crab.regular;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class Crab extends PeacefullDivineMonster {

    public Crab(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public Crab(World world) {
        super(EntitiesRegistry.crab, world, SoundRegistry.CRAB_HURT, SoundRegistry.CRAB, 1);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(45, 6);
    }
}
