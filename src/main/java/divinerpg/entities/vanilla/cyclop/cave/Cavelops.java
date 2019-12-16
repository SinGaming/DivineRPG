package divinerpg.entities.vanilla.cyclop.cave;

import divinerpg.entities.base.DivineArcher;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Cavelops extends DivineArcher {
    public Cavelops(World world) {
        super(EntitiesRegistry.cavelops, world, SoundRegistry.CYCLOPS_HURT, SoundRegistry.CYCLOPS, 3.5F,
                1, "", "", ItemStack.EMPTY);
    }

    public Cavelops(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(60, 6);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        registerAttackAI(0.27F, 30, 10.0F);
    }

    @Override
    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        return (T) new BulletEntity(world, this, 6, "cave_rock", null);
    }

    @Override
    protected void lunch(IProjectile bullet, double x, double y, double z, double xzVec) {
        bullet.shoot(x, y + xzVec * 0.20000000298023224D, z, 1.6F, 12.0F);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 20) {
            return -1;
        }

        return super.getBlockPathWeight(pos, worldIn);
    }
}
