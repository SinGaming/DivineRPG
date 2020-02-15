package divinerpg.entities.wildwood.epiphite;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Epiphite extends PeacefullDivineMonster {
    public Epiphite(World world) {
        super(EntitiesRegistry.epiphite, world, SoundRegistry.GROWL_HURT, SoundRegistry.GROWL, 1.1F);

        experienceValue = 40;
    }

    public Epiphite(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(100, 14);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && world instanceof ServerWorld && rand.nextInt(5) == 0) {
            LightningBoltEntity boltEntity = new LightningBoltEntity(world, entityIn.serverPosX, entityIn.serverPosY, entityIn.serverPosZ, true);
            ((ServerWorld) world).addLightningBolt(boltEntity);
        }

        return result;
    }
}
