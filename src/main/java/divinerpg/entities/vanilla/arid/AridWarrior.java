package divinerpg.entities.vanilla.arid;

import divinerpg.entities.base.DivineArcher;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AridWarrior extends DivineArcher {
    public AridWarrior(World world) {
        super(EntitiesRegistry.arid_warrior, world, SoundRegistry.ARID_WARRIOR_HURT, SoundRegistry.ARID_WARRIOR, 2.25F, 0, "", "",
                new ItemStack(ItemRegistry.shadow_bow));
    }

    public AridWarrior(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        registerAttackAI(0.27F, 18, 16.0F);
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        ArrowEntity arrowEntity = new ArrowEntity(world, this);
        arrowEntity.setDamage(1.5F);
        return (T) arrowEntity;
    }
}
