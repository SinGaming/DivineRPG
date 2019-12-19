package divinerpg.entities.bosses.ayeraco;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.bosses.ayeraco.model.AyeracoModel;
import divinerpg.entities.goal.RandomFlyGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ayeraco extends DivineBoss {
    private final static String beamKey = "BeamPos";
    private final AyeracoModel model;
    private BlockPos beam;

    public Ayeraco(World world) {
        this(world, null, randomColor());
    }

    protected Ayeraco(World world, BlockPos summoner, BossInfo.Color color) {
        super(EntitiesRegistry.ayeraco, world, color, 2000);
        model = new AyeracoModel(this);

        if (summoner == null)
            return;

        switch (color) {
            case GREEN:
                beam = getBeamLocation(8, 8);
                break;

            case BLUE:
                beam = getBeamLocation(15, 0);
                break;

            case RED:
                beam = getBeamLocation(5, -12);
                break;

            case YELLOW:
                beam = getBeamLocation(-5, -12);
                break;

            case PURPLE:
                beam = getBeamLocation(-8, 8);
                break;
        }
    }

    /**
     * Main ctor for summoning Ayeraco team
     *
     * @param world
     * @param summoner
     */
    public Ayeraco(World world, BlockPos summoner) {
        this(world, summoner, BossInfo.Color.GREEN);

        List<BossInfo.Color> colors = Arrays.asList(BossInfo.Color.BLUE, BossInfo.Color.RED, BossInfo.Color.YELLOW, BossInfo.Color.PURPLE);
        List<Ayeraco> all = colors.stream().map(x -> new Ayeraco(world, summoner, x)).collect(Collectors.toList());
        all.add(this);

        all.forEach(x -> x.model.initGang(all));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(600, 5, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);
        this.goalSelector.addGoal(5, new RandomFlyGoal(this));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    public BossInfo.Color getColor() {
        return info.getColor();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        model.read(compound);

        if (compound.contains(beamKey)) {
            beam = BlockPos.fromLong(compound.getLong(beamKey));
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        model.write(compound);

        if (beam != null) {
            compound.putLong(beamKey, beam.toLong());
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return !model.isProtected(source) && super.attackEntityFrom(source, amount);
    }

    @Override
    public void setHealth(float health) {
        boolean wasAngry = model.isAngry(this);

        super.setHealth(health);

        if (wasAngry != model.isAngry(this)) {
            this.playSound(SoundRegistry.AYERACO_HALF_HEALTH, 20, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        model.afterAITasks();
    }


    private BlockPos getBeamLocation(int x, int z) {
        final BlockPos original = getPosition().add(x, 0, z);

        // going down
        for (BlockPos i = new BlockPos(original); i.getY() > 0; i = i.down()) {
            if (!world.getBlockState(i).isAir(world, i))
                return i;
        }

        // going down
        for (BlockPos i = new BlockPos(original); i.getY() < world.getMaxHeight(); i = i.up()) {
            if (!world.getBlockState(i).isAir(world, i))
                return i;
        }

        return original;
    }
}
