package divinerpg.dimensions.vethea.all;
import divinerpg.dimensions.vethea.IVetheaChunk;
import divinerpg.dimensions.vethea.IVetheanStructure;
import divinerpg.registry.ModBlocks;

public class Ring implements IVetheanStructure {
    public void generate(IVetheaChunk chunk, int i, int j, int k) {
        chunk.setBlock(i + 0, j + 3, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 0, j + 4, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 1, j + 2, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 1, j + 3, k + 0, ModBlocks.everstone);
        chunk.setBlock(i + 1, j + 4, k + 0, ModBlocks.everstone);
        chunk.setBlock(i + 1, j + 5, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 2, j + 1, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 2, j + 2, k + 0, ModBlocks.everstone);
        chunk.setBlock(i + 2, j + 3, k + 0, ModBlocks.darkEverstone);
        chunk.setBlock(i + 2, j + 4, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 2, j + 5, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 2, j + 6, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 3, j + 0, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 3, j + 1, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 3, j + 2, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 3, j + 5, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 3, j + 6, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 3, j + 7, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 4, j + 0, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 4, j + 1, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 4, j + 6, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 4, j + 7, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 4, j + 8, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 5, j + 0, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 5, j + 1, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 5, j + 6, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 5, j + 7, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 5, j + 8, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 6, j + 0, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 6, j + 1, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 6, j + 2, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 6, j + 5, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 6, j + 6, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 6, j + 7, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 7, j + 1, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 7, j + 2, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 7, j + 3, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 7, j + 4, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 7, j + 5, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 7, j + 6, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 8, j + 2, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 8, j + 3, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 8, j + 4, k + 0, ModBlocks.everstone);
		chunk.setBlock(i + 8, j + 5, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 9, j + 3, k + 0, ModBlocks.darkEverstone);
		chunk.setBlock(i + 9, j + 4, k + 0, ModBlocks.darkEverstone);
	}
}