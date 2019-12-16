package divinerpg.entities.skythern.samek;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class Samek extends DivineMonster {
    public Samek(World world) {
        super(EntitiesRegistry.samek, world, SoundRegistry.VEREK_HURT, SoundRegistry.VEREK, 1.7F);

        experienceValue = 40;
    }

    public Samek(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(160, 20);
    }
}
