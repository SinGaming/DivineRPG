package divinerpg.entities.vanilla.cyclop.regular;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Cyclops extends PeacefullDivineMonster {
    public Cyclops(World world) {
        super(EntitiesRegistry.cyclops, world, SoundRegistry.CYCLOPS_HURT, SoundRegistry.CYCLOPS, 3.5F);
        this.experienceValue = 40;
    }

    public Cyclops(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        // Can spawn above sea
        if (pos.getY() < worldIn.getSeaLevel()) {
            return -1;
        }

        return super.getBlockPathWeight(pos, worldIn);
    }
}
