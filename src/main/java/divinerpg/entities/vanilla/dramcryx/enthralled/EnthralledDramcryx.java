package divinerpg.entities.vanilla.dramcryx.enthralled;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class EnthralledDramcryx extends DivineMonster {

    public EnthralledDramcryx(World world) {
        super(EntitiesRegistry.entrhralled_dramcryx, world, SoundRegistry.DRAMCRYX_HURT, SoundRegistry.DRAMCRYX, 1.25F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(80, 7);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 16)
            return -1;

        return super.getBlockPathWeight(pos, worldIn);
    }
}
