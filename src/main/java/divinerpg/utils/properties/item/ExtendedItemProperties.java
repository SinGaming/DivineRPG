package divinerpg.utils.properties.item;

import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExtendedItemProperties extends Item.Properties {
    public IShootEntity spawnBullet;
    public Supplier<Item> ammo;
    public int ammoConsumeCount = -1;
    public int cooldown = 0;
    public int bowLikeDuration = 0;
    public int arcana = 0;
    public IRightClick rightClick;
    public IHitEntity onHit;
    public boolean disableSword = false;
    public final List<ArrowItem> possibleArrows = new ArrayList<>();
    public boolean infiniteArrows = false;

    /**
     * Adding shoot ability
     *
     * @param spawnBullet - function for creating bullet entity
     * @return this
     */
    public ExtendedItemProperties withShooter(IShootEntity spawnBullet) {
        this.spawnBullet = spawnBullet;
        return this;
    }

    /**
     * Consuming some amount of ammo
     *
     * @param ammo  - ammo item
     * @param count - consuming ammoConsumeCount at once
     * @return this
     */
    public ExtendedItemProperties withAmmo(Supplier<Item> ammo, int count) {
        this.ammo = ammo;
        this.ammoConsumeCount = count;
        return this;
    }

    /**
     * Set cooldown between shoots
     */
    public ExtendedItemProperties withDelay(int ticks) {
        this.cooldown = ticks;
        return this;
    }

    /**
     * Set max duration like for bow
     */
    public ExtendedItemProperties withUseDuration(int duration) {
        this.bowLikeDuration = duration;
        return this;
    }

    /**
     * Set arcana usage per shot
     */
    public ExtendedItemProperties useArcana(int arcana) {
        this.arcana = arcana;
        return this;
    }

    /**
     * With callback on right click
     */
    public ExtendedItemProperties onRightClick(IRightClick rightClick) {
        this.rightClick = rightClick;
        return this;
    }

    /**
     * With callback on entity hit
     */
    public ExtendedItemProperties onHit(IHitEntity onHit) {
        this.onHit = onHit;
        return this;
    }

    /**
     * Disabling possiblity of sword hitting the entity
     *
     * @param isDisabled
     * @return
     */
    public ExtendedItemProperties disableSword(boolean isDisabled) {
        disableSword = isDisabled;
        return this;
    }

    /**
     * Adding arrows for shooting. The first will be used as default
     *
     * @param arrows - list of possible arrows
     */
    public ExtendedItemProperties withArrows(ArrowItem... arrows) {
        if (arrows != null && arrows.length > 0) {
            possibleArrows.addAll(Arrays.stream(arrows).collect(Collectors.toList()));
        }
        return this;
    }

    /**
     * Will bow need arrows at all or not
     */
    public ExtendedItemProperties infiiniteArrows(boolean isInfinite) {
        infiniteArrows = isInfinite;
        return this;
    }
}
