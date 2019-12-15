package divinerpg.entities.apalachia.archer;

import divinerpg.entities.base.DivineArcher;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EnchantedArcher extends DivineArcher {
    public EnchantedArcher(World world) {
        super(EntitiesRegistry.enchanted_archer, world, SoundRegistry.HIGH_HIT, SoundRegistry.ARCHER, 2.9F, ItemRegistry.apalachia_bow);
    }

    public EnchantedArcher(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        registerAttackAI(0.27, 60, 15);
    }
}
