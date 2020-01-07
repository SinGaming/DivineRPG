package divinerpg.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import divinerpg.DivineRPG;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Util class for creating new villagers
 */
public class VillagerBuilder {

    private final IForgeRegistry<VillagerProfession> registry;

    private final String name;
    private final List<Biome> biomes = new ArrayList<>();
    private final Map<Integer, VillagerTrades.ITrade[]> trades = Maps.newHashMap();
    private final IVillagerType villagerType;

    /**
     * Init villager builder
     *
     * @param registry     - forge registry
     * @param name         - name of villager
     * @param villagerType - name of villager type
     */
    public VillagerBuilder(IForgeRegistry<VillagerProfession> registry, String name, IVillagerType villagerType) {
        this.registry = registry;
        this.name = name;
        this.villagerType = villagerType;
    }

    /**
     * Gets or register villager type only by name
     *
     * @param name - Name of the villager, Will be formed as divinerpg:NAME
     * @return
     */
    @Nonnull
    public static IVillagerType getOrCreate(String name) {
        if (name == null || name.isEmpty()) {
            CrashReport.makeCrashReport(new Exception(), "Name is always needed to create villager type");
        }

        checkStaticField();

        ResourceLocation key = new ResourceLocation(DivineRPG.MODID, name);

        IVillagerType result = Registry.VILLAGER_TYPE.getOrDefault(key);
        if (result.toString() != key.toString()) {
            // need to register
            result = IVillagerType.register(key.toString());
        }

        return result;
    }

    /**
     * Need to check wherever field is loaded
     */
    private static void checkStaticField() {
        // dirty hack? need to load the field
        if (VillagerTrades.field_221239_a == null) {
            CrashReport.makeCrashReport(new Exception(), "Can't load trade data");
        }
    }

    /**
     * Add villager type in selected biomes
     *
     * @param biomes -
     * @return
     */
    public VillagerBuilder inBiomes(Biome... biomes) {
        if (biomes != null && biomes.length > 0) {
            this.biomes.addAll(Arrays.asList(biomes));
        }
        return this;
    }

    /**
     * Creates trader recepies by level. Second call will add trades on second level etc.
     *
     * @param trades - list of trades
     * @return
     */
    public VillagerBuilder withTrades(VillagerTrades.ITrade... trades) {
        if (trades != null || trades.length > 0) {
            this.trades.put(this.trades.size() + 1, trades);
        }

        return this;
    }

    /**
     * Creating and register a new villager, new villager type, register it in biomes and register villager trades by profession
     *
     * @param interestType - interest type
     * @return
     */
    public VillagerProfession build(PointOfInterestType interestType) {
        VillagerProfession profession = new VillagerProfession(name, interestType, ImmutableSet.of(), ImmutableSet.of()).setRegistryName(DivineRPG.MODID, name);
        registry.register(profession);

        checkStaticField();

        if (!biomes.isEmpty()) {
            biomes.forEach(x -> IVillagerType.BY_BIOME.put(x, villagerType));
        }

        if (!trades.isEmpty()) {
            VillagerTrades.field_221239_a.put(profession, new Int2ObjectOpenHashMap<>(trades));
        }

        return profession;
    }
}
