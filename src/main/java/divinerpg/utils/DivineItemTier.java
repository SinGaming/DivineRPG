package divinerpg.utils;

import divinerpg.registry.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public class DivineItemTier implements IItemTier {
    public static final DivineItemTier REALMIT = new DivineItemTier(3, 4000, 12.0F, 6.0F,
            10, () -> Ingredient.fromItems(ItemRegistry.realmiteIngot));
    public static final DivineItemTier ARLEMIT = new DivineItemTier(3, 3000, 13.0F, 6.0F,
            10, () -> Ingredient.fromItems(ItemRegistry.arlemiteIngot));
    public static final DivineItemTier RUPEE = new DivineItemTier(3, 2500, 16.0F, 7.0F,
            10, () -> Ingredient.fromItems(ItemRegistry.rupeeIngot));
    public static final DivineItemTier CORRUPTED = new DivineItemTier(3, 6000, 16, 5.0F,
            15, () -> Ingredient.fromItems(ItemRegistry.corruptedStone));
    public static final DivineItemTier PALAVENCE = new DivineItemTier(3, 60, 1, 0,
            15, Ingredient::fromItems);

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;

    public DivineItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
    }

    public DivineItemTier forShickaxe() {
        return new DivineItemTier(harvestLevel, maxUses * 4, efficiency, attackDamage, (int) (enchantability * 1.5), repairMaterial::getValue);
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }
}
