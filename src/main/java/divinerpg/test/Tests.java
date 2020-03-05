package divinerpg.test;

import divinerpg.dimensions.vethea.layer2.HiveNest;
import divinerpg.test.Template.TemplateGenerator;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.io.File;
import java.util.Map;

public class Tests {

    public static void start() {
        generate(new File("D:\\Hive"), new HiveNest());
    }

    private static void generate(File directory, WorldGenerator worldGen) {
        TemplateGenerator generator = new TemplateGenerator(worldGen);
        Map<ChunkPos, NBTTagCompound> templates = generator.createTemplates();

        if (directory.mkdirs() || directory.isDirectory()) {
            templates.forEach((pos, nbt) -> {
                File file = new File(directory, pos.toString() + ".nbt");

                try {
                    if (!file.createNewFile()) {
                        throw new Exception("Can't create file");
                    }

                    CompressedStreamTools.write(nbt, file);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("Wrong path: " + directory.getAbsolutePath());
        }
    }
}
