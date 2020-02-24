package divinerpg.entities.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class SunBurnGoal extends Goal {

    private Entity entity;

    public SunBurnGoal(Entity entity) {
        this.entity = entity;
    }

    /**
     * Taken from ZombieEntity.livingTick
     *
     * @return
     */
    @Override
    public boolean shouldExecute() {
        if (entity.isAlive() && isInDaylight()) {
            if (entity instanceof MobEntity) {
                MobEntity mobEntity = (MobEntity) this.entity;

                ItemStack itemstack = mobEntity.getItemStackFromSlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageable()) {
                        itemstack.setDamage(itemstack.getDamage() + mobEntity.world.rand.nextInt(2));
                        if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
                            mobEntity.sendBreakAnimation(EquipmentSlotType.HEAD);
                            mobEntity.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();

        entity.setFire(8);
    }

    /**
     * Legacy copy of MobEntity.isInDaylight
     *
     * @return
     */
    protected boolean isInDaylight() {
        if (entity.world.isDaytime() && !entity.world.isRemote) {
            float f = entity.getBrightness();
            BlockPos blockpos = entity.getRidingEntity() instanceof BoatEntity
                    ? (new BlockPos(entity.func_226277_ct_(), (double) Math.round(entity.func_226278_cu_()), entity.func_226281_cx_())).up()
                    : new BlockPos(entity.func_226277_ct_(), (double) Math.round(entity.func_226278_cu_()), entity.func_226281_cx_());

            return f > 0.5F && entity.world.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && entity.world.func_226660_f_(blockpos);
        }

        return false;
    }
}
