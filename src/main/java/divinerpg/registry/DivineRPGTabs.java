package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DivineRPGTabs {


//
//    public static ItemGroup spawner = new ItemGroup("Spawner") {
//        @Override
//        public ItemStack createIcon() {
//            return new ItemStack(ItemRegistry.callOfTheWatcher);
//        }
//    };
//    public static ItemGroup utility = new ItemGroup("Utility") {
//        @Override
//        public ItemStack createIcon() {
//            return new ItemStack(ItemRegistry.snowGlobe);
//        }
//    };

    public static final ItemGroup DivineMaterials = new ItemGroup(DivineRPG.MODID + ".materials") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.rupeeIngot);
        }
    };
    public static final ItemGroup DivineBlocks = new ItemGroup(DivineRPG.MODID + ".blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.edenOre);
        }
    };
    public static final ItemGroup DivineItems = new ItemGroup(DivineRPG.MODID + ".items") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.corruptedStone);
        }
    };
    public static final ItemGroup DivineTools = new ItemGroup(DivineRPG.MODID + ".tools") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.rupeeShickaxe);
        }
    };
    public static final ItemGroup DivineRanged = new ItemGroup(DivineRPG.MODID + ".ranged") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.halite_phaser);
        }
    };
    public static final ItemGroup DivineArmor = new ItemGroup(DivineRPG.MODID + ".armor") {
        @Override
        public ItemStack createIcon() {
            // TODO use divine icon
            return new ItemStack(ItemRegistry.kraken_helmet);
        }
    };
    public static ItemGroup DivineFood = new ItemGroup(DivineRPG.MODID + ".food") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.cheese);
        }
    };
    public static ItemGroup DivineSwords = new ItemGroup("swords") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.haliteBlade);
        }
    };
}
