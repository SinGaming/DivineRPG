package divinerpg.world.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class TemplateStructureStart extends StructureStart {
    private ResourceLocation location = null;

    public TemplateStructureStart(Structure<?> structure, int chunkX, int chunkZ, Biome biomeIn, MutableBoundingBox boundsIn, int referenceIn, long seed) {
        super(structure, chunkX, chunkZ, biomeIn, boundsIn, referenceIn, seed);

        if (structure instanceof TemplateStructure) {
            location = ((TemplateStructure) structure).getTemplateLocation();
        }
    }

    @Override
    public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
        this.components.add(new TemplateStructurePart(location, templateManagerIn, new BlockPos(chunkX * 16, generator.getSeaLevel(), chunkZ * 16)));
        this.recalculateStructureSize();
    }
}
