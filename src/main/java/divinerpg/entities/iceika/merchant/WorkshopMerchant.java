package divinerpg.entities.iceika.merchant;

import divinerpg.entities.base.villager.DivineVillager;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.VillagerRegistry;
import divinerpg.utils.CachedTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class WorkshopMerchant extends DivineVillager {
    private static final List<String> MESSAGES = Arrays.asList("message.merchant.ho", "message.merchant.out", "message.merchant.in", "message.merchant.burr");

    protected WorkshopMerchant(EntityType<? extends VillagerEntity> entityType, World world, IVillagerType type, VillagerProfession profession) {
        super(entityType, world, type, profession);
    }

    public WorkshopMerchant(World world) {
        this(EntitiesRegistry.workshop_merchant, world, VillagerRegistry.ICEIKA, VillagerRegistry.workshop_merchant);
    }


    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        boolean result = super.processInteract(player, hand);
        if (result) {
            sendRandomMessage(player);
        }
        return result;
    }

    /**
     * Should always return true!
     *
     * @return
     */
    private void sendRandomMessage(PlayerEntity player) {
        player.sendMessage(new TranslationTextComponent("entity.divinerpg." + getType().getRegistryName().getPath()).applyTextStyle(TextFormatting.AQUA)
                .appendText(" : ")
                .appendSibling(
                        new TranslationTextComponent(
                                MESSAGES.get(rand.nextInt(MESSAGES.size()))
                        )
                ));
    }

    @Override
    protected ResourceLocation getGUI() {
        return CachedTexture.GUI.getTexture("workshop_worker");
    }
}
