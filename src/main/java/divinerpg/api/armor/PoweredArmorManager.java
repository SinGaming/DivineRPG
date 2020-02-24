package divinerpg.api.armor;

import divinerpg.api.armor.interfaces.IPoweredArmor;
import divinerpg.api.armor.interfaces.IPoweredArmorManage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.*;
import java.util.stream.Collectors;

public class PoweredArmorManager implements IPoweredArmorManage {

    private final List<ForgeEventHandler<?>> abilities;
    private IPoweredArmor armor;
    private final Set<PlayerEntity> players = new HashSet<>();

    public PoweredArmorManager(IPoweredArmor armor) {

        this.armor = armor;

        // cloning abilities
        abilities = armor.getAbilityMap().values().stream()
                .map(x -> (ForgeEventHandler<?>) x.cloneWithPredicate(this::canExecute))
                .collect(Collectors.toList());
    }

    @Override
    public IPoweredArmor getArmorSet() {
        return armor;
    }

    @Override
    public void addPlayer(PlayerEntity player) {
        players.add(player);
        checkSubscription();

        getArmorSet().onEquppedChanged(player, true);
    }

    @Override
    public void removePlayer(PlayerEntity player) {
        players.remove(player);
        checkSubscription();

        getArmorSet().onEquppedChanged(player, false);
    }

    /**
     * Check wherever we can subscribe on events
     */
    private void checkSubscription() {
        // unsubscribe
        if (players.isEmpty()) {
            abilities.forEach(ForgeEventHandler::unsubscribe);
        }
        // subscribe
        else if (players.size() == 1) {
            abilities.forEach(ForgeEventHandler::subscribe);
        }
    }

    /**
     * Check if we can execute current event for armor set.
     * Player from event should be equipped with current set
     *
     * @param e   - event
     * @param <T> - event type
     * @return
     */
    private <T extends Event> boolean canExecute(T e) {
        if (players.isEmpty())
            return false;

        return players.contains(getPlayerFromEvent(e));
    }

    /**
     * We need to get player reference from Event.
     * Possible weak-perfomance method
     *
     * @return
     */
    private <T extends Event> net.minecraft.entity.Entity getPlayerFromEvent(T e) {
        if (e instanceof EntityEvent && ((EntityEvent) e).getEntity() instanceof PlayerEntity) {
            return ((EntityEvent) e).getEntity();
        }

        if (e instanceof TickEvent.PlayerTickEvent) {
            return ((TickEvent.PlayerTickEvent) e).player;
        }

        if (e instanceof LivingHurtEvent
                && ((LivingHurtEvent) e).getSource().getTrueSource() instanceof PlayerEntity) {
            return ((LivingHurtEvent) e).getSource().getTrueSource();
        }

        return null;
    }
}
