package divinerpg.utils;

import divinerpg.registry.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public class DivineItemTier implements IItemTier {
    public static final DivineItemTier UNREPAIRABLE = new DivineItemTier(3, 1, 1, 0,
            15, () -> Ingredient.EMPTY);

    public static final DivineItemTier REALMIT = new DivineItemTier(3, 4000, 12.0F, 3,
            10, () -> Ingredient.fromItems(ItemRegistry.realmiteIngot));
    public static final DivineItemTier ARLEMIT = new DivineItemTier(3, 3000, 13.0F, 6.0F,
            10, () -> Ingredient.fromItems(ItemRegistry.arlemiteIngot));
    public static final DivineItemTier RUPEE = new DivineItemTier(3, 2500, 16.0F, 7.0F,
            10, () -> Ingredient.fromItems(ItemRegistry.rupeeIngot));
    public static final DivineItemTier CORRUPTED = new DivineItemTier(3, 6000, 16, 5.0F,
            15, () -> Ingredient.fromItems(ItemRegistry.corruptedStone));
    public static final DivineItemTier SLIME = new DivineItemTier(3, 1000, 7, 11,
            22, () -> Ingredient.fromItems(Items.DIAMOND_SWORD));
    public static final DivineItemTier TERRAN = new DivineItemTier(3, 750, 7, 12,
            10, () -> Ingredient.fromItems(ItemRegistry.terranStone));
    public static final DivineItemTier OCEAN = new DivineItemTier(3, 2000, 7, 10,
            15, () -> Ingredient.fromItems(ItemRegistry.aquaticIngot));
    public static final DivineItemTier MOLTEN = new DivineItemTier(3, 5000, 7, 10,
            15, () -> Ingredient.fromItems(ItemRegistry.moltenStone));
    public static final DivineItemTier SHADOW = new DivineItemTier(3, 10000, 7, 26,
            22, () -> Ingredient.fromItems(ItemRegistry.shadowStone));
    public static final DivineItemTier SHADOWBAR = new DivineItemTier(3, 3000, 7, 20,
            15, () -> Ingredient.fromItems(ItemRegistry.shadowBar));
    public static final DivineItemTier JUNGLE = new DivineItemTier(3, 1200, 7, 12,
            10, () -> Ingredient.fromItems(ItemRegistry.jungleStone));
    public static final DivineItemTier GLACIER = new DivineItemTier(3, 1000, 7, 18,
            15, () -> Ingredient.fromItems(ItemRegistry.snowFlake));
    public static final DivineItemTier CYCLOP = new DivineItemTier(3, 1000, 7, 12,
            10, () -> Ingredient.fromItems(ItemRegistry.cyclops_eye));
    public static final DivineItemTier BEDROCK = new DivineItemTier(3, 14000, 20, 14,
            22, () -> Ingredient.fromItems(ItemRegistry.bedrock_chunk));
    public static final DivineItemTier DIVINE = new DivineItemTier(3, 35000, 20, 10,
            22, () -> Ingredient.fromItems(ItemRegistry.divineStone));
    public static final DivineItemTier FROST = new DivineItemTier(3, 5000, 7, 10,
            15, () -> Ingredient.fromItems(ItemRegistry.iceStone));
    public static final DivineItemTier AQUATOOTH = new DivineItemTier(3, 3500, 7, 15,
            10, () -> Ingredient.fromItems(ItemRegistry.liopleurodonSkull));
    public static final DivineItemTier CRAB_CLAW = new DivineItemTier(3, 4000, 7, 11,
            15, () -> Ingredient.fromItems(ItemRegistry.crabClaw));
    public static final DivineItemTier SCORCHING = new DivineItemTier(3, 500, 7, 20,
            15, () -> Ingredient.fromItems(ItemRegistry.purpleBlaze));
    public static final DivineItemTier ENDER = new DivineItemTier(3, -1, 7, 16,
            16, () -> Ingredient.fromItems(ItemRegistry.enderStone));


    public static final DivineItemTier EDEN = new DivineItemTier(3, 6000, 20, 12, //(22)
            22, () -> Ingredient.fromItems(ItemRegistry.edenGem));
    public static final DivineItemTier WILDWOOD = new DivineItemTier(3, 6200, 28, 14, //(24)
            22, () -> Ingredient.fromItems(ItemRegistry.wildwoodGem));
    public static final DivineItemTier APALACHIA = new DivineItemTier(3, 6400, 40, 19, //(29)
            22, () -> Ingredient.fromItems(ItemRegistry.apalachiaGem));
    public static final DivineItemTier SKYTHERN = new DivineItemTier(3, 6800, 48, 21, //(31)
            22, () -> Ingredient.fromItems(ItemRegistry.skythernGem));
    public static final DivineItemTier MORTUM = new DivineItemTier(3, 7000, 56, 23, //(33)
            22, () -> Ingredient.fromItems(ItemRegistry.mortumGem));
    public static final DivineItemTier HALITE = new DivineItemTier(3, 7000, 56, 26, //(36)
            22, () -> Ingredient.fromItems(ItemRegistry.mortumGem));

    public static final DivineItemTier SNOWFLAKE = new DivineItemTier(3, 1000, 12, 18,
            10, () -> Ingredient.fromItems(ItemRegistry.snowFlake));


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
        return new DivineItemTier(harvestLevel, maxUses * 3, efficiency, attackDamage, (int) (enchantability * 1.5), repairMaterial::getValue);
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
