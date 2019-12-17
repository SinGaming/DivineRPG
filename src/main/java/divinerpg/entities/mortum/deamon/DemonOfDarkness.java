package divinerpg.entities.mortum.deamon;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.world.World;

public class DemonOfDarkness extends DivineMonster {
    public DemonOfDarkness(World world) {
        super(EntitiesRegistry.demon_of_darkness, world, SoundRegistry.GROWL_HURT, SoundRegistry.DEMON_OF_DARKNESS, 0.95F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(200,17,10);
    }
}
