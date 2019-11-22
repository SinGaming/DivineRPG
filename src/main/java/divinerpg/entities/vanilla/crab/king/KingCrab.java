package divinerpg.entities.vanilla.crab.king;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class KingCrab extends PeacefullDivineMonster {
    public KingCrab(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public KingCrab(World world) {
        super(EntitiesRegistry.king_crab, world, SoundRegistry.CRAB_HURT, SoundRegistry.CRAB, 1);
        experienceValue = 40;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
    }
}
