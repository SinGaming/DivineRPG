package divinerpg.tile.furnace;

import divinerpg.registry.TileEntityRegistry;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class DivineFurnaceTileEntity extends AbstractFurnaceTileEntity {
    // 1 slot is the fuel
    private TextComponent name;
    private float cookTimeSpeedModifier;
    private boolean isInfinite = true;

    public DivineFurnaceTileEntity() {
        this("container.furnace", 1, true);
    }

    public DivineFurnaceTileEntity(String name, float cookTimeSpeedModifier, boolean isInfinite) {
        super(TileEntityRegistry.infinite_furnace, IRecipeType.SMELTING);
        this.name = new TranslationTextComponent(name);
        this.cookTimeSpeedModifier = cookTimeSpeedModifier;
        this.isInfinite = isInfinite;
    }

    @Override
    public void tick() {
        boolean wasBurning = this.isBurning();
        boolean statusChanged = false;
        int tickCount = getTickIncrease();

        if (this.isBurning()) {
            setBurnTime(getBurnTime() - tickCount);
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.items.get(1);
            if (!this.items.get(0).isEmpty() && haveFuel()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    setBurnTime(getBurnTime(itemstack));
                    setRecipesUsed(getBurnTime());
                    if (this.isBurning()) {
                        statusChanged = true;

                        shrinkFuel(itemstack);
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    setCookTime(getCookTime() + tickCount);
                    if (getCookTime() >= getCookTimeTotal()) {
                        setCookTime(0);
                        setCookTimeTotal(func_214005_h());
                        this.populateRecipe(irecipe);
                        statusChanged = true;
                    }
                } else {
                    setCookTime(0);
                }
            } else if (!this.isBurning() && getCookTime() > 0) {
                setCookTime(MathHelper.clamp(getCookTime() - 2, 0, getCookTimeTotal()));
            }

            if (wasBurning != this.isBurning()) {
                statusChanged = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
            }
        }

        if (statusChanged) {
            this.markDirty();
        }
    }

    @Override
    protected int getBurnTime(ItemStack stack) {
        return needFuel()
                ? super.getBurnTime(stack)
                : func_214005_h();
    }

    @Override
    protected int func_214005_h() {
        // takes less time to cook item
        return (int) Math.floor(super.func_214005_h() * cookTimeSpeedModifier);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT result = super.write(compound);
        result.putFloat("cookTimeSpeedModifier", cookTimeSpeedModifier);
        result.putString("defaultName", getDefaultName().getString());
        result.putBoolean("isInfinite", isInfinite);
        return result;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        cookTimeSpeedModifier = compound.getFloat("cookTimeSpeedModifier");
        name = new TranslationTextComponent(compound.getString("defaultName"));
        isInfinite = compound.getBoolean("isInfinite");
    }

    // region helping methods

    protected void populateRecipe(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack ingredientStack = this.items.get(0);
            ItemStack output = recipe.getRecipeOutput();
            ItemStack resultStack = this.items.get(2);
            if (resultStack.isEmpty()) {
                this.items.set(2, output.copy());
            } else if (resultStack.getItem() == output.getItem()) {
                resultStack.grow(output.getCount());
            }

            if (!this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            if (ingredientStack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            ingredientStack.shrink(1);
        }
    }

    protected boolean isBurning() {
        return getBurnTime() > 0;
    }

    protected int getBurnTime() {
        return furnaceData.get(0);
    }

    protected void setBurnTime(int value) {
        furnaceData.set(0, value);
    }

    protected int getRecipesUsed() {
        return furnaceData.get(1);
    }

    protected void setRecipesUsed(int value) {
        furnaceData.set(1, value);
    }

    protected int getCookTime() {
        return furnaceData.get(2);
    }

    protected void setCookTime(int value) {
        furnaceData.set(2, value);
    }

    protected int getCookTimeTotal() {
        return furnaceData.get(3);
    }

    protected void setCookTimeTotal(int value) {
        furnaceData.set(3, value);
    }

    protected void shrinkFuel(ItemStack stack) {
        if (stack.hasContainerItem())
            this.items.set(1, stack.getContainerItem());
        else if (!stack.isEmpty()) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                this.items.set(1, stack.getContainerItem());
            }
        }
    }

    /**
     * Gets tick modifier based on ground material (lava or fire)
     *
     * @return
     */
    private int getTickIncrease() {
        BlockState state = world.getBlockState(pos.down());

        // source increase by 3 times
        if (state.getBlock() == Blocks.LAVA && ((FlowingFluidBlock) state.getBlock()).getFluidState(state).isSource()) {
            return 3;
            // fire or floating lava - 2 times
        } else if (state.getMaterial() == Material.FIRE || state.getBlock() == Blocks.LAVA) {
            return 2;
        }

        return 1;
    }

    /**
     * Checks wherever furnace needs fuel
     *
     * @return
     */
    private boolean haveFuel() {
        if (isInfinite)
            return true;

        if (isBurning())
            return true;

        return !items.get(1).isEmpty();
    }

    /**
     * Is furnace infinite
     *
     * @return
     */
    public boolean needFuel() {
        return !isInfinite;
    }

    // endregion

    @Override
    protected ITextComponent getDefaultName() {
        return name;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new DivineFurnaceContainer(id, player, this, this.furnaceData);
    }

}
