package divinerpg.api.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Class contains helper methods for power armor
 */
public class ArmorEvents {

    private static final UUID ARMOR_SPEED_UUID = UUID.fromString("2ae05d96-4b26-420b-8406-156e8febb45f");

    /**
     * Managing player's fly ability on server side
     *
     * @param player - player
     * @param canFly - can we fly or not
     */
    public static void onCanFlyChanged(PlayerEntity player, boolean canFly) {
        // in creative mode we do not need any checks
        if (player.isCreative()
                || canFly == player.abilities.allowFlying
                // Checking only on server
                || isRemote(player)) {
            return;
        }

        player.abilities.allowFlying = canFly;
        if (!canFly && player.abilities.isFlying) {
            player.abilities.isFlying = false;
        }

        player.sendPlayerAbilities();
    }

    /**
     * Adding melee damage from player on server side
     *
     * @param e                    - event
     * @param damageConversionFunc - function modifying original damage amount
     */
    public static void onAddMeleeDamage(LivingHurtEvent e, Function<Float, Float> damageConversionFunc) {
        DamageSource source = e.getSource();
        if (!(source.getTrueSource() instanceof PlayerEntity)
                || source.isMagicDamage()
                || source.isProjectile()
                // should call only on server
                || isRemote(e.getEntity())) {
            return;
        }

        e.setAmount(damageConversionFunc.apply(e.getAmount()));
    }

    /**
     * Adding ranged damage from player on Server side
     *
     * @param e                    - event
     * @param damageConversionFunc - function modifying original damage amount
     */
    public static void onAddRangedDamage(LivingHurtEvent e, Function<Float, Float> damageConversionFunc) {
        DamageSource source = e.getSource();
        if (!(source.getTrueSource() instanceof PlayerEntity)
                || !source.isProjectile()
                // should call only on server
                || isRemote(e.getEntity()))
            return;

        e.setAmount(damageConversionFunc.apply(e.getAmount()));
    }

    /**
     * Canceling damage received by player by condition on server side
     *
     * @param e        -  event
     * @param canApply - can block current damage source
     */
    public static void onCancelPlayerReceiveDamage(LivingHurtEvent e, Function<DamageSource, Boolean> canApply) {
        onPlayerReceiveDamage(e, canApply, aFloat -> 0.f);
    }

    /**
     * refilling hunger of player on server side
     *
     * @param e - tick event
     */
    public static void refillHunger(TickEvent.PlayerTickEvent e) {
        PlayerEntity player = e.player;

        if (!isRemote(player) && player.getFoodStats().needFood()) {
            player.getFoodStats().addStats(1, 0);
        }
    }

    /**
     * Managing amount of damage receiving by player. Should work only on server side
     *
     * @param e                    - tick event
     * @param canApply             - can manage damage with current source
     * @param damageConversionFunc - function returning final damage. If equals/less zero, damage is canceled
     */
    public static void onPlayerReceiveDamage(LivingHurtEvent e, Function<DamageSource, Boolean> canApply, Function<Float, Float> damageConversionFunc) {
        DamageSource source = e.getSource();
        if (!(e.getEntity() instanceof PlayerEntity)
                // should call only on server
                || isRemote(e.getEntity())
                || !canApply.apply(source))
            return;

        Float damage = damageConversionFunc.apply(e.getAmount());
        if (damage <= 0) {
            e.setCanceled(true);
        } else {
            e.setAmount(damage);
        }
    }

    /**
     * Speeds up ONLY CLIENT player. 1 - regular speed, 2 - 2 times faster, etc.
     * Algorythm tying to set the most speed value, can ignore it with force flag
     *
     * @param player          - player
     * @param speedMultiplier - Speed Multiplier 1 - regular speed, 2 - 2 times faster, etc. Negatives disables ability
     * @param force           - should force to set passed value to player
     */
    public static void speedUpPlayer(PlayerEntity player, float speedMultiplier, boolean force) {
        IAttribute speedAttr = SharedMonsterAttributes.MOVEMENT_SPEED;
        IAttributeInstance playerSpeedAttribute = player.getAttribute(speedAttr);
        AttributeModifier modifier = playerSpeedAttribute.getModifier(ARMOR_SPEED_UUID);

        // Detect if removing speed modifier
        boolean isRemove = speedMultiplier <= 1;

        // changing step height on client
        if (isRemote(player)) {
            player.stepHeight = isRemove ? 0.6F : 1.0625F;
        } else {
            // Change speed on server

            // removing speed modifier if can set faster
            if (force || modifier == null || modifier.getAmount() < speedMultiplier) {
                playerSpeedAttribute.removeModifier(ARMOR_SPEED_UUID);

                if (!isRemove) {
                    modifier = (new AttributeModifier(ARMOR_SPEED_UUID, "Armor speed modifier", speedMultiplier,
                            AttributeModifier.Operation.MULTIPLY_BASE));
                    playerSpeedAttribute.applyModifier(modifier);
                }
            }
        }
    }

