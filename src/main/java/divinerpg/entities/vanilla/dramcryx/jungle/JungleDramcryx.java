package divinerpg.entities.vanilla.dramcryx.jungle;

import divinerpg.api.armor.ArmorEvents;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class JungleDramcryx extends MonsterEntity {
    public JungleDramcryx(World world) {
        this(EntitiesRegistry.jungle_dramcryx, world);
    }

    public JungleDramcryx(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.DRAMCRYX;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.DRAMCRYX_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return getDeathSound();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn) || ArmorEvents.tryPoison(entityIn, 1);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return worldIn.getDimension().isSurfaceWorld()
                && BiomeDictionary.hasType(worldIn.getBiome(this.getPosition()), BiomeDictionary.Type.JUNGLE)
                && super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        int i = 0;
        this.targetSelector.addGoal(i++, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        i = 0;
        goalSelector.addGoal(i++, new MeleeAttackGoal(this, 1, false));
        goalSelector.addGoal(i++, new WaterAvoidingRandomWalkingGoal(this, 1));
        goalSelector.addGoal(i++, new LookAtGoal(this, PlayerEntity.class, 20.0F));
        goalSelector.addGoal(i++, new RandomWalkingGoal(this, 1.0D));
    }
}
