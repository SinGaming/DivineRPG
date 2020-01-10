package divinerpg.utils;

import divinerpg.DivineRPG;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class DivineTags {
    public static Tag<Block> GRASS = new BlockTags.Wrapper(new ResourceLocation(DivineRPG.MODID, "grass"));
}
