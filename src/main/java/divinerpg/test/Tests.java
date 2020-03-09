package divinerpg.test;

import divinerpg.dimensions.vethea.FloorTexture;
import divinerpg.dimensions.vethea.IVetheanStructure;
import divinerpg.dimensions.vethea.all.*;
import divinerpg.dimensions.vethea.layer1.*;
import divinerpg.dimensions.vethea.layer2.HiveNest;
import divinerpg.dimensions.vethea.layer2.Pyramid1;
import divinerpg.dimensions.vethea.layer2.Pyramid2;
import divinerpg.dimensions.vethea.layer2.Tree3;
import divinerpg.dimensions.vethea.layer3.KarosMadhouse;
import divinerpg.dimensions.vethea.layer3.QuadroticPost;
import divinerpg.dimensions.vethea.layer3.Tree7;
import divinerpg.dimensions.vethea.layer3.Tree8;
import divinerpg.dimensions.vethea.layer4.*;
import divinerpg.test.Template.TemplateGenerator;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Tests {

    public static void start() {
        generate("0level\\crypt1", new Crypt1());
        generate("0level\\crypt2", new Crypt2());
        generate("0level\\tree4", new Tree4());
        generate("0level\\tree5", new Tree5());
        generate("0level\\tree6", new Tree6());


        generate("1level\\hive", new HiveNest());
        generate("1level\\pyramid", new Pyramid1());
        generate("1level\\pyramid2", new Pyramid2());
        generate("1level\\tree3", new Tree3());


        generate("2level\\karosmadhouse", new KarosMadhouse());
        generate("2level\\quadroticpost", new QuadroticPost());
        generate("2level\\tree7", new Tree7());
        generate("2level\\tree8", new Tree8());

        generate("3level\\evergarden", new Evergarden());
        generate("3level\\layer4tree1", new Layer4Tree1());
        generate("3level\\layer4tree2", new Layer4Tree2());
        generate("3level\\raglokchamber", new RaglokChamber());
        generate("3level\\wreckhall", new WreckHall());

        generate("floating_tree\\floatingtree1", new FloatingTree1());
        generate("floating_tree\\floatingtree2", new FloatingTree2());
        generate("floating_tree\\floatingtree3", new FloatingTree3());
        generate("floating_tree\\floatingtree4", new FloatingTree4());
        generate("floating_tree\\floatingtree5", new FloatingTree5());
        generate("floating_tree\\floatingtree6", new FloatingTree6());
        generate("floating_tree\\floatingtree7", new FloatingTree7());

        generate("infusionoutpost", new InfusionOutpost());
        generate("lamp1", new Lamp1());
        generate("lamp2", new Lamp2());

        generate("bow", new Bow());
        generate("hook", new Hook());
        generate("mushroom", new Mushroom());
        generate("pickaxe", new Pickaxe());
        generate("pointedsquare", new Pointedsquare());
        generate("ring", new Ring());
        generate("sword", new Sword());
        generate("trident", new Trident());
        generate("floortexture", new FloorTexture());


//        generate("villagers\\housedown1", new HouseDown1());
//        generate("villagers\\housedown2", new HouseDown2());
//        generate("villagers\\housedown3", new HouseDown3());
//        generate("villagers\\houseup1", new HouseUp1());
//        generate("villagers\\houseup2", new HouseUp2());
//        generate("villagers\\houseup3", new HouseUp3());
//        generate("villagers\\houseup4", new HouseUp4());
//        generate("villagers\\lightdown1", new LightDown1());
//        generate("villagers\\lightdown2", new LightDown2());
//        generate("villagers\\lightdown3", new LightDown3());
//        generate("villagers\\lightdown4", new LightDown4());
//        generate("villagers\\lightup1", new LightUp1());
//        generate("villagers\\lightup2", new LightUp2());
//        generate("villagers\\lightup3", new LightUp3());
//        generate("villagers\\lightup4", new LightUp4());
//        generate("villagers\\lightup5", new LightUp5());
    }

    private static void generate(String directoryName, WorldGenerator worldGen) {
        generate(directoryName, TemplateGenerator.createTemplates(worldGen));
    }

    private static void generate(String directoryName, IVetheanStructure structure) {
        generate(directoryName, TemplateGenerator.createTemplates(structure));
    }

    private static void generate(String directoryName, Map<ChunkPos, NBTTagCompound> templates) {
        if (templates == null || templates.isEmpty())
            return;

        File mainDir = new File("D:\\structures\\");

        if (!mainDir.mkdirs() && !mainDir.isDirectory())
            return;

        final File directory = new File(mainDir.getAbsolutePath()
                + "\\"
                + directoryName
                + (templates.size() == 1
                ? ".nbt"
                : ""));

        if (!directory.getParentFile().mkdirs() && !directory.getParentFile().isDirectory())
            return;

        if (templates.size() == 1) {
            try {
                directory.createNewFile();
                CompressedStreamTools.writeCompressed(templates.values().iterator().next(), new FileOutputStream(directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (!directory.mkdirs() && !directory.isDirectory()) {
            System.out.println("Wrong path: " + directory.getAbsolutePath());
            return;
        }

        templates.forEach((pos, nbt) -> {
            File file = new File(directory, pos.toString() + ".nbt");

            try {
                if (!file.createNewFile()) {
                    throw new Exception("Can't create file");
                }

                CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
