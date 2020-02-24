package divinerpg.world.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Random;
import java.util.function.Function;


public class TemplateStructure<T extends IFeatureConfig> extends Structure<T> {
    private final int sizeInChunks;
    private ResourceLocation res;

    public TemplateStructure(Function<Dynamic<?>, ? extends T> parseFunc, ResourceLocation res, int sizeInChunks) {
        super(parseFunc);
        this.sizeInChunks = sizeInChunks;
        this.res = res;
    }

    @Override
    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
        // don't know why but we need to add 9 to find a start
        Biome biome = chunkGen.func_226691_t_Provider().func_226691_t_(new BlockPos((chunkPosX << 4) + 9, chunkGen.getSeaLevel(), (chunkPosZ << 4) + 9));
        return chunkGen.hasStructure(biome, this);
    }

    @Override
    public IStartFactory getStartFactory() {
        return TemplateStructureStart::new;
    }

    @Override
    public String getStructureName() {
        return res.toString();
    }

    @Override
    public int getSize() {
        return sizeInChunks;
    }

    public ResourceLocation getTemplateLocation() {
        return res;
    }
}
