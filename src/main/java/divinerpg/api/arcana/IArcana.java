package divinerpg.api.arcana;

import net.minecraft.entity.Entity;

public interface IArcana {

    /**
     * Gets max of entity's arcana
     */
    float getMaxArcana();

    /**
     * Sets max arcana
     */
    void setMaxArcana(float point);

    /**
     * Get regen cooldown in ticks
     */
    int getRegenCooldown();

    /**
     * Set cooldown ticks count
     */
    void setRegenCooldown(int ticks);

    /**
     * Gets current amount of arcana
     */
    float getArcana();

    /**
     * Sets amount of arcana
     *
     * @param points     - current arcana points
     * @param sendPacket - should send packet
     */
    void setArcana(float points, boolean sendPacket);

    /**
     * Consume some arcana
     */
    void consume(float points);

    /**
     * Regen arcana
     */
    void fill(float points);

    /**
     * Gets current entity
     */
    Entity getAssotitatedEntity();
}
