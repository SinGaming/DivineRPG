package divinerpg.test.Template;

import divinerpg.dimensions.vethea.IVetheanStructure;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Map;

public class TemplateGenerator {
    private static Map<ChunkPos, NBTTagCompound> fromWorld(FakeWorld fakeWorld) {
        HashMap<ChunkPos, NBTTagCompound> result = new HashMap<>();

        fakeWorld.getChunkIterator().forEachRemaining(chunk -> {
            TempleToJsonConverter temple = new TempleToJsonConverter(fakeWorld, chunk);

            result.put(chunk, temple.writeToNBT());
        });

        return result;
    }

    public static Map<ChunkPos, NBTTagCompound> createTemplates(WorldGenerator generator) {
        FakeWorld fakeWorld = new FakeWorld();
        generator.generate(fakeWorld, fakeWorld.rand, BlockPos.ORIGIN);
        return fromWorld(fakeWorld);
    }

    public static Map<ChunkPos, NBTTagCompound> createTemplates(IVetheanStructure generator) {
        FakeWorld fakeWorld = new FakeWorld();
        generator.generate(fakeWorld, 0, 0, 0);
        return fromWorld(fakeWorld);
    }
}
