package divinerpg.tile.furnace;

import divinerpg.registry.TileEntityRegistry;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class DivineFurnaceTileEntity extends AbstractFurnaceTileEntity {
    // 1 slot is the fuel

    public DivineFurnaceTileEntity() {
        super(TileEntityRegistry.infinite_furnace, IRecipeType.SMELTING);
    }

    @Override
    public void tick() {
        boolean wasBurning = this.isBurning();
        boolean statusChanged = false;
        if (this.isBurning()) {
            setBurnTime(getBurnTime() - 1);
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.items.get(1);
            if (!this.items.get(0).isEmpty()) {
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
                    cookTick();
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
        return func_214005_h();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 0;
    }

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

    private void cookTick() {
        int cookTime = getCookTime();

        int descrease = 1;

        Material material = world.getBlockState(pos.down()).getMaterial();

        if (material == Material.FIRE)
            descrease = 2;

        if (material == Material.LAVA)
            descrease = 3;

        setCookTime(cookTime + descrease);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.furnace");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new DivineFurnaceContainer(id, player, this, this.furnaceData);
    }
}
