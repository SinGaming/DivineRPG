package divinerpg.arcana;

import divinerpg.DivineRPG;
import divinerpg.api.arcana.IArcana;
import divinerpg.messages.ArcanaMessage;
import divinerpg.utils.time.TimedAction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.concurrent.atomic.AtomicReference;

public class Arcana implements IArcana {

    private final AtomicReference<Integer> cooldown;
    private final AtomicReference<Float> arcana;
    private final AtomicReference<Float> max;

    private TimedAction action;

    private Entity entity = null;

    /**
     * Nessesary for init creation
     */
    public Arcana() {
        cooldown = new AtomicReference<>(0);
        arcana = new AtomicReference<>(0.0F);
        max = new AtomicReference<>(200.0F);

        action = new TimedAction(getRegenCooldown());
    }

    /**
     * Assotiating entity with capability
     * @param e - player
     */
    public Arcana withPlayer(Entity e) {
        entity = e;

        updateTimedAction();
        return this;
    }


    @Override
    public float getMaxArcana() {
        return max.get();
    }

    @Override
    public void setMaxArcana(float point) {
        // Can't be less 50
        setNumberAndSendPacket(max, Math.max(50, point));
    }

    @Override
    public int getRegenCooldown() {
        return cooldown.get();
    }

    @Override
    public void setRegenCooldown(int ticks) {
        // Can't set cooldown more than 100 ticks
        setNumberAndSendPacket(cooldown, MathHelper.clamp(ticks, 0, 100));
        updateTimedAction();
    }

    @Override
    public float getArcana() {
        return arcana.get();
    }

    @Override
    public void setArcana(float points, boolean sendPacket) {
        float newValue = MathHelper.clamp(points, 0, max.get());
        setNumberAndSendPacket(arcana, newValue, sendPacket);
    }

    @Override
    public void consume(float points) {
        setArcana(arcana.get() - points, true);
    }

    @Override
    public void fill(float points) {
        if (action.tryExecute()) {
            consume(points * -1);
        }
    }

    @Override
    public Entity getAssotitatedEntity() {
        return entity;
    }


    private void updateTimedAction() {
        action.updateTicksCount(getRegenCooldown());
    }

    private <T> void setNumberAndSendPacket(AtomicReference<T> prev, T val) {
        setNumberAndSendPacket(prev, val, true);
    }

    private <T> void setNumberAndSendPacket(AtomicReference<T> prev, T val, boolean sendPacket) {
        if (prev.get() != val) {
            prev.set(val);

            if (sendPacket) {
                sendPacket();
            }
        }
    }

    /**
     * Sending packet to client
     */
    private void sendPacket() {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) this.entity;
            DivineRPG.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new ArcanaMessage(this));
        }
    }
}
