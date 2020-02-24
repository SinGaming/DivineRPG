package divinerpg.api.armor;

import divinerpg.api.armor.interfaces.IArmorSet;
import divinerpg.api.armor.interfaces.IEquipped;
import divinerpg.api.armor.interfaces.IPoweredArmor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PoweredArmor extends ForgeRegistryEntry<IPoweredArmor> implements IPoweredArmor {
    protected final Map<Class, ForgeEventHandler<?>> abilities = new HashMap<>();
    private final IEquipped handler;
    private final IArmorSet set;

    public PoweredArmor(IArmorSet set) {
        this(set, null);
    }

    public PoweredArmor(IArmorSet set, IEquipped handler) {
        Objects.requireNonNull(set);

        this.handler = handler;
        this.set = set;
    }


    public PoweredArmor(net.minecraft.item.Item helmet, net.minecraft.item.Item chestplate, net.minecraft.item.Item legs, Item boots) {
        this(helmet, chestplate, legs, boots, null);
    }

    public PoweredArmor(Item helmet, Item chestplate, Item legs, Item boots, IEquipped handler) {
        this(new ArmorSet().withVariant(helmet, chestplate, legs, boots), handler);
    }

    @Override
    public Map<Class, ForgeEventHandler<?>> getAbilityMap() {
        return abilities;
    }


    @Override
    public <T extends Event, TRes extends IPoweredArmor> TRes addAbility(Class<T> clazz, java.util.function.Consumer<T> e) {
        if (!abilities.containsKey(clazz)) {
            abilities.put(clazz, new ForgeEventHandler<>(clazz, e));
        }

        return (TRes) this;
    }

    @Override
    public IArmorSet getArmorDescription() {
        return set;
    }

    @Override
    public void onEquppedChanged(PlayerEntity player, boolean isEquipped) {
        if (handler != null) {
            handler.onEquppedChanged(player, isEquipped);
        }
    }
}
