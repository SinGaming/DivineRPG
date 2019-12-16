package divinerpg.entities.skythern.fiend;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SkythernFiend extends DivineMonster {
    public SkythernFiend(World world) {
        super(EntitiesRegistry.skythern_fiend, world, SoundRegistry.GROWL_HURT, SoundRegistry.INSECT, 1.75F);
    }

    public SkythernFiend(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(80, 20, 10);
    }
}
