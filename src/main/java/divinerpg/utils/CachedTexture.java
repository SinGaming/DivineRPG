package divinerpg.utils;

import divinerpg.DivineRPG;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CachedTexture implements ITextured {
    // single instance to store all loaded values
    private static final Map<String, ResourceLocation> values = new HashMap<>();
    private final String name;

    public CachedTexture(String name, String pattern) {
        this.name = name;

        if (!values.containsKey(name)) {
            values.put(name, new ResourceLocation(DivineRPG.MODID, String.format(pattern, name)));
        }
    }

    public static CachedTexture createForProjectiles(String name) {
        return new CachedTexture(name, "textures/projectiles/%s.png");
    }

    @Override
    public ResourceLocation getTexture() {
        return values.get(name);
    }
}
