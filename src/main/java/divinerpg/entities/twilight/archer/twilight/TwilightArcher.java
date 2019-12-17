package divinerpg.entities.twilight.archer.twilight;

import divinerpg.entities.twilight.archer.entiites.SkythernArcher;
import divinerpg.items.DivineBowItem;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class TwilightArcher extends SkythernArcher {
    public TwilightArcher(World world) {
        this(EntitiesRegistry.twilight_archer, world, ItemRegistry.twilight_bow);
    }

    public TwilightArcher(EntityType<? extends MonsterEntity> type, World world, DivineBowItem bow) {
        super(type, world, bow);
    }

    @Override
    protected float getArcherHealth() {
        return super.getArcherHealth() + 20;
    }

    @Override
    protected float getAttackDamage() {
        return super.getAttackDamage() + 2;
    }
}
