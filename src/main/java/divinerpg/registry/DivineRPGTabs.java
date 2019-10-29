package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DivineRPGTabs {
    public static final ItemGroup DivineBlocks = new ItemGroup(DivineRPG.MODID + ".blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.divineRock);
        }
    };
    public static final ItemGroup DivineItems = new ItemGroup(DivineRPG.MODID + ".items") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.corruptedStone);
        }
    };
}
