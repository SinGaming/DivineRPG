package divinerpg.api.armor;

import divinerpg.api.DivineAPI;
import divinerpg.registry.BlockRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Class contains helper methods for power armor
 */
public class ArmorEvents {

    private static final UUID ARMOR_SPEED_UUID = UUID.fromString("2ae05d96-4b26-420b-8406-156e8febb45f");
    private static final LazyLoadBase<List<net.minecraft.block.Block>> twilightOres = new LazyLoadBase<>(
            () -> Arrays.asList(BlockRegistry.edenOre, BlockRegistry.wildwoodOre, BlockRegistry.apalachiaOre, BlockRegistry.skythernOre, BlockRegistry.mortumOre));

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
    public static void onAddRangedDamage(LivingHurtEvent e, ResourceLocation armorId, Function<Float, Float> damageConversionFunc) {
        if (isRemote(e.getEntity()) || !(e.getSource().getTrueSource() instanceof PlayerEntity))
            return;

        DamageSource source = e.getSource();
        Entity entity = source.getTrueSource();

        // if ranged damage
        if (source.isProjectile() || source.getDamageType().equals("thrown")) {
            // if armor is on
            if (DivineAPI.isOn(entity, armorId)) {
                // set new amount
                e.setAmount(damageConversionFunc.apply(e.getAmount()));
            }
        }
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

        boolean isRemove = speedMultiplier <= 0;

        if (isRemote(player)) {
            player.stepHeight = isRemove ? 0.6F : 1.0625F;
        }

        speedUpAttribute(player, SharedMonsterAttributes.MOVEMENT_SPEED, speedMultiplier, force);
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

        List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(e.player,
                player.getBoundingBox().grow(radius));

        EffectInstance effectInstance = new EffectInstance(Effects.SLOWNESS, skipTicks * 2, 1, true, true);
        entities.stream().filter(x -> x instanceof LivingEntity).forEach(x -> ((LivingEntity) x).addPotionEffect(effectInstance));
    }

    /**
     * Should Speed up player in water
     *
     * @param player - player
     * @param speed  - increase speed
     * @param force  - is forced
     */
    public static void speedUpInWater(PlayerEntity player, float speed, boolean force) {
        speedUpAttribute(player, LivingEntity.SWIM_SPEED, speed, force);
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
        if (event != null && event.getEntityLiving() != null) {
            event.getEntityLiving().addVelocity(0, totalHeight / 10F, 0);
        }
    }

    /**
     * extinguish player and add fire resistance
     *
     * @param event
     */
    public static void removeFire(TickEvent.PlayerTickEvent event) {
        event.player.extinguish();
        event.player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 4));
    }

    /**
     * Increasing fragments drop values
     *
     * @param e
     */
    public static void increaseFragmentDrops(BlockEvent.HarvestDropsEvent e) {
        if (e.isSilkTouching() || e.getDrops().isEmpty())
            return;

        if (!twilightOres.getValue().contains(e.getState().getBlock()))
            return;

        ItemStack fragment = e.getDrops().get(0);

        if (!fragment.isEmpty())
            e.getDrops().addAll(Collections.nCopies(3, fragment.copy()));
    }

    /**
     * Add potion effect.
     *
     * @param entity - for entity
     * @param effect - current effect
     * @param values - Optional duration and amplifier values. 0 by default
     */
    public static void addEffect(Entity entity, Effect effect, int... values) {
        if (!(entity instanceof LivingEntity))
            return;

        int duration = 0;
        int amplifier = 0;

        if (values != null && values.length > 0) {
            duration = values[0];

            if (values.length > 1) {
                amplifier = values[1];
            }
        }

        ((LivingEntity) entity).addPotionEffect(new EffectInstance(effect, duration, amplifier));
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

    /**
     * speed up selected attribute
     *
     * @param player          - for player
     * @param attr            - increasing that attribute
     * @param speedMultiplier - 1 - regular speed, 2 - 2 times faster, etc.
     * @param force           - if false don't change if player already faster. Otherwise force new value
     */
    private static void speedUpAttribute(PlayerEntity player, IAttribute attr, float speedMultiplier, boolean force) {
        // getting instance
        IAttributeInstance attribute = player.getAttribute(attr);
        // get special divine modifier
        AttributeModifier modifier = attribute.getModifier(ARMOR_SPEED_UUID);

        // chech if removing
        boolean remove = speedMultiplier <= 0;

        // already deleted
        if (remove && modifier == null)
            return;

        // can't change
        if (!force && modifier != null && modifier.getAmount() >= speedMultiplier)
            return;

        // remove attribute
        if (modifier != null)
            attribute.removeModifier(modifier);

        // already deleted delete
        if (remove) {
            return;
        }

        // createing new modifier
        modifier = new AttributeModifier(ARMOR_SPEED_UUID, "Divine modifier", speedMultiplier, AttributeModifier.Operation.MULTIPLY_BASE);
        // and applying it
        attribute.applyModifier(modifier);
    }
}
