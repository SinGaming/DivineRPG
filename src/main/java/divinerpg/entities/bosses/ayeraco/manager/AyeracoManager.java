package divinerpg.entities.bosses.ayeraco.manager;

import divinerpg.entities.bosses.ayeraco.Ayeraco;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

// TODO think about ayeraco goal?..
public class AyeracoManager {
    /**
     * Possible ayeraco colors
     */
    public static final Map<BossInfo.Color, Vec3i> beamLocations = new LinkedHashMap<BossInfo.Color, Vec3i>() {{
        put(BossInfo.Color.GREEN, new Vec3i(8, 0, 8));
        put(BossInfo.Color.BLUE, new Vec3i(15, 0, 0));
        put(BossInfo.Color.RED, new Vec3i(5, 0, -12));
        put(BossInfo.Color.YELLOW, new Vec3i(-5, 0, -12));
        put(BossInfo.Color.PURPLE, new Vec3i(-8, 0, 8));
    }};

    public AyeracoManager(Ayeraco owner) {
        this.owner = owner;
    }

    /**
     * Gets beam location
     *
     * @param world
     * @param origin
     * @param color
     * @return
     */
    public static BlockPos getBeamLocation(World world, final BlockPos origin, BossInfo.Color color) {
        Vec3i vec = beamLocations.getOrDefault(color, new Vec3i(0, 0, 0));
        final BlockPos seed = origin.add(vec);

        // going down
        for (BlockPos i = new BlockPos(seed); i.getY() > 0; i = i.down()) {
            if (!world.getBlockState(i).isAir(world, i))
                return i;
        }

        // going down
        for (BlockPos i = new BlockPos(seed); i.getY() < world.getMaxHeight(); i = i.up()) {
            if (!world.getBlockState(i).isAir(world, i))
                return i;
        }

        // take bottom
        return seed.down(seed.getY() - 1);
    }

    private static final String key = "AyeracosGang";
    private final Ayeraco owner;
    private final List<EntityFinder<Ayeraco>> gang = new ArrayList<>();

    /**
     * Summon whole gang
     *
     * @param world
     * @param summonPos
     * @return
     */
    public static boolean summonGang(World world, BlockPos summonPos) {
        List<Ayeraco> ayeracos = beamLocations.keySet().stream().map(x -> {
            Ayeraco result = new Ayeraco(world, summonPos, x);
            BlockPos beam = result.getBeam().up();
            result.setPosition(beam.getX(), beam.getY(), beam.getZ());
            return result;
        }).collect(Collectors.toList());

        if (ayeracos.size() != beamLocations.size())
            return false;

        ayeracos.forEach(x -> x.initGang(ayeracos));
        ayeracos.forEach(world::addEntity);

        return true;
    }

    public void write(CompoundNBT tag) {
        if (gang.isEmpty())
            return;

        ListNBT ayeracos = new ListNBT();
        ayeracos.addAll(gang.stream().map(EntityFinder::find).filter(this::isAlive)
                .map(x -> NBTUtil.writeUniqueId(x.getUniqueID())).collect(Collectors.toList()));
        tag.put(key, ayeracos);
    }

    public void read(CompoundNBT tag) {
        gang.clear();

        if (!tag.contains(key))
            return;

        INBT listRaw = tag.get(key);
        if (!(listRaw instanceof ListNBT))
            return;

        gang.addAll(((ListNBT) listRaw).stream().filter(x -> x instanceof CompoundNBT).map(x -> NBTUtil.readUniqueId(((CompoundNBT) x)))
                .map(x -> new EntityFinder<Ayeraco>(Ayeraco.class, owner.world, x)).collect(Collectors.toList()));
    }


    /**
     * Calling after AI updates
     */
    public void afterAITasks() {
        List<EntityFinder<Ayeraco>> toRemove = gang.stream().filter(x -> x.find() != null && !x.find().isAlive()).collect(Collectors.toList());
        gang.removeAll(toRemove);

        if (!isAlive(owner))
            return;

        if (isAngry(owner)) {

            List<Ayeraco> ayeracos = gang.stream().map(EntityFinder::find).filter(this::isAlive).collect(Collectors.toList());

            switch (owner.getColor()) {
                // adding speed
                case YELLOW:
                    EffectInstance speed = new EffectInstance(Effects.SPEED, 1, 1);
                    ayeracos.forEach(x -> x.addPotionEffect(speed));
                    break;

                // Heal others
                case RED:
                    ayeracos.forEach(x -> x.heal(0.3F));
                    break;

                // strength
                case BLUE:
                    EffectInstance strength = new EffectInstance(Effects.STRENGTH, 1, 2);
                    ayeracos.forEach(x -> x.addPotionEffect(strength));
                    break;
            }
        }

        if (checkColor(BossInfo.Color.PURPLE)) {
            teleportEntity();
            owner.setIdleTime(0);
        }
    }

    /**
     * Call on entity hit
     *
     * @param source
     * @return
     */
    public boolean isProtected(DamageSource source) {
        // Green is protecting from projectiles
        boolean isProtected = source.isProjectile() && checkColor(BossInfo.Color.GREEN);
        return isProtected;
    }

    /**
     * Init gang with all ayeracos
     */
    public void initGang(List<Ayeraco> ayeracos) {
        gang.clear();
        gang.addAll(ayeracos.stream().filter(x -> x != owner)
                .map(EntityFinder::new).collect(Collectors.toList()));
    }

    /**
     * Gets beam location for entity
     *
     * @param seed - summon pos
     * @return
     */
    public BlockPos getBeamLocation(@Nullable BlockPos seed) {
        if (isAlive(owner) && seed != null && beamLocations.containsKey(owner.getColor())) {
            return getBeamLocation(owner.world, seed, owner.getColor());
        }

        return null;
    }


    ////////////////////////
    // HELPING METHODS
    ////////////////////////

    private void teleportEntity() {
        if (!isAlive(owner) || owner.world == null || owner.world.rand == null)
            return;


        Random rand = owner.world.rand;
        owner.playSound(SoundRegistry.AYERACO_TELEPORT, 2.0F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
        owner.setMotion(new Vec3d(rand.nextInt(5), 20, rand.nextInt(5)));
    }

    private boolean checkColor(BossInfo.Color color) {
        // if owner isn't alive, it is doesnt matter
        if (!isAlive(owner))
            return false;

        // che if we have that color
        if (owner.getColor() == color)
            return true;

        // check if gang have healthy ayeraco with that color
        return isHealty(findByColor(color));
    }

    /**
     * Find Ayeraco by color
     *
     * @param color
     * @return
     */
    @Nullable
    private Ayeraco findByColor(BossInfo.Color color) {
        return this.gang.stream().map(EntityFinder::find).filter(x -> x != null && x.getColor() == color).findFirst().orElse(null);
    }

    private boolean isAlive(Entity e) {
        return e != null && e.isAlive();
    }

    /**
     * Checks wherever below 50% of health
     *
     * @return
     */
    public boolean isAngry(LivingEntity e) {
        return isAlive(e) && e.getHealth() / e.getMaxHealth() <= 0.5F;
    }

    /**
     * Checks wherever above 50% of health
     *
     * @param e
     * @return
     */
    private boolean isHealty(LivingEntity e) {
        return isAlive(e) && e.getHealth() / e.getMaxHealth() > 0.5F;
    }


}
