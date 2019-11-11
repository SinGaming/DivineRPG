package divinerpg.utils;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.RedstoneParticleData;

public class DivineParticleTypes {
    public static final IParticleData EDEN = createFromRGB(219, 182, 17);
    public static final IParticleData WILDWOOD = createFromRGB(16, 83, 218);
    public static final IParticleData APALACHIA = createFromRGB(142, 54, 165);
    public static final IParticleData SKYTHERN = createFromRGB(158, 142, 163);
    public static final IParticleData MORTUM = createFromRGB(33, 3, 6);
    public static final IParticleData HALITE = createFromRGB(75, 194, 75);

    private static IParticleData createFromRGB(int red, int green, int blue) {
        return new RedstoneParticleData(red / 256F, green / 256F, blue / 256F, 1);
    }
}
