package divinerpg.entities.wildwood.wolf;

import divinerpg.entities.base.DivineWolf;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MoonWolf extends DivineWolf {
    public MoonWolf(World w) {
        super(EntitiesRegistry.moon_wolf, w);
    }

    public MoonWolf(EntityType<? extends Entity> type, World worldIn) {
        this(worldIn);
    }

    @Override
    protected float getWolfHealth() {
        return isTamed()
                ? 20
                : 200;
    }

    @Override
    protected int getTamingChance() {
        return 20;
    }

    @Override
    protected float getAttack() {
        return 10;
    }

}
