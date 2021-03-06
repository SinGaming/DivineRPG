package divinerpg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.config.Config;
import divinerpg.objects.entities.assets.render.RenderHat;
import divinerpg.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;

public class Utils {
    public static int mobID = 500, projectileID = 0, entityListID = 2500;

    public static DamageSource trapSource = new DamageSource("trap");
    public static DamageSource acidSource = new DamageSource("acid");
    public static DamageSource spikeSource = new DamageSource("spike");
    public static DamageSource arcanaSource = new DamageSource("arcana");

    private static String str;

    private static Object args;
    public static String BLACK = "\u00a70", DARK_BLUE = "\u00a71", DARK_GREEN = "\u00a72", DARK_AQUA = "\u00a73",
            DARK_RED = "\u00a74";
    public static String DARK_PURPLE = "\u00a75", GOLD = "\u00a76", GRAY = "\u00a77", DARK_GRAY = "\u00a78",
            BLUE = "\u00a79";
    public static String GREEN = "\u00a7a", AQUA = "\u00a7b", RED = "\u00a7c", LIGHT_PURPLE = "\u00a7d",
            YELLOW = "\u00a7e";
    public static String WHITE = "\u00a7f";

    public static List<String> DEV_LIST = new ArrayList<String>();
    public static List<String> TESTER_LIST = new ArrayList<String>();
    public static Map<AbstractClientPlayer, RenderHat.Type> REGISTRY = new LinkedHashMap<>();
    private static List<String> PATRON_LIST = new ArrayList<String>();
    public static Map<String, ResourceLocation> capeMap = new HashMap<>();

    public static ITextComponent addChatMessage(ITextComponent displayName) {
        TextComponentString ret = new TextComponentString(args + str);
        ret.getStyle().setColor(TextFormatting.WHITE);
        return ret;
    }

    public static TextComponentString addChatMessage(String str, Object... args) {
        TextComponentString ret = new TextComponentString(args + str);
        ret.getStyle().setColor(TextFormatting.WHITE);
        return ret;
    }

    private static String convertCapeString(String capeName) {
        StringBuilder underscoreCase = new StringBuilder();
        for (int i = 0; i < capeName.length(); ++i) {
            char c = capeName.charAt(i);
            if (!Character.isLowerCase(c)) {
                underscoreCase.append("_");
                c = Character.toLowerCase(c);
            }
            underscoreCase.append(c);
        }
        return underscoreCase.toString();
    }

