package divinerpg.entities.vanilla.dramcryx.enthralled;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class EnthralledDramcryx extends DivineMonster {

    public EnthralledDramcryx(World world) {
        super(EntitiesRegistry.entrhralled_dramcryx, world, SoundRegistry.DRAMCRYX_HURT, SoundRegistry.DRAMCRYX, 1.25F);
    }

    public EnthralledDramcryx(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 16)
            return -1;

        return super.getBlockPathWeight(pos, worldIn);
    }
}
