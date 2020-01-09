package divinerpg.entities.iceika.frosty;

import divinerpg.api.armor.ArmorEvents;
import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class Frosty extends PeacefullDivineMonster {
    public Frosty(World world) {
        super(EntitiesRegistry.frosty, world, SoundRegistry.FROSTY_HURT, SoundRegistry.FROSTY, 1.85F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(150, 20);

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3 / 1.6);
        this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result){
            ArmorEvents.addEffect(entityIn, Effects.SLOWNESS, 100, 2, true, false);
        }

        return result;
    }
}
