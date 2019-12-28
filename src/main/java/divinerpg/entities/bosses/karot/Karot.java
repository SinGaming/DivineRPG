package divinerpg.entities.bosses.karot;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.eden.bunny.angry.AngryBunny;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.RGBHelper;
import divinerpg.utils.time.TimedAction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class Karot extends DivineBoss {
    private final TimedAction timedAction;

    public Karot(World world) {
        super(EntitiesRegistry.karot, world, randomColor(), 2000);
        timedAction = new TimedAction(240);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1250, 32, 10);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (timedAction.tryExecute() && !world.isRemote()) {
            summonPet();
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote()) {
            for (int i = 0; i < 5; i++) {
                summonPet();
            }
        }
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 3.7F;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }


    private void summonPet() {
        BlockPos pos = this.getPosition().add(new Vec3i(randDistribute(), randDistribute(), randDistribute()));
        AngryBunny angryBunny = new AngryBunny(world);
        angryBunny.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(angryBunny);

        world.addParticle(RGBHelper.particlefromRGB(255, 0, 0),
                angryBunny.posX,
                angryBunny.posY + angryBunny.getHeight() + 0.5,
                angryBunny.posZ,
                randDistribute(),
                randDistribute(),
                randDistribute());
    }

    private float randDistribute() {
        return rand.nextFloat() * 2 - 1;
    }
}
