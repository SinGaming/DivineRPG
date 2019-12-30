package divinerpg.utils.portal.description;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IPortalDescription {
    /**
     * Creates full lightened portal
     *
     * @param world - in current world
     * @param pos   - player pos, standing inside portal
     */
    void createPortal(World world, BlockPos pos);

    /**
     * Only lights portal (creates portal blocks)
     *
     * @param world - in current world
     * @param match - frame match
     */
    void lightPortal(World world, BlockPattern.PatternHelper match);

    /**
     * portal frame
     *
     * @return
     */
    Block getFrame();

    /**
     * detect if pos is part of  portal
     *
     * @param world - current world
     * @param pos   - current pos
     */
    @Nullable
    BlockPattern.PatternHelper matchFull(World world, BlockPos pos);

    /**
     * Detect if we can light the portal
     *
     * @param world - current world
     * @param pos   - current pos
     * @return
     */
    @Nullable
    BlockPattern.PatternHelper matchFrame(World world, BlockPos pos);

    /**
     * Returns max portal size
     *
     * @return
     */
    double maxSize();

    /**
     * Gets pos inside portal
     *
     * @param match - portal match
     * @return
     */
    BlockPos getPlayerPosition(BlockPattern.PatternHelper match);

    default void placeBlock(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state);
    }
}
