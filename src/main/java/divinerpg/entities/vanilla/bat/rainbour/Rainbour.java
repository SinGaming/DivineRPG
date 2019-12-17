package divinerpg.entities.vanilla.bat.rainbour;

import divinerpg.entities.base.DivineBat;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class Rainbour extends DivineBat {
    public Rainbour(World world) {
        super(EntitiesRegistry.rainbour, world, SoundRegistry.RAINBOUR_HURT, SoundRegistry.RAINBOUR);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(18);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        // TODO spawn particle
    }
}
