package divinerpg.utils;

import divinerpg.DivineRPG;
import divinerpg.registry.ItemRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class DivineArmorMaterial implements IArmorMaterial {
    public static final DivineArmorMaterial JACK_O_MAN = new DivineArmorMaterial("jack_o_man", -1, 7, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.skythle.increase", 3));

    public static final DivineArmorMaterial WITHER_REAPER = new DivineArmorMaterial("wither_reaper", -1, 7, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.wither.protection"));

    public static final DivineArmorMaterial SKELEMAN = new DivineArmorMaterial("skeleman", -1, 7, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, () -> Ingredient.EMPTY,
            TooltipUtil.noHunger());

    public static final DivineArmorMaterial HALITE = new DivineArmorMaterial("halite", -1, 40, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 20, () -> Ingredient.EMPTY,
            TooltipUtil.addMeleeDamage(6));

    public static final DivineArmorMaterial DIVINE = new DivineArmorMaterial("divine", 11500, 37, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.fromItems(ItemRegistry.divineStone),
            TooltipUtil.addMeleeDamage(2),
            TooltipUtil.jumpIncrease(2),
            TooltipUtil.noFall());

    public static final DivineArmorMaterial BEDROCK = new DivineArmorMaterial("bedrock", 10000, 37, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.fromItems(ItemRegistry.bedrock_chunk),
            TooltipUtil.fireProtect(),
            TooltipUtil.exposionProtection());

    public static final DivineArmorMaterial REALMIT = new DivineArmorMaterial("realmite", 5000, 24, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.realmiteIngot));

    public static final DivineArmorMaterial AQUASTRIVE = new DivineArmorMaterial("aquastrive", -1, 29, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.EMPTY,
            TooltipUtil.i18n("tooltip.effect.swim"),
            TooltipUtil.i18n("tooltip.effect.underwater"));

    public static final DivineArmorMaterial ELITE_REALMIT = new DivineArmorMaterial("elite_realmite", -1, 29, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.EMPTY,
            TooltipUtil.noFall());

    public static final DivineArmorMaterial ARLEMITE = new DivineArmorMaterial("arlemite", -1, 24, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.arlemiteIngot),
            TooltipUtil.i18n("tooltip.effect.ranged.protection", 85));

    public static final DivineArmorMaterial KRAKEN = new DivineArmorMaterial("kraken", 5000, 20, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 10, () -> Ingredient.fromItems(ItemRegistry.kraken_skin),
            TooltipUtil.i18n("tooltip.effect.underwater"));

    public static final DivineArmorMaterial INFERNO = new DivineArmorMaterial("inferno", 6500, 35, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.EMPTY,
            TooltipUtil.fireProtect());

    public static final DivineArmorMaterial SHADOW = new DivineArmorMaterial("shadow", -1, 37, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.EMPTY,
            TooltipUtil.armorSpeed(2.5));

    public static final DivineArmorMaterial NETHERITE = new DivineArmorMaterial("netherite", 5000, 34, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 14, () -> Ingredient.fromItems(ItemRegistry.netheriteIngot),
            TooltipUtil.fireProtect());

    public static final DivineArmorMaterial JUNGLE = new DivineArmorMaterial("jungle", 5000, 27, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.jungleStone),
            TooltipUtil.i18n("tooltip.effect.magic.protection"));

    public static final DivineArmorMaterial FROZEN = new DivineArmorMaterial("frozen", 3000, 27, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.iceStone),
            TooltipUtil.i18n("tooltip.effect.freeze", 6));

    public static final DivineArmorMaterial CORRUPTED = new DivineArmorMaterial("corrupted", 5000, 29, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.corruptedStone),
            TooltipUtil.i18n("tooltip.effect.ranged.increase", 50));

    public static final DivineArmorMaterial TERRAN = new DivineArmorMaterial("terran", 3000, 29, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10, () -> Ingredient.fromItems(ItemRegistry.terranStone),
            TooltipUtil.i18n("tooltip.effect.haste"));

    public static final DivineArmorMaterial EDEN = new DivineArmorMaterial("eden", -1, 30, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 15, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.twilight_ore.drops"));

    public static final DivineArmorMaterial WILDWOOD = new DivineArmorMaterial("wildwood", -1, 32, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 16, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.water.regen"));

    public static final DivineArmorMaterial APALACHIA = new DivineArmorMaterial("apalachia", -1, 36, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 17, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.block_protection"));

    public static final DivineArmorMaterial SKYTHERN = new DivineArmorMaterial("skythern", -1, 36, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 18, () -> Ingredient.EMPTY,
            TooltipUtil.jumpIncrease(5),
            TooltipUtil.noFall());

    public static final DivineArmorMaterial MORTUM = new DivineArmorMaterial("mortum", -1, 38, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 19, () -> Ingredient.EMPTY, TooltipUtil.i18n("tooltip.effect.night_vision"));

    public static final DivineArmorMaterial ANGELIC = new DivineArmorMaterial("angelic", -1, 15, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0, () -> Ingredient.EMPTY,
            TooltipUtil.noFall(),
            TooltipUtil.i18n("tooltip.effect.flight"));


    public static final DivineArmorMaterial SANTA = new DivineArmorMaterial("santa", -1, 7, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, () -> Ingredient.EMPTY,
            TooltipUtil.armorSpeed(2.5),
            TooltipUtil.addMeleeDamage(6),
            TooltipUtil.noHunger(),
            TooltipUtil.meleeProtection(80));

    private final int[] MAX_DAMAGE_ARRAY;
    private final List<ITextComponent> keyes = new ArrayList<>();

    private final LazyValue<Ingredient> repairMaterial;

    private final float chestplateDamage;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
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
        this.name = new ResourceLocation(DivineRPG.MODID, name);
        this.enchantability = enchantabilityIn;
        this.soundEvent = onEquip;
        this.toughness = toughness;
        this.repairMaterial = new LazyValue<>(repairMaterialSupplier);

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
    private final ResourceLocation name;

    /**
     * @param name             - name of armor material
     * @param chestplateDamage - amount of chestplate damage. Others parts will be calculated by resources, needed to createFireball armor piece
     * @param totalDefense     - amount of armor points. Diamond have 20 points that gives 80% defence
     * @param enchantabilityIn - enchantability. 25 for gold, 10 by default
     * @param onEquip          - sound on equip
     * @param toughness        - toughness. 2 for Diamond
     * @param keys             - armor description
     */
    public DivineArmorMaterial(String name, int chestplateDamage, int totalDefense, int enchantabilityIn, SoundEvent onEquip, float toughness, Supplier<Ingredient> repairMaterialSupplier,
                               ITextComponent... keys) {
        this(name, chestplateDamage, totalDefense, enchantabilityIn, onEquip, toughness, repairMaterialSupplier);

        this.keyes.addAll(Arrays.asList(keys));
    }

    public static DivineArmorMaterial forRupee(String color) {
        return new DivineArmorMaterial(color + "rupee", -1, 25, 15,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, () -> Ingredient.fromItems(ItemRegistry.rupeeIngot),
                TooltipUtil.meleeProtection(85));
    }

    public static DivineArmorMaterial forEnder(String color) {
        return new DivineArmorMaterial(color + "ender", 7500, 27, 15,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, () -> Ingredient.fromItems(ItemRegistry.enderStone),
                TooltipUtil.exposionProtection());
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

    public String getName() {
        return this.name.toString();
    }

    public void addTooltip(List<ITextComponent> tooltip) {
        if (!keyes.isEmpty()) {
            tooltip.add(TooltipUtil.i18n("tooltip.fullset"));
            tooltip.addAll(keyes);
        }
    }

    public String getArmorName() {
        return name.getPath();
    }

    public float getToughness() {
        return this.toughness;
    }
}
