package divinerpg.entities.base;

import divinerpg.entities.projectiles.DivineArrow.DivineArrowEntity;
import divinerpg.items.DivineBowItem;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DivineArcher extends PeacefullDivineMonster implements IRangedAttackMob {
    private static final DataParameter<String> NAME = EntityDataManager.createKey(DivineArcher.class, DataSerializers.STRING);
    private static final DataParameter<String> POWER = EntityDataManager.createKey(DivineArcher.class, DataSerializers.STRING);
    private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DivineArcher.class, DataSerializers.FLOAT);

    @Deprecated
    protected DivineArcher(World world) {
        super(world);
    }

    protected DivineArcher(EntityType<? extends MonsterEntity> type, World world, SoundEvent hurt,
                           SoundEvent ambient, float eyeHight, DivineBowItem bow) {
        this(type, world, hurt, ambient, eyeHight, bow.damage, bow.arrowName, bow.power, new ItemStack(bow));
    }

    protected DivineArcher(EntityType<? extends MonsterEntity> type, World world, SoundEvent hurt,
                           SoundEvent ambient, float eyeHight, float damage, String arrowName, String powers,
                           ItemStack mainHand) {
        super(type, world, hurt, ambient, eyeHight);

        EntityDataManager manager = getDataManager();
        setItemStackToSlot(EquipmentSlotType.MAINHAND, mainHand);

        manager.set(NAME, arrowName);
        manager.set(POWER, powers);
        manager.set(DAMAGE, damage);
    }

    @Override
    protected void registerData() {
        super.registerData();

        EntityDataManager manager = getDataManager();

        manager.register(DAMAGE, 0F);
        manager.register(NAME, "");
        manager.register(POWER, "");
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        ItemStack itemstack = this.findAmmo(this.getHeldItem(ProjectileHelper.getHandWith(this, Items.BOW)));
        IProjectile bullet = this.createArrow(itemstack, distanceFactor);

        double d0 = target.posX - this.posX;
        double d1 = target.getBoundingBox().minY + (double) (target.getHeight() / 3.0F) - ((Entity) bullet).posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        lunch(bullet, d0, d1, d2, d3);
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(((Entity) bullet));
    }

    protected void lunch(IProjectile bullet, double x, double y, double z, double xzVec) {
        bullet.shoot(x, y + xzVec * (double) 0.2F, z, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
    }

    protected <T extends Entity & IProjectile> T createArrow(ItemStack arrow, float distance) {
        EntityDataManager manager = getDataManager();
        AbstractArrowEntity bullet = new DivineArrowEntity(this.world, this, manager.get(NAME), manager.get(DAMAGE), manager.get(POWER));

        if (this.getHeldItemMainhand().getItem() instanceof net.minecraft.item.BowItem)
            bullet = ((net.minecraft.item.BowItem) this.getHeldItemMainhand().getItem()).customeArrow(bullet);

        return (T) bullet;
    }
}
