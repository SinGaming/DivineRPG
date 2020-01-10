package divinerpg.world.iceika;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.WorldGenRegion;

public class IceikaChunkGenerator extends OverworldChunkGenerator {
    public IceikaChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
        super(worldIn, provider, settingsIn);
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {
        // just removed
    }
}
