package divinerpg.entities.twilight.samek.entities;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class Verek extends DivineMonster {
    public Verek(World world) {
        super(EntitiesRegistry.verek, world, SoundRegistry.VEREK_HURT, SoundRegistry.VEREK, 1.8F);
        experienceValue = 40;
    }

    public Verek(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(50, 12, 6);
    }
}
