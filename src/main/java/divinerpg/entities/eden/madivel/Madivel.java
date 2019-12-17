package divinerpg.entities.eden.madivel;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.world.World;

public class Madivel extends DivineMonster {
    public Madivel(World world) {
        super(EntitiesRegistry.madivel, world, SoundRegistry.MADIVEL_HURT, SoundRegistry.MADIVEL, 2.6F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(150, 18);
    }
}
