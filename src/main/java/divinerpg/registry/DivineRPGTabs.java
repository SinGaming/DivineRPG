package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class DivineRPGTabs {


//    public static DivineRPGTabs swords = new DivineRPGTabs("Swords", "Melee Weapons") {
//        @Override
//        public ItemStack createIcon() {
//            return new ItemStack(ItemRegistry.aquaton);
//        }
//    };
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
//    public static ItemGroup food = new ItemGroup("Food") {
//        @Override
//        public ItemStack createIcon() {
//            return new ItemStack(ItemRegistry.bacon);
//        }
//    };


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
    public static final ItemGroup DivineMaterials = new ItemGroup("Materials") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.rupeeIngot);
        }
    };
    public static final ItemGroup DivineTools = new ItemGroup("Tools") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.rupeeShickaxe);
        }
    };
    public static final ItemGroup DivineRanged = new ItemGroup("Ranged Weapons") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.halite_phaser);
        }
    };
    public static final ItemGroup DivineArmor = new ItemGroup("Armor") {
        @Override
        public ItemStack createIcon() {
            // TODO use divine icon
            return new ItemStack(Items.DIAMOND_HELMET);
        }
    };
}
