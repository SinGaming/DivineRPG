package divinerpg.utils;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class RGBHelper {
    public static Vec3d vecFromRGB(int red, int green, int blue) {
        float actualRed = red > 0
                ? red / 255F
                : 0;
        float actualGreen = green > 0
                ? green / 255F
                : 0;
        float actualBlue = blue > 0
                ? blue / 255F
                : 0;

        return new Vec3d(actualRed, actualGreen, actualBlue);
    }

    public static Vec3d vecFromColor(Color color) {
        return vecFromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static IParticleData particlefromRGB(int red, int green, int blue) {
        Vec3d color = vecFromRGB(red, green, blue);
        return new RedstoneParticleData((float) color.x, (float) color.y, (float) color.z, 1);
    }
}
