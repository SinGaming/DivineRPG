package divinerpg.entities.vanilla.rotatick;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Rotatick extends DivineMonster {
    public Rotatick(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public Rotatick(World world) {
        super(EntitiesRegistry.rotatick, world, SoundRegistry.ROTATICK_HURT, SoundRegistry.ROTATICK, 0.75F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(40, 6);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 25) {
            return -1;
        }

        return super.getBlockPathWeight(pos, worldIn);
    }
}
