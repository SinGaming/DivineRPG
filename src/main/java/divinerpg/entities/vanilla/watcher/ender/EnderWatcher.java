package divinerpg.entities.vanilla.watcher.ender;

import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EnderWatcher extends EndermanEntity {

    public EnderWatcher(EntityType<? extends EndermanEntity> p_i50210_1_, World world) {
        this(world);
    }

    public EnderWatcher(World world) {
        super(EntitiesRegistry.ender_watcher, world);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return super.getLootTable();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }
}
