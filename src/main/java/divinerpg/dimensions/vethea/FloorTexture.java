package divinerpg.dimensions.vethea;

import divinerpg.registry.ModBlocks;
import net.minecraft.init.Blocks;

import java.util.Random;

public class FloorTexture implements IVetheanStructure {
	private static Random rand = new Random();

	@Override
	public void generate(IVetheaChunk chunk, int x, int y, int z) {
		for (double r = 0; r < 7; r += 0.5) {
			for (double theta = 0; theta < Math.PI * 2; theta += 0.2) {
				chunk.setBlock((int) Math.round(x + Math.cos(theta) * r), y, (int) Math.round(z + Math.sin(theta) * r), Blocks.AIR);
				chunk.setBlock((int) Math.round(x + Math.cos(theta) * r), y - 1, (int) Math.round(z + Math.sin(theta) * r), ModBlocks.dreamGrass);
			}
		}
	}

}
