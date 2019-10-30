package divinerpg.utils.properties.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.PlantType;

public class ExtendedBlockProperties {
    public final Block.Properties props;
    public IPlacementCheck validGround;
    public PlantType type = PlantType.Plains;
    public VoxelShape shape = VoxelShapes.fullCube();

    public ExtendedBlockProperties(Block.Properties props) {
        this.props = props;
    }

    public ExtendedBlockProperties withGround(IPlacementCheck validGround) {
        this.validGround = validGround;
        return this;
    }

    /**
     * Using special plant type
     * take a look at static fields here {@link divinerpg.registry.BlockRegistry}
     *
     * @param type - special plant type
     * @return
     */
    public ExtendedBlockProperties withType(PlantType type) {
        this.type = type;
        return this;
    }

    /**
     * Custom size for flower
     *
     * @param height - height from 0 to 16
     * @param width  - width from 0 to 16
     */
    public ExtendedBlockProperties withSize(float height, float width) {
        height = MathHelper.clamp(height, 0, 16);
        width = MathHelper.clamp(width, 0, 16);

        // This is bounding box, 1*1 size
        // Center point (A) is 0.5;0.5
        // We need to find L's and R's points, that's a
        // rectangle with given size
        //////////////////////////
        //  L1----width----R1   //
        //  h                   //
        //  e                   //
        //  i      A            //
        //  g                   //
        //  h                   //
        //  t                   //
        //  L2             R2   //
        //////////////////////////

        // middle height
        double middle = 8;

        double leftCorner = Double.max(0, middle - width / 2);
        double rightCorner = middle + width / 2;

        shape = Block.makeCuboidShape(leftCorner, 0, leftCorner, rightCorner, height, rightCorner);

        return this;
    }
}
