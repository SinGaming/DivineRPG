package divinerpg.test.Template;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Map;

public class TemplateGenerator {
    private WorldGenerator generator;

    public TemplateGenerator(WorldGenerator generator) {
        this.generator = generator;
    }

    public Map<ChunkPos, NBTTagCompound> createTemplates() {
        FakeWorld fakeWorld = new FakeWorld();
        generator.generate(fakeWorld, fakeWorld.rand, BlockPos.ORIGIN);

        HashMap<ChunkPos, NBTTagCompound> result = new HashMap<>();

        fakeWorld.getChunkIterator().forEachRemaining(chunk -> {
            TempleToJsonConverter temple = new TempleToJsonConverter(fakeWorld, chunk);

            result.put(chunk, temple.writeToNBT());
        });

        return result;
    }
}
