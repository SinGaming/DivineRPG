package divinerpg.entities.twilight.tomo.entities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class WildwoodTomo extends EdenTomo {
    public WildwoodTomo(World world) {
        this(EntitiesRegistry.wildwood_tomo, world);
    }

    public WildwoodTomo(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        registerInner(110, 10);
    }
}
