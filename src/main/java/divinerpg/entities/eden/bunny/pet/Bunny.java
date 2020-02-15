package divinerpg.entities.eden.bunny.pet;

import divinerpg.entities.base.DivineWolf;
import divinerpg.entities.eden.bunny.angry.AngryBunny;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Bunny extends DivineWolf {
    public Bunny(World w) {
        super(EntitiesRegistry.bunny, w);
        experienceValue = 40;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 0.5F;
    }

    @Override
    protected float getWolfHealth() {
        return isTamed()
                ? 20
                : 10;
    }

    @Override
    protected float getAttack() {
        return 1;
    }

    @Override
    protected boolean isTamingItem(ItemStack stack) {
        return stack.getItem() == ItemRegistry.eden_sparkles;
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (world.isRemote() || isTamed())
            return;

        Entity angryBunny = createAngryBunny();
        angryBunny.setLocationAndAngles(this.serverPosX, this.serverPosY, this.serverPosZ, this.rotationYaw, this.rotationPitch);
        world.addEntity(angryBunny);

        remove();
    }

    private Entity createAngryBunny() {
        return new AngryBunny(world);
    }
}
