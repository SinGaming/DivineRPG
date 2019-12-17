package divinerpg.entities.vanilla.crab.king;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.world.World;

public class KingCrab extends PeacefullDivineMonster {

    public KingCrab(World world) {
        super(EntitiesRegistry.king_crab, world, SoundRegistry.CRAB_HURT, SoundRegistry.CRAB, 1);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(45, 6);
    }
}
