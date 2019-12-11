package divinerpg.entities.twilight.tomo;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ApalachiaTomo extends EdenTomo {
    public ApalachiaTomo(World world) {
        this(EntitiesRegistry.apalachia_tomo, world);
    }

    public ApalachiaTomo(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        registerInner(115, 10);
    }
}
