package divinerpg.entities.vanilla.eye;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Eye extends DivineMonster {
    public Eye(World world) {
        super(EntitiesRegistry.eye, world, SoundRegistry.THE_EYE_HURT, SoundRegistry.THE_EYE, 1.75F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(40, 10);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        PlayerEntity player = this.world.getClosestPlayer(this, 64);
        if (isStareAtMe(player)) {
            player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, true));

            // Todo trigger
        }
    }

    /**
     * Checks to see if this enderman should be attacking this player
     */
    private boolean isStareAtMe(PlayerEntity player) {
        if (player == null)
            return false;

        Vec3d vec3d = player.getLook(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(this.serverPosX - player.serverPosX, this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.serverPosY + (double) player.getEyeHeight()), this.serverPosZ - player.serverPosZ);
        double d0 = vec3d1.length();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 16)
            return -1;

        return super.getBlockPathWeight(pos, worldIn);
    }
}
