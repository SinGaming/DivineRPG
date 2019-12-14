package divinerpg.utils;

import divinerpg.DivineRPG;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CachedTexture {
    // single instance to store all loaded values
    private static final Map<String, ResourceLocation> values = new HashMap<>();
    public static final CachedTexture PROJECTILES = new CachedTexture("textures/projectiles/%s.png");
    public static final CachedTexture ENTITIES = new CachedTexture("textures/entity/%s.png");

    private final String pattern;

    public CachedTexture(String pattern) {
        this.pattern = pattern;
    }

    public ResourceLocation getTexture(String name) {
        return values.computeIfAbsent(name, key -> new ResourceLocation(DivineRPG.MODID, String.format(pattern, name)));
    }
}
