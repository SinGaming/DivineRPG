package divinerpg.world.structure;

import com.mojang.datafixers.Dynamic;
import divinerpg.DivineRPG;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TemplateFeature<T extends TemplateFeatureConfig> extends Feature<T> {
    private BiFunction<World, BlockPos, Boolean> canPlace;

    public TemplateFeature(Function<Dynamic<?>, ? extends T> configFactoryIn) {
        super(configFactoryIn);
    }

    public TemplateFeature() {
        this(dynamic -> (T) new TemplateFeatureConfig(dynamic));
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, T config) {
        if (!(worldIn.getWorld() instanceof ServerWorld)) {
            return false;
        }

        TemplateManager manager = ((ServerWorld) worldIn.getWorld()).getStructureTemplateManager();
        Template template = manager.getTemplate(config.template);
        if (template == null)
            return false;

        BlockPos templateSize = template.getSize();
        BlockPos start = calculateOffset(worldIn, pos, templateSize, rand);

        return canPlace(worldIn, start, templateSize, config) && template.addBlocksToWorld(worldIn, start, config.create(template, start), 2);
    }

    /**
     * Check if can place
     *
     * @param world  - world
     * @param pos    - template position
     * @param size   - template size
     * @param config - template config
     * @return
     */
    protected boolean canPlace(IWorld world, BlockPos pos, BlockPos size, T config) {
        // Check if empty
        if (config.surface == null || config.surface.isAir(world.getWorld(), pos)) {
            return false;
        }

        AtomicBoolean result = new AtomicBoolean(true);

        BlockPos.getAllInBoxMutable(pos, pos.add(size.getX(), 0, pos.getZ())).forEach(x -> {
            if (world.getWorld().getBlockState(x).getBlock() != config.surface.getBlock()) {
                result.set(false);
                return;
            }
        });

        return result.get();
    }

    /**
     * Calculates offset for structure
     *
     * @param world  - world
     * @param start  - start pos (from generator)
     * @param size   - template size
     * @param random - random var
     * @return
     */
    protected BlockPos calculateOffset(IWorld world, BlockPos start, @Nonnull BlockPos size, Random random) {
        // Worldgen start generating features when 2*2 chunks was loaded

        // We should stay at the right up corner (where the star is )
        //  *-----|------|
        //  |  1  |   2  |
        //  |-----|------|
        //  |  3  |   4  |
        //  |-----|------|

        // So we sure that we have 32*32 loaded square
        final int maxSize = 32;

        // chunk start position
        int x = start.getX() << 4 >> 4;
        int z = start.getZ() << 4 >> 4;

        if (size.getX() > maxSize || size.getY() > maxSize) {
            DivineRPG.LOGGER.warn(String.format("Template is too big (%s), will cause chunk overpopulation during loading. " +
                    "For structures more than 32*32 use net.minecraft.world.gen.feature.structure.Structure", size.toString()));
        }

        x += random.nextInt(MathHelper.clamp(maxSize - size.getX(), 1, 16));
        z += random.nextInt(MathHelper.clamp(maxSize - size.getZ(), 1, 16));

        // Getting correct surface position
        return new BlockPos(x, world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z), z);
    }
}
