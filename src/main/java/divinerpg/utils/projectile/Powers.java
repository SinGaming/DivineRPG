package divinerpg.utils.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Powers {
    //region Private

    final ArrayList<EffectInstance> effects = new ArrayList<>();
    int fire = -1;
    float explosion = -1;
    Explosion.Mode mode = Explosion.Mode.NONE;

    public Powers() {

    }

    public Powers(CompoundNBT ntb) {
        read(ntb);
    }

    public static void handlePowers(@Nonnull Entity bulletEntity, @Nonnull RayTraceResult rayTrace, CompoundNBT nbt) {
        if (nbt == null || nbt.isEmpty())
            return;

        Powers powers = new Powers();
        powers.read(nbt);
        powers.handle(bulletEntity, rayTrace instanceof EntityRayTraceResult ? ((EntityRayTraceResult) rayTrace).getEntity() : null);
    }

    //endregion

    void read(CompoundNBT nbt) {
        fire = nbt.getInt("fire");
        explosion = nbt.getFloat("explosion");

        if (nbt.contains("mode"))
            mode = Explosion.Mode.valueOf(nbt.getString("mode"));

        effects.clear();
        effects.addAll(PotionUtils.getEffectsFromTag(nbt));
    }

    void write(CompoundNBT nbt) {
        nbt.putInt("fire", fire);
        nbt.putFloat("explosion", explosion);

        if (mode != null)
            nbt.putString("mode", mode.name());

        // Taken from ArrowEntity.writeAdditional
        if (!this.effects.isEmpty()) {
            ListNBT listnbt = new ListNBT();

            for (EffectInstance effectinstance : this.effects) {
                listnbt.add(effectinstance.write(new CompoundNBT()));
            }

            nbt.put("CustomPotionEffects", listnbt);
        }
    }

    void handle(Entity bullet, Entity target) {
        if (target != null) {
            if (fire > 0) {
                target.setFire(fire);
            }

            if (target instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) target;

                effects.forEach(livingEntity::addPotionEffect);
            }
        }

        if (explosion > 0 && !bullet.world.isRemote()) {
            bullet.world.createExplosion(bullet, bullet.serverPosX, bullet.serverPosY, bullet.serverPosZ, explosion, mode);
        }
    }

    /**
     * Sets victim on fire after bullet hit. Pass 0 or less to disable
     *
     * @param seconds
     * @return
     */
    public Powers withFire(int seconds) {
        fire = seconds;
        return this;
    }

    /**
     * Creates explosion on hit. Pass zero or less to disable
     *
     * @param explosion
     * @param mode
     * @return
     */
    public Powers withExplosion(float explosion, Explosion.Mode mode) {
        this.explosion = explosion;
        this.mode = mode;
        return this;
    }

    /**
     * Add potion efects on hit
     *
     * @param instances
     * @return
     */
    public Powers withPotions(EffectInstance... instances) {
        if (instances != null && instances.length > 0) {
            effects.addAll(Arrays.asList(instances));
        }

        return this;
    }

    public void addTooltip(List<ITextComponent> tooltip) {
        if (fire > 0) {
            tooltip.add(new TranslationTextComponent("tooltip.effect.burns", fire));
        }

        if (explosion > 0) {
            tooltip.add(new TranslationTextComponent("tooltip.shots.explosive"));
        }
    }

    public CompoundNBT toTag() {
        CompoundNBT result = new CompoundNBT();
        write(result);
        return result;
    }
}
