package divinerpg.entities.vanilla.miner;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Miner extends DivineMonster {
    public Miner(World world) {
        super(EntitiesRegistry.miner, world, SoundEvents.ENTITY_ZOMBIE_HURT, SoundEvents.ENTITY_ZOMBIE_AMBIENT,
                1.725F);
    }

    public Miner(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        goalSelector.addGoal(1, new BreakDoorGoal(this, a -> true));
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        ILivingEntityData result = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        setEquipmentBasedOnDifficulty(difficultyIn);
        return result;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        Item pickaxe = rand.nextInt(7) == 0
                ? Items.DIAMOND_PICKAXE
                : rand.nextInt(5) == 0
                ? Items.IRON_PICKAXE
                : rand.nextInt(3) == 0
                ? Items.STONE_PICKAXE
                : Items.WOODEN_PICKAXE;
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(pickaxe));
        this.setEnchantmentBasedOnDifficulty(difficulty);
    }

    @Override
    public void livingTick() {
        // TAKEN FROM ZOMBIE
        if (this.isAlive()) {
            boolean canBurn = this.isInDaylight();
            if (canBurn) {
                ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageable()) {
                        itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
                        if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
                            this.sendBreakAnimation(EquipmentSlotType.HEAD);
                            this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }

                    canBurn = false;
                }

                if (canBurn) {
                    this.setFire(8);
                }
            }
        }

        super.livingTick();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (isBurning()) {
                entityIn.setFire(2);
            }

            return true;
        }

        return false;
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);

        if (source.getTrueSource() instanceof PlayerEntity && rand.nextInt(1000) + looting * 10 < 25) {
            ItemStack pickaxe = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!pickaxe.isEmpty())
                world.addEntity(new ItemEntity(world, posX, posY, posZ, pickaxe));
        }
    }
}
