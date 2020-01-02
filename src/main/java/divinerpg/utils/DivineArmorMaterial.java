package divinerpg.utils;

import divinerpg.DivineRPG;
import divinerpg.registry.ItemRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.function.Supplier;

public class DivineArmorMaterial implements IArmorMaterial {
    public static final DivineArmorMaterial JACK_O_MAN = new DivineArmorMaterial("jack_o_man", -1, 7, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, () -> Ingredient.EMPTY);
    public static final DivineArmorMaterial HALITE = new DivineArmorMaterial("halite", -1, 40, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 20, () -> Ingredient.EMPTY);
    public static final DivineArmorMaterial DIVINE = new DivineArmorMaterial("divine", 11500, 37, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.fromItems(ItemRegistry.divineStone));
    public static final DivineArmorMaterial BEDROCK = new DivineArmorMaterial("bedrock", 10000, 37, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.fromItems(ItemRegistry.bedrock_chunk));
    public static final DivineArmorMaterial REALMIT = new DivineArmorMaterial("realmite", 5000, 24, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.realmiteIngot));
    public static final DivineArmorMaterial ELITE_REALMIT = new DivineArmorMaterial("elite_realmite", -1, 29, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.realmiteIngot));
    public static final DivineArmorMaterial ARLEMITE = new DivineArmorMaterial("arlemite", -1, 24, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.arlemiteIngot));
    public static final DivineArmorMaterial KRAKEN = new DivineArmorMaterial("kraken", 5000, 20, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 10, () -> Ingredient.fromItems(ItemRegistry.kraken_skin));
    public static final DivineArmorMaterial INFERNO = new DivineArmorMaterial("inferno", 6500, 35, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.EMPTY);

    /**
     * @param name                   - name of armor material
     * @param chestplateDamage       - amount of chestplate damage. Others parts will be calculated by resources, needed to createFireball armor piece
     * @param totalDefense           - amount of armor points. Diamond have 20 points that gives 80% defence
     * @param enchantabilityIn       - enchantability. 25 for gold, 10 by default
     * @param onEquip                - sound on equip
     * @param toughness              - toughness. 2 for Diamond
     * @param repairMaterialSupplier - reparing material
     */
    public DivineArmorMaterial(String name, int chestplateDamage, int totalDefense, int enchantabilityIn, SoundEvent onEquip, float toughness, Supplier<Ingredient> repairMaterialSupplier) {
        MAX_DAMAGE_ARRAY = new int[]{4, 7, 8, 5};
        this.damageReductionAmountArray = new int[]{0, 0, 0, 0};

        // damage by single ingot
        this.chestplateDamage = chestplateDamage / 8F;
        this.name = new ResourceLocation(DivineRPG.MODID, name).toString();
        this.enchantability = enchantabilityIn;
        this.soundEvent = onEquip;
        this.toughness = toughness;
        this.repairMaterial = new LazyLoadBase<>(repairMaterialSupplier);

        if (totalDefense < 1)
            return;

        // Total amount of ingots
        float defenceByIngot = (float) totalDefense / Arrays.stream(MAX_DAMAGE_ARRAY).sum();

        for (int i = 0; i < MAX_DAMAGE_ARRAY.length; i++) {
            int defencePoints = (int) Math.floor(defenceByIngot * MAX_DAMAGE_ARRAY[i]);
            damageReductionAmountArray[i] = defencePoints;
            totalDefense -= defencePoints;
        }

        // Some troubles while floor numbers
        if (totalDefense != 0) {
            damageReductionAmountArray[2] += totalDefense;
        }
    }

    private final int[] MAX_DAMAGE_ARRAY;
    public final String name;
    private final float chestplateDamage;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> repairMaterial;

    public static DivineArmorMaterial forRupee(String color) {
        return new DivineArmorMaterial(color + "rupee", -1, 20, 15,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, () -> Ingredient.fromItems(ItemRegistry.rupeeIngot));
    }

    public static DivineArmorMaterial forEnder(String color) {
        return new DivineArmorMaterial(color + "ender", 7500, 24, 15,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, () -> Ingredient.fromItems(ItemRegistry.enderStone));
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return (int) (MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.chestplateDamage);
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }
}
