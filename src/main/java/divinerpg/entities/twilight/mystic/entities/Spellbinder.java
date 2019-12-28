package divinerpg.entities.twilight.mystic.entities;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Spellbinder extends DivineArcher {
    public Spellbinder(World world) {
        this(EntitiesRegistry.spellbinder, world);
    }

    public Spellbinder(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world, SoundRegistry.INSECT, SoundRegistry.INSECT, 2, 0, "", null, ItemStack.EMPTY);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        registerAttackAI(1, 20, 15);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(95, 7);
    }

    @Override
    protected SoundEvent getShootSound() {
        return SoundRegistry.MAGE_FIRE;
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 7, "blank", RGBHelper.particlefromRGB(234, 158, 253));
    }
}
