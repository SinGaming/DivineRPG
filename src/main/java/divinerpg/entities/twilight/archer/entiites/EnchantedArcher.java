package divinerpg.entities.twilight.archer.entiites;

import divinerpg.entities.base.DivineArcher;
import divinerpg.items.DivineBowItem;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EnchantedArcher extends DivineArcher {
    public EnchantedArcher(World world) {
        this(EntitiesRegistry.enchanted_archer, world, ItemRegistry.apalachia_bow);
    }

    public EnchantedArcher(EntityType<? extends MonsterEntity> type, World world, DivineBowItem bow) {
        super(type, world, SoundRegistry.HIGH_HIT, SoundRegistry.ARCHER, 2.9F, bow);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(getArcherHealth(), getAttackDamage(), getArmorValue());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        registerAttackAI(0.27, 60, 15);
    }

    protected float getArcherHealth() {
        return 100;
    }

    protected float getAttackDamage() {
        return 12;
    }

    protected float getArmorValue() {
        return 10;
    }
}