    /**
     * Remove Armor speed modifier at all.
     *
     * @param player - player
     */
    public static void removeSpeed(PlayerEntity player) {
        speedUpPlayer(player, -1, true);
    }

    /**
     * Froze near mobs on server side
     *
     * @param e         - event
     * @param skipTicks - tick delay
     * @param radius    - radius of mobs should be frozen
     */
    public static void frozeNearMobs(TickEvent.PlayerTickEvent e, int skipTicks, int radius) {
        PlayerEntity player = e.player;
        World world = player.world;

        if (isRemote(player) || world.getGameTime() % skipTicks != 0) {
            return;
        }

        List<Entity> entities = player.world.getEntitiesWithinAABB(LivingEntity.class,
                player.getBoundingBox().grow(radius));

        for (Entity mob : entities) {
            ((LivingEntity) mob).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 1, true, true));
        }
    }

    /**
     * Should Speed up CLIENT SIDE player in water
     *
     * @param player - player
     * @param speed  - speed modifier
     */
    public static void speedUpInWater(PlayerEntity player, float speed) {
        if (player == null
                // Should call on client
                || !isRemote(player)
                || !player.isInWater()) {
            return;
        }

        Vec3d m = player.getMotion();
        double x = m.getX();
        double y = m.getY();
        double z = m.getZ();

        // Motion should determine by client
        // Server only receive position changed status
        if (!isMaxSpeed(x, speed)) {
            x *= speed;
        }
        if (!isMaxSpeed(z, speed)) {
            z *= speed;
        }

        if (!isMaxSpeed(y, speed)) {
            // max X/Z speed. If moving faster, should not change
            // Y cord
            double maxSpeed = 0.3D;

            // managing Y pos if sneaking or just come out the water
            // on full speed should stay on same Y level
            if (player.isSneaking() ||
                    x < maxSpeed && z < maxSpeed) {
                y *= speed;
            }
        }

        player.setMotion(x, y, z);
    }

    /**
     * Disabling fall damage on server side
     *
     * @param event - tick event
     */
    public static void disableFallDamage(TickEvent.PlayerTickEvent event) {
        if (!isRemote(event.player))
            event.player.fallDistance -= 0.5F;
    }

    /**
     * Implements infinite water breating on server side
     *
     * @param event - tick event
     */
    public static void breatheUnderwater(TickEvent.PlayerTickEvent event) {
        if (!isRemote(event.player) && event.player.world.getGameTime() % 30 == 0)
            event.player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING,
                    40, 0, true, false));
    }

    /**
     * Check wherever DamageSource is melee damage
     *
     * @param source - Damage source
     * @return true is it only melee damage
     */
    public static boolean isMeeleeDamage(DamageSource source) {
        return !source.isExplosion() && !source.isMagicDamage() && !source.isFireDamage() && !source.isProjectile()
                && !source.isDamageAbsolute() && !source.isUnblockable();
    }

    /**
     * Trying to heal entity
     *
     * @param e      - healing entity
     * @param amount - life points amount
     * @return - true is healed
     */
    public static boolean tryHeal(LivingEntity e, float amount) {
        if (e.getHealth() >= e.getMaxHealth())
            return false;

        e.heal(amount);
        return true;
    }

    /**
     * Tries to poison entity
     *
     * @param entity  - victim
     * @param seconds - seconds of poison effect
     */
    public static boolean tryPoison(Entity entity, int seconds) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, seconds * 20, 1));
            return true;
        }

        return false;
    }

    /**
     * Summon lightning
     *
     * @param playerEntity - player
     * @param pos          - on current position
     */
    public static void spawnLightning(PlayerEntity playerEntity, BlockPos pos) {
        if (!playerEntity.getEntityWorld().isRemote()
                && playerEntity instanceof ServerPlayerEntity) {
            LightningBoltEntity boltEntity = new LightningBoltEntity(playerEntity.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), false);
            boltEntity.setCaster(((ServerPlayerEntity) playerEntity));

            ((ServerWorld) playerEntity.getEntityWorld()).addLightningBolt(boltEntity);
        }
    }

    /**
     * increasing jump height for event
     *
     * @param event       - jump event
     * @param totalHeight - desired jump height in blocks (default is one)
     */
    public static void adjustJumpHeight(LivingEvent.LivingJumpEvent event, int totalHeight) {
        if (event != null && event.getEntityLiving() != null){
            event.getEntityLiving().addVelocity(0, totalHeight / 10F, 0);
        }
    }


    /**
     * Trying to check wherever player motion is more than passed maxSpeed
     *
     * @param motion - player motion (can be negative)
     * @param speed  - speed (always not negative)
     * @return is Player reached max speed
     */
    private static boolean isMaxSpeed(double motion, float speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be less than zero");
        }

        return !(motion > -speed && motion < speed);
    }

    /**
     * Checks wherever entity contains in remote world
     *
     * @param e - entity
     * @return is remote world
     */
    private static boolean isRemote(Entity e) {
        return e == null || e.world.isRemote;
    }
}
