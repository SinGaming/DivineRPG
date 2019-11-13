package divinerpg.api.armor;

import net.minecraft.crash.CrashReport;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoweredArmorSet extends ForgeRegistryEntry<IPoweredArmorSet> implements IPoweredArmorSet {
    private final IArmorSet set;
    private final IEquipped equipped;
    private final Map<Class, List<IPowerAbility>> abilityMap = new HashMap<>();

    public PoweredArmorSet(IArmorSet set, IEquipped equipped) {
        this.set = set;
        this.equipped = equipped;
    }

    @Override
    public IArmorSet getArmorSetDescriber() {
        return set;
    }

    @Override
    public IEquipped getEquippedHandler() {
        return equipped;
    }

    @Override
    public <T extends Event> IPoweredArmorSet addAbility(Class<T> clazz, IPowerAbility<T> ability) {
        if (!abilityMap.containsKey(clazz)) {
            abilityMap.put(clazz, new ArrayList<>());
        }

        List<IPowerAbility> abilities = abilityMap.get(clazz);
        if (abilities.contains(ability)) {
            CrashReport.makeCrashReport(new RuntimeException(), "Ability was already registered!");
        }

        abilities.add(ability);
        return this;
    }

    @Override
    public void handleAbility(Event event) {
        Class<? extends Event> clazz = event.getClass();
        if (abilityMap.containsKey(clazz)) {
            abilityMap.get(clazz).forEach(iPowerAbility -> iPowerAbility.handleAbility(event));
        }
    }
}
