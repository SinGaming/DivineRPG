package divinerpg.utils.portal;

import divinerpg.registry.BlockRegistry;
import divinerpg.registry.DimensionTypeRegistry;
import divinerpg.utils.portal.description.IPortalDescription;
import divinerpg.utils.portal.description.NetherLikeDescription;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PortalConstants {
    /**
     * Mapping frame with portals
     */
    public static final Map<Block, IPortalDescription> portalBlockMap = new HashMap<>();
    /**
     * Mapping dimensiontypes and portaldescriptions
     */
    public static final Map<DimensionType, IPortalDescription> portalDimensionMap = new HashMap<>();
    private static final ArrayList<IPortalDescription> possiblePortals = new ArrayList<>();

    static {
        put(null, new NetherLikeDescription(Blocks.OBSIDIAN, Blocks.NETHER_PORTAL));

        put(DimensionType.byName(DimensionTypeRegistry.EDEN), new NetherLikeDescription(BlockRegistry.divineRock, BlockRegistry.edenPortal));
        put(DimensionType.byName(DimensionTypeRegistry.WILDWOOD), new NetherLikeDescription(BlockRegistry.edenBlock, BlockRegistry.wildwoodPortal));
        put(DimensionType.byName(DimensionTypeRegistry.APALACHIA), new NetherLikeDescription(BlockRegistry.wildwoodBlock, BlockRegistry.apalachiaPortal));
        put(DimensionType.byName(DimensionTypeRegistry.SKYTHERN), new NetherLikeDescription(BlockRegistry.apalachiaBlock, BlockRegistry.skythernPortal));
        put(DimensionType.byName(DimensionTypeRegistry.MORTUM), new NetherLikeDescription(BlockRegistry.skythernBlock, BlockRegistry.mortumPortal));
        put(DimensionType.byName(DimensionTypeRegistry.ICEIKA), new NetherLikeDescription(Blocks.SNOW_BLOCK, BlockRegistry.iceika_portal));
    }

    private static void put(DimensionType type, IPortalDescription description) {
        possiblePortals.add(description);
        portalBlockMap.put(description.getFrame(), description);

        if (type != null)
            portalDimensionMap.put(type, description);
    }
}