    public static boolean doesPlayerHaveBlueHat(AbstractClientPlayer player) {
        for (int i = 0; i < PATRON_LIST.size(); ++i) {
            String uuid = player.getUniqueID().toString() + "_BHAT";
            if (!uuid.equals(PATRON_LIST.get(i))) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static boolean doesPlayerHaveRedHat(AbstractClientPlayer player) {
        for (int i = 0; i < PATRON_LIST.size(); ++i) {
            String uuid = player.getUniqueID().toString() + "_RHAT";
            if (!uuid.equals(PATRON_LIST.get(i))) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static ITextComponent getChatComponent(String str) {
        TextComponentString ret = new TextComponentString(str);
        return ret;
    }

    public static ITextComponent getChatComponent(String str, String args) {
        TextComponentString ret = new TextComponentString(args + str);
        ret.getStyle().setColor(TextFormatting.WHITE);
        return ret;
    }
    @Deprecated
    static List<String> getPatronList() {
        try {
            List<String> entries = new ArrayList<String>();
            HttpURLConnection con;
            con = (HttpURLConnection) new URL(
                    "https://raw.githubusercontent.com/NicosaurusRex99/DivineRPG/1.12.2/dev.txt").openConnection();
            con.setConnectTimeout(1000);
            InputStream in2 = con.getInputStream();
            entries = IOUtils.readLines(in2);
            if (!entries.isEmpty()) {
                con.disconnect();
                return entries;
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static RenderHat.Type getWingType(AbstractClientPlayer player) {
        if (player != null) {
            if (REGISTRY.containsKey(player)) {
                return REGISTRY.get(player);
            }
        }
        return null;
    }

    public static boolean isContributor(AbstractClientPlayer player) {
        return player != null && REGISTRY.containsKey(player);
    }

    public static boolean isDeveloperName(String name) {
        return DEV_LIST.contains(name);
    }
    
    public static boolean isTesterName(String name) {
        return TESTER_LIST.contains(name);
    }

    public static void postFMLEvent(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

    public static void postForgeEvent(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

    @Deprecated
    public static void setupCapes() {
        try {
            Utils.updateCapeList();
        } catch (Exception e) {
            FMLLog.severe("Error while setting up DivineRPG dev hats");
            e.printStackTrace();
        }

    }
    @Deprecated
    public static void updateCapeList() {
        int timeout = 10000;
        URL capeListUrl;

        try {
            capeListUrl = new URL("https://raw.githubusercontent.com/NicosaurusRex99/DivineRPG/1.12.2/dev.txt");
        } catch (IOException e) {
            FMLLog.severe("Error getting capes list URL");
            if (Config.debug) {
                e.printStackTrace();
            }
            return;
        }

        URLConnection connection;

        try {
            connection = capeListUrl.openConnection();
        } catch (IOException e) {
            if (Config.debug) {
                e.printStackTrace();
            }
            return;
        }

        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        InputStream stream;

        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            if (Config.debug) {
                e.printStackTrace();
            }
            return;
        }

        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    int splitLocation = line.indexOf(":");
                    String username = line.substring(0, splitLocation);
                    Utils.capeMap.put(username, new ResourceLocation(Reference.MODID, "textures/models/devhats/"
                            + convertCapeString(line.substring(splitLocation + 1)) + ".png"));
                }
            }
        } catch (IOException e) {
            if (Config.debug) {
                e.printStackTrace();
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                if (Config.debug) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DamageSource causeArcanaDamage(Entity projectile, Entity shooter) {
        return new EntityDamageSourceIndirect("arrow", projectile, shooter).setMagicDamage();
    }

    public static ToolMaterial addHammerMaterial(float damage) {
        return EnumHelper.addToolMaterial("sword", 0, -1, 0, damage - 4, 22);
    }

    public static boolean bordersTar(World world, int x, int y, int z) {
        for (int i = x - 4; i <= x + 4; ++i) {
            for (int j = y; j <= y + 1; ++j) {
                for (int k = z - 4; k <= z + 4; ++k) {
                    if (world.getBlockState(new BlockPos(i, j, k)).getBlock() == ModBlocks.tar) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean bordersTar(World w, BlockPos pos) {
        return bordersTar(w, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void addDevsToList() {
        DEV_LIST.add("607043b2-830e-4d7c-a600-871754c01827"); //RadioactiveStud
        DEV_LIST.add("a75f7026-aebd-4777-9cbb-de40516dca84"); //Eternaldoom1
        DEV_LIST.add("989677b3-7af4-4b9a-9672-07b367677ecb"); //BossLetsPlays
        DEV_LIST.add("010318ef-28fc-4c7c-8940-2f0d62eabfa6"); //Xolova
        DEV_LIST.add("6e65f2ca-fd15-47c5-ab91-13dd2731d73f"); //sheenrox82
        DEV_LIST.add("b9ee091b-c611-41ff-a52b-4020027e5cb6"); //The_SlayerMC
        DEV_LIST.add("3a037d9d-0744-4452-aa80-f3c9ab17a1d2"); //insanity414all
        DEV_LIST.add("de247846-69fb-4880-907b-a5e1f58267f3"); //deathman12e3
        DEV_LIST.add("1586e8bd-e266-49d0-aa2a-f6aab3a8e90d"); //Fire_Sight
        DEV_LIST.add("c657219e-36ba-495c-aff4-dad3c248f76c"); //Krwminer
        DEV_LIST.add("17356bc2-89ad-484e-9f2a-8fdbdcf1f3cd"); //NicosaurusRex99
        DEV_LIST.add("1e2326e7-a592-4e11-9b4c-d0c930deeca3"); //Wufflez
        DEV_LIST.add("e11679a6-2269-46aa-a6fd-4e2aec9f3b96"); //Dash
    }
    
	public static void addTestersToList() {
        TESTER_LIST.add("509f6794-ad97-4270-9627-ae85b03a0534"); //alexandru
        TESTER_LIST.add("2903ef30-1a71-40ff-87aa-dceee2b6f6d9"); //FireBitMC
        TESTER_LIST.add("ae8d12d9-391f-4b0c-9627-662b3e91b3c9"); //Locomen_
        TESTER_LIST.add("52b7a6ca-ec5f-406a-90cb-012dbb8dbc0d"); //69EHOTUK96
        TESTER_LIST.add("f4bcf30e-cc3a-48bc-82dc-d75798f1f844"); //rpgmaster99
        TESTER_LIST.add("74e117c8-7f46-4f39-a174-c86bafb6ad50"); //KIRYLL_Wexd1105
        TESTER_LIST.add("0d731c4e-7d1a-4072-8ef3-54cc6a524e47"); //PinkGoose_
        TESTER_LIST.add("f4bcf30e-cc3a-48bc-82dc-d75798f1f844"); //Cpfuzzyz
        TESTER_LIST.add("6280b6f6-eafe-4a38-9296-a05cace12c15"); //sikerow
        TESTER_LIST.add("5f7b78f0-e8e0-4ae3-a55c-2e8ddab3c51e"); //EliteXander2017
        TESTER_LIST.add("52dc9328-c685-4868-bc71-0f17b66c3ae0"); //Arob105
        TESTER_LIST.add("ac5a82b0-6ace-4649-ac54-ac66597127fc"); //Dinozver
        TESTER_LIST.add("4cf228db-7980-4543-ab01-4138ed5824c7"); //Lukifuge
        TESTER_LIST.add("c3c9543a-70a1-4b24-9c3c-7f6569b90437"); //TellNoLies
        TESTER_LIST.add("404b95bb-9fc1-4653-98b2-6be5ef77ede8"); //PeggyPenguin
        TESTER_LIST.add("48448fdb-18d4-4359-af91-eb529486f18c"); //Chelovechecheggg
        TESTER_LIST.add("940b96bb-1b57-40d1-9cbf-51e261e3b31d"); //SystemDysphoria
        TESTER_LIST.add("d6f20cb4-6a17-4ffc-b928-dc05c6d5c809"); //a09hopper
        TESTER_LIST.add("af0adaea-4a23-4b18-9237-fc7661e854a5"); //PeopleMcNugget
        TESTER_LIST.add("0c23ad99-41b5-420a-8503-9868cf174183"); //whitebeartigtig
        TESTER_LIST.add("417f6730-13ec-4ad0-83cf-abd6ef1ef9a9"); //MaxTalks
        TESTER_LIST.add("ebbfcae9-9181-46f7-9b0b-840b75b4919c"); //masterbobli
        TESTER_LIST.add("0c23ad99-41b5-420a-8503-9868cf174183"); //whitebear___
        TESTER_LIST.add("492875d9-06f7-4c3b-85f1-9e3a63f71128"); //FLAVSY9000
        TESTER_LIST.add("a9d16dd7-d917-48ac-9888-095eb30ab3af"); //Techno3712
    }
}