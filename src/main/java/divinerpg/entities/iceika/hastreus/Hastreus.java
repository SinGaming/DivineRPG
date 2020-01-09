package divinerpg.entities.iceika.hastreus;

import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class Hastreus extends DivineMonster {
    public Hastreus(World world) {
        super(EntitiesRegistry.hastreus, world, SoundRegistry.HASTREUS_HURT, SoundRegistry.HASTREUS, 1.3F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(80, 12);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().expand(5, 5, 5),
                player -> !player.isCreative() && player.isSpectator() && canEntityBeSeen(player))
                .forEach(x -> ArmorEvents.addEffect(x, Effects.SLOWNESS, 12, 18, true, false));

    }
}
