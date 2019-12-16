package divinerpg.entities.twilight.mystic;

import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Mystic extends Spellbinder {
    public Mystic(World world) {
        this(EntitiesRegistry.mystic, world);
    }

    public Mystic(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(120, 10);
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 10, "blank", RGBHelper.particlefromRGB(234, 223, 228));
    }
}
