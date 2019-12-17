package divinerpg.entities.eden.bunny.angry;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.world.World;

public class AngryBunny extends DivineMonster {
    public AngryBunny(World world) {
        super(EntitiesRegistry.angry_bunny, world, SoundRegistry.GROWL_HURT, SoundRegistry.HISS, 1.4F);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(60, 12);
    }


}
