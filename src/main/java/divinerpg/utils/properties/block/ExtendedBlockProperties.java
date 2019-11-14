package divinerpg.utils.properties.block;

import divinerpg.registry.BlockRegistry;
import divinerpg.utils.DivinePlantType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtendedBlockProperties {
    public final Block.Properties props;
    public IPlacementCheck validGround;
    public DivinePlantType type;
    public VoxelShape shape = VoxelShapes.fullCube();
    public Predicate<Block> canSpreadGrass = block -> block == Blocks.DIRT;
    public IExpDrop drop;
    public Consumer<Entity> onCollision;
    public Consumer<Entity> onHarvest;
    public int maxAge = 7;
    public boolean noBoneMeal = false;
    public Supplier<IItemProvider> getSeed;

    public static ExtendedBlockProperties createForOre(float hard, float resist, int harvestLevel, IExpDrop drop) {
        return createForOre(hard, resist, harvestLevel).withCustomExpDrop(drop);
    }

    public static ExtendedBlockProperties createForOre(float hard, float resist, int harvestLevel) {
        return new ExtendedBlockProperties(Block.Properties.create(Material.ROCK)
                .harvestTool(ToolType.PICKAXE).hardnessAndResistance(hard, resist)
                .harvestLevel(harvestLevel));
    }

    public static ExtendedBlockProperties createForSapling(MaterialColor color) {
        ExtendedBlockProperties result = new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, color)
                .doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT));

        // default size
        result.shape = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

        return result;
    }

    public static ExtendedBlockProperties createForLeaves(MaterialColor color) {
        return new ExtendedBlockProperties(Block.Properties.create(Material.LEAVES, color).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT));
    }

    public ExtendedBlockProperties(Block.Properties props) {
        this.props = props;
        type = DivinePlantType.fromPlantType(PlantType.Plains);
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
    public ExtendedBlockProperties withNonVanillaType(DivinePlantType type) {
        this.type = type;
        Function<Supplier<Block>, IPlacementCheck> getPlacement = block -> (state, worldIn, pos) -> state.getBlock() == block.get();

        if (type == DivinePlantType.EDEN) {
            withGround(getPlacement.apply(() -> BlockRegistry.edenGrass));
        }

        if (type == DivinePlantType.WILDWOOD) {
            withGround(getPlacement.apply(() -> BlockRegistry.wildwoodGrass));
        }

        if (type == DivinePlantType.APALACHIA) {
            withGround(getPlacement.apply(() -> BlockRegistry.apalachiaGrass));
        }

        if (type == DivinePlantType.SKYTHERN) {
            withGround(getPlacement.apply(() -> BlockRegistry.skythernGrass));
        }

        if (type == DivinePlantType.MORTUM) {
            withGround(getPlacement.apply(() -> BlockRegistry.mortumGrass));
        }

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

    /**
     * Adds spreading function predicate (for grass)
     */
    public ExtendedBlockProperties withSpreading(Predicate<Block> canSpreadGrass) {
        if (props != null) {
            props.tickRandomly();
        }
        this.canSpreadGrass = canSpreadGrass;
        return this;
    }

    /**
     * Callback on block destroy by player
     */
    public ExtendedBlockProperties withCustomExpDrop(IExpDrop drop) {
        this.drop = drop;
        return this;
    }

    /**
     * Callback on entuty collision
     */
    public ExtendedBlockProperties onCollision(Consumer<Entity> onCollision) {
        this.onCollision = onCollision;
        return this;
    }

    public ExtendedBlockProperties withAge(int age) {
        this.maxAge = age;
        return this;
    }

    public ExtendedBlockProperties disableBonemeal() {
        noBoneMeal = true;
        return this;
    }

    public ExtendedBlockProperties withSeed(Supplier<IItemProvider> getSeed) {
        this.getSeed = getSeed;
        return this;
    }

    public ExtendedBlockProperties onHarvest(Consumer<Entity> onHarvest) {
        this.onHarvest = onHarvest;
        return this;
    }
}
