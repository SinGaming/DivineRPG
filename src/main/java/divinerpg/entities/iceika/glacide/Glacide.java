package divinerpg.entities.iceika.glacide;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Glacide extends DivineMonster {
    public Glacide(World world) {
        super(EntitiesRegistry.glacide, world, SoundRegistry.GLACIDE_HURT, SoundRegistry.GLACIDE, 1.8F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        initAttr(80, 12);

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D * 1.6D);
    }
}
