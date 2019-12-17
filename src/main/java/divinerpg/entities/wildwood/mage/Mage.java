package divinerpg.entities.wildwood.mage;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Mage extends DivineArcher {
    public Mage(World world) {
        super(EntitiesRegistry.mage, world, SoundRegistry.INSECT, SoundRegistry.INSECT, 2, 0, "", "", ItemStack.EMPTY);
    }

    public Mage(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(90, 5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        registerAttackAI(1, 20, 15);
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 5, "blank", RGBHelper.particlefromRGB(62, 212, 254));
    }

    @Override
    protected SoundEvent getShootSound() {
        return SoundRegistry.MAGE_FIRE;
    }
}
