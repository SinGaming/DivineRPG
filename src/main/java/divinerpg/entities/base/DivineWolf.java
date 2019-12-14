package divinerpg.entities.base;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.IPacket;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

public abstract class DivineWolf extends WolfEntity {

    @Deprecated
    protected DivineWolf(World w) {
        this(EntityType.WOLF, w);
    }

    public DivineWolf(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.sitGoal = new SitGoal(this);

        if (canSeat())
            this.goalSelector.addGoal(2, this.sitGoal);

        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (item.isFood()) {
                    if (canAcceptFood(item.getFood()) && getMaxHealth() > getHealth()) {
                        if (!player.isCreative()) {
                            itemstack.shrink(1);
                        }

                        this.heal((float) item.getFood().getHealing());
                        return true;
                    }
                } else if (canBeTamable() && item instanceof DyeItem) {
                    DyeColor dyecolor = ((DyeItem) item).getDyeColor();
                    if (dyecolor != this.getCollarColor()) {
                        this.setCollarColor(dyecolor);
                        if (!player.isCreative()) {
                            itemstack.shrink(1);
                        }

                        return true;
                    }
                }
            }

            if (this.isOwner(player) && !this.world.isRemote() && !this.isBreedingItem(itemstack)) {
                this.sitGoal.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
            }
        } else if (canBeTamable() && isTamingItem(itemstack) && !this.isAngry()) {
            if (!player.isCreative()) {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(getTamingChance()) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget(null);
                    this.sitGoal.setSitting(true);
                    this.setHealth(getWolfHealth());
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }

            return true;
        }

        return processInteractOverride(player, hand);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getWolfHealth());
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttack());
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getWolfHealth());
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttack());
    }

    @Override
    public WolfEntity createChild(AgeableEntity ageable) {
        WolfEntity wolfentity = (WolfEntity) getType().create(this.world);
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            wolfentity.setOwnerId(uuid);
            wolfentity.setTamed(true);
        }

        return wolfentity;
    }

    @Override
    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        if (target instanceof CatEntity || target instanceof ParrotEntity) {
            return !((TameableEntity) target).isTamed();
        }

        if (target instanceof AbstractHorseEntity)
            return !((AbstractHorseEntity) target).isTame();

        if (target instanceof TameableEntity) {
            return ((TameableEntity) target).getOwner() != owner;
        }

        return target != owner;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !isTamed() && super.canDespawn(distanceToClosestPlayer);
    }

    ///////////////////////
    // Helping methods
    /////////////////////

    protected boolean processInteractOverride(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (this.isBreedingItem(itemstack)) {
            if (this.getGrowingAge() == 0 && this.canBreed()) {
                this.consumeItemFromStack(player, itemstack);
                this.setInLove(player);
                return true;
            }

            if (this.isChild()) {
                this.consumeItemFromStack(player, itemstack);
                this.ageUp((int) ((float) (-this.getGrowingAge() / 20) * 0.1F), true);
                return true;
            }
        }

        Item item = itemstack.getItem();
        if (item instanceof SpawnEggItem && ((SpawnEggItem) item).hasType(itemstack.getTag(), this.getType())) {
            if (!this.world.isRemote) {
                AgeableEntity ageableentity = this.createChild(this);
                if (ageableentity != null) {
                    ageableentity.setGrowingAge(-24000);
                    ageableentity.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                    this.world.addEntity(ageableentity);
                    if (itemstack.hasDisplayName()) {
                        ageableentity.setCustomName(itemstack.getDisplayName());
                    }

                    this.onChildSpawnFromEgg(player, ageableentity);
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected int getTamingChance() {
        return 3;
    }

    protected boolean isTamingItem(ItemStack stack) {
        return stack.getItem() == Items.BONE;
    }

    protected boolean canBeTamable() {
        return true;
    }

    protected float getWolfHealth() {
        return isTamed()
                ? 20
                : 8;
    }

    protected float getAttack() {
        return isTamed()
                ? 4
                : 2;
    }

    protected boolean canAcceptFood(Food food) {
        return food.isMeat();
    }

    protected boolean canSeat() {
        return true;
    }
}

