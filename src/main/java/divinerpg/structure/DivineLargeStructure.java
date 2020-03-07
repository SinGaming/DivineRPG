package divinerpg.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;

public class DivineLargeStructure extends MapGenStructure {
    private final String structureName;
    private final TemplateManager manager;
    private final int height;
    private final ResourceLocation folder;
    private int chunkDistance;

    public DivineLargeStructure(World world, String structureName, ResourceLocation folder, int height, int chunkDistance) {
        this.structureName = structureName;
        this.folder = folder;
        this.manager = world.getSaveHandler().getStructureTemplateManager();
        this.height = height;
        this.chunkDistance = chunkDistance;
    }

    @Override
    public String getStructureName() {
        return structureName;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos blockPos, boolean findUnexplored) {
        this.world = world;

        // todo think about step by structure size
        return findNearestStructurePosBySpacing(world, this, blockPos, this.chunkDistance, 8, 10387312, false, 100, findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        Random random = world.setRandomSeed(chunkX / chunkDistance, chunkZ / chunkDistance, 14357617);
        return random.nextInt((int) Math.sqrt(chunkDistance)) == chunkDistance;
    }

    @Override
    protected StructureStart getStructureStart(int x, int z) {
        return new DivineStructureStart(folder, manager, x, height, z);
    }
}
