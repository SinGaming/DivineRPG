package divinerpg.registry;

import divinerpg.api.DivineAPI;
import divinerpg.api.armor.*;
import net.minecraft.item.Items;
import net.minecraftforge.event.TickEvent;

public class PoweredArmorRegistry {

    /**
     * Below is example how to register powered armor
     */
    public static void register() {

        // TODO remove!!!
        // Describing what we need to wear to achieve powers from armor
        IArmorSet goldenArmor = new ArmorSet().withVariant(Items.GOLDEN_HELMET,
                Items.GOLDEN_CHESTPLATE,
                Items.GOLDEN_LEGGINGS,
                Items.GOLDEN_BOOTS,
                null);

        IPoweredArmorSet poweredGoldenArmor = new PoweredArmorSet(goldenArmor,
                // describing what happens on armor status changed
                (player, isEquipped) -> {
                    if (!isEquipped) {
                        ArmorEvents.speedUpPlayer(player, 1, true);
                    }
                    // adding special ability by event class
                }).addAbility(TickEvent.PlayerTickEvent.class, event -> ArmorEvents.speedUpPlayer(event.player, 2, false));


        DivineAPI.addPowerHandlers(poweredGoldenArmor);
    }
}
