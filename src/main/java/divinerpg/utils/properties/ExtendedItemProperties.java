package divinerpg.utils.properties;

import net.minecraft.item.Item;

public class ExtendedItemProperties extends Item.Properties {
    public ISpawnEntity spawnBullet;
    public Item ammo;
    public int count = -1;
    public int delay = 0;
    public int useDuration = 0;
    public int arcana = 0;

    /**
     * Adding shoot ability
     *
     * @param spawnBullet - function for creating bullet entity
     * @return this
     */
    public ExtendedItemProperties withBulletOnLeftClick(ISpawnEntity spawnBullet) {
        this.spawnBullet = spawnBullet;
        return this;
    }

    /**
     * Consuming some amount of ammo
     *
     * @param ammo  - ammo item
     * @param count - consuming count at once
     * @return this
     */
    public ExtendedItemProperties withAmmo(Item ammo, int count) {
        this.ammo = ammo;
        this.count = count;
        return this;
    }

    /**
     * Set delay between shoots
     */
    public ExtendedItemProperties withDelay(int ticks) {
        this.delay = ticks;
        return this;
    }

    /**
     * Set max duration like for bow
     */
    public ExtendedItemProperties withUseDuration(int duration) {
        this.useDuration = duration;
        return this;
    }

    /**
     * Set arcana usage per shot
     */
    public ExtendedItemProperties useArcana(int arcana) {
        this.arcana = arcana;
        return this;
    }
}
