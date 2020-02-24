package divinerpg.api.armor.interfaces;

import divinerpg.api.armor.ForgeEventHandler;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.function.Consumer;


/**
 * Describe what powered armor should do
 */
public interface IPoweredArmor extends IForgeRegistryEntry<IPoweredArmor>, IEquipped {

    java.util.Map<Class, ForgeEventHandler<?>> getAbilityMap();

    /**
     * Add ability to armor set
     *
     * @param clazz - class of event
     * @param e     - handling event
     * @param <T>   - type of event
     */
    <T extends Event, TRes extends IPoweredArmor> TRes addAbility(Class<T> clazz, Consumer<T> e);

    /**
     * Returns current armor description
     *
     * @return
     */
    @Nonnull
    IArmorSet getArmorDescription();
}
