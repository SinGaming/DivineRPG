package divinerpg.entities.twilight.archer.entiites;

import divinerpg.items.DivineBowItem;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SkythernArcher extends EnchantedArcher {
    public SkythernArcher(World world) {
        this(EntitiesRegistry.skythern_archer, world, ItemRegistry.skythern_bow);
    }

    public SkythernArcher(EntityType<? extends MonsterEntity> type, World world, DivineBowItem bow) {
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
