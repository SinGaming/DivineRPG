package divinerpg.entities.vanilla.pig;

import divinerpg.entities.base.DivineWolf;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class HellPig extends DivineWolf {
    public HellPig(World w) {
        super(EntitiesRegistry.hell_pig, w);
    }

    public HellPig(EntityType<? extends Entity> type, World worldIn) {
        this(worldIn);
    }

    @Override
    protected boolean canSeat() {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.ROTTEN_FLESH;
    }

    @Override
    protected boolean isTamingItem(ItemStack stack) {
        return stack.getItem() == Items.BLAZE_POWDER;
    }

    @Override
    protected float getWolfHealth() {
        return isTamed()
                ? 80
                : 50;
    }

    @Override
    protected float getAttack() {
        switch (world.getDifficulty()) {
            case HARD:
                return 7;

            case NORMAL:
                return 5;

            default:
                return 3;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            entityIn.setFire(3);
            return true;
        }

        return false;
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND) {
            return super.getBlockPathWeight(pos, worldIn);
        }

        return -1;
    }
}
