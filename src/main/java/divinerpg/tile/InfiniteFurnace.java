package divinerpg.tile;

import divinerpg.utils.time.FurnaceArbiter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class InfiniteFurnace extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private final IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn;
    protected int[] inputs;
    protected int[] outputs;
    protected NonNullList<ItemStack> items;
    private FurnaceArbiter arbiter;

    public InfiniteFurnace(TileEntityType<?> typeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
        super(typeIn);
        this.recipeTypeIn = recipeTypeIn;
        arbiter = new FurnaceArbiter();
        setSize(2, 1);
    }

    /**
     * Set size of furnace
     *
     * @param totalSlots   - total furnace slots
     * @param outputsCount - input slots count. Others will be for outputs
     */
    protected void setSize(int totalSlots, int outputsCount) {
        items = NonNullList.withSize(totalSlots, ItemStack.EMPTY);

        inputs = IntStream.range(0, outputsCount).toArray();
        outputs = IntStream.range(outputsCount, items.size()).toArray();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return outputs;
        }

        return inputs;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return direction != Direction.DOWN || ArrayUtils.contains(outputs, index);
    }

    @Override
    public int getSizeInventory() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        items.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        // todo

        return null;
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe == null)
            return;

        // todo
    }

    @Override
    public void tick() {
        Float maxSmeltTime = Arrays.stream(inputs).mapToObj(x -> getBurnTime(getStackInSlot(x))).max(Comparator.naturalOrder()).orElse(-1F);
        IRecipe<?> irecipe = null;

        if (!arbiter.isBurning() && maxSmeltTime >= 0) {
            irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeTypeIn, this, this.world).orElse(null);
            if (canSmelt(irecipe)) {
                arbiter.setReceipe((int) Math.ceil(maxSmeltTime));
            }
        }

        if (arbiter.isBurning() && canSmelt(irecipe)) {
            if (arbiter.heat()) {
                populateOutput(irecipe);
            }
        } else {
            // Do not need to notify
            if (!arbiter.cooldown())
                return;
        }

        markDirty();
    }

    public float getBurnTime(ItemStack stack) {
        int burnTime = stack.isEmpty()
                ? 0
                : ForgeHooks.getBurnTime(stack);

        return burnTime * getMultiplier();
    }

    protected float getMultiplier() {
        Material material = world.getBlockState(pos.down()).getMaterial();

        if (material == Material.LAVA)
            return 0.5F;

        if (material == Material.FIRE)
            return 0.7F;

        return 1;
    }

    protected boolean canSmelt(IRecipe recipe) {
        if (recipe == null)
            return false;

        ItemStack output = recipe.getRecipeOutput();
        if (output.isEmpty())
            return false;

        // required amount
        Integer sum = Arrays.stream(inputs).mapToObj(this::getStackInSlot).filter(x -> !x.isEmpty()).map(x -> x.getCount() + output.getCount()).reduce(0, (l, r) -> l + r);
        // max stack size
        int maxStackSize = getMaxStackSize(output);

        Integer freeItems = Arrays.stream(outputs).mapToObj(this::getStackInSlot).filter(x -> x.isEmpty() || ItemStack.areItemsEqual(x, output))
                .map(x -> maxStackSize - x.getCount()).reduce(0, (l, r) -> l + r);

        return freeItems >= sum;
    }

    protected void populateOutput(IRecipe<?> irecipe) {
        // unknown receipe
        if (irecipe == null)
            return;

        ItemStack output = irecipe.getRecipeOutput();
        // unknown result
        if (output.isEmpty())
            return;

        // get all input clots
        List<ItemStack> inputStacks = Arrays.stream(inputs).mapToObj(this::getStackInSlot).filter(x -> !x.isEmpty()).collect(Collectors.toList());
        if (inputStacks.isEmpty())
            return;

        // get max stack size
        int maxStackSize = getMaxStackSize(output);
        // total result count
        int totalOut = output.getCount() * inputStacks.size();

        // find all available result slots
        List<ItemStack> outputStacks = Arrays.stream(outputs).mapToObj(this::getStackInSlot).filter(x -> x.isEmpty() || x.getCount() < maxStackSize).collect(Collectors.toList());

        for (ItemStack stack : outputStacks) {
            // trying to fit-in in every possible stack limit
            int left = maxStackSize - stack.getCount();
            totalOut -= left;
            stack.setCount(stack.getCount() + left);

            // already filled all
            if (totalOut <= 0)
                break;
        }

        // shrink all items
        inputStacks.forEach(x -> x.shrink(1));
    }

    protected int getMaxStackSize(ItemStack stack) {
        return Math.min(stack.getMaxStackSize(), this.getInventoryStackLimit());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        ItemStackHelper.loadAllItems(compound, items);
        arbiter.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStackHelper.saveAllItems(compound, items);
        arbiter.write(compound);
        return super.write(compound);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (ArrayUtils.contains(outputs, index)) {
            return false;
        }

        return Arrays.stream(this.inputs).mapToObj(this::getStackInSlot)
                // all slots are empty
                .allMatch(x -> x.isEmpty()
                        // or contains same item and not full
                        || ItemStack.areItemsEqual(x, stack) && x.getCount() < getMaxStackSize(x));
    }
}
