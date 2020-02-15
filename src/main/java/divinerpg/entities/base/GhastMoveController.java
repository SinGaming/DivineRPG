package divinerpg.entities.base;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * Copy of Ghast controller, but accepting any mob types
 */
public class GhastMoveController extends MovementController {
    private final MobEntity parentEntity;
    private int courseChangeCooldown;

    public GhastMoveController(MobEntity ghast) {
        super(ghast);
        this.parentEntity = ghast;
    }

    public void tick() {
        if (this.action == MovementController.Action.MOVE_TO) {
            if (this.courseChangeCooldown-- <= 0) {
                this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                Vec3d vec3d = new Vec3d(this.serverPosX - this.parentEntity.serverPosX, this.serverPosY - this.parentEntity.serverPosY, this.serverPosZ - this.parentEntity.serverPosZ);
                double d0 = vec3d.length();
                vec3d = vec3d.normalize();
                if (this.func_220673_a(vec3d, MathHelper.ceil(d0))) {
                    this.parentEntity.setMotion(this.parentEntity.getMotion().add(vec3d.scale(0.1D)));
                } else {
                    this.action = MovementController.Action.WAIT;
                }
            }

        }
    }

    private boolean func_220673_a(Vec3d p_220673_1_, int p_220673_2_) {
        AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

        for (int i = 1; i < p_220673_2_; ++i) {
            axisalignedbb = axisalignedbb.offset(p_220673_1_);
            if (!this.parentEntity.world.isCollisionBoxesEmpty(this.parentEntity, axisalignedbb)) {
                return false;
            }
        }

        return true;
    }
}
