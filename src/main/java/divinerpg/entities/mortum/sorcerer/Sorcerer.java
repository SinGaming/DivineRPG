package divinerpg.entities.mortum.sorcerer;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Sorcerer extends DivineArcher {
    public Sorcerer(World world) {
        super(EntitiesRegistry.sorcerer, world, SoundRegistry.INSECT, SoundRegistry.INSECT, 2.2F, 0, "", null, ItemStack.EMPTY);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.registerAttackAI(1, 20, 15);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        initAttr(150, 12);
    }

    @Override
    protected SoundEvent getShootSound() {
        return SoundRegistry.MAGE_FIRE;
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 12, "blank", RGBHelper.particlefromRGB(97, 31, 54));
    }
}
