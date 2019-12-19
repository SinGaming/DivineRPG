package divinerpg.entities.bosses.ayeraco.model;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// TODO think about ayeraco goal?..
public class AyeracoModel {
    private static final String key = "AyeracosGang";
    private final Ayeraco owner;
    private final List<EntityFinder<Ayeraco>> gang = new ArrayList<>();

    public AyeracoModel(Ayeraco owner) {
        this.owner = owner;
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
