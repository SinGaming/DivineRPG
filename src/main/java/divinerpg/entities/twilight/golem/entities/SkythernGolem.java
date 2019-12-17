package divinerpg.entities.twilight.golem.entities;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.world.World;

public class SkythernGolem extends ApalachiaGolem {
    public SkythernGolem(World world) {
        super(EntitiesRegistry.skythern_golem, world);
    }

    @Override
    protected float getGolemHealth() {
        return super.getGolemHealth() + 50;
    }

    @Override
    protected float getGolemAttack() {
        return super.getGolemAttack() + 2;
    }
}
