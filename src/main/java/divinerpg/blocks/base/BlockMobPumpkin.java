package divinerpg.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockMobPumpkin extends Block {

    private SoundEvent pumpkinSound;

    public BlockMobPumpkin(SoundEvent sound) {
        super(Block.Properties.create(Material.GOURD).sound(SoundType.WOOD).hardnessAndResistance(1.0F).harvestTool(ToolType.AXE));

        this.pumpkinSound = sound;
    }

    @Deprecated
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!player.isSneaking() && this.pumpkinSound != null) {
            worldIn.playSound(null, pos, this.pumpkinSound, SoundCategory.BLOCKS, 20.0F, 1.0F);
            return true;
        }
        return false;
    }
}
