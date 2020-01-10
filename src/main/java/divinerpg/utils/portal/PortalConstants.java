package divinerpg.utils.portal;

import divinerpg.registry.BlockRegistry;
import divinerpg.utils.portal.description.IPortalDescription;
import divinerpg.utils.portal.description.NetherLikeDescription;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class PortalConstants {

    public static final ArrayList<IPortalDescription> possiblePortals = new ArrayList<>();

    static {
        possiblePortals.add(new NetherLikeDescription(Blocks.OBSIDIAN, Blocks.NETHER_PORTAL));

        possiblePortals.add(new NetherLikeDescription(BlockRegistry.divineRock, BlockRegistry.edenPortal));
        possiblePortals.add(new NetherLikeDescription(BlockRegistry.edenBlock, BlockRegistry.wildwoodPortal));
        possiblePortals.add(new NetherLikeDescription(BlockRegistry.wildwoodBlock, BlockRegistry.apalachiaPortal));
        possiblePortals.add(new NetherLikeDescription(BlockRegistry.apalachiaBlock, BlockRegistry.skythernPortal));
        possiblePortals.add(new NetherLikeDescription(BlockRegistry.skythernBlock, BlockRegistry.mortumPortal));
        possiblePortals.add(new NetherLikeDescription(Blocks.SNOW_BLOCK, BlockRegistry.iceika_portal));
    }

    @Nullable
    public static IPortalDescription findByFrame(Block frame) {
        return possiblePortals.stream().filter(x -> x.getFrame() == frame).findFirst().orElse(null);
    }
}
