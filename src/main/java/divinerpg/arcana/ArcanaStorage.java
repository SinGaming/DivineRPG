package divinerpg.arcana;

import divinerpg.api.arcana.IArcana;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ArcanaStorage implements Capability.IStorage<IArcana> {
    private static final String arcana = "arcana";
    private static final String regenDelay = "regenDelay";
    private static final String maxArcana = "maxArcana";

    @Nullable
    @Override
    public INBT writeNBT(Capability<IArcana> capability, IArcana instance, Direction side) {
        CompoundNBT result = new CompoundNBT();

        result.putInt(regenDelay, instance.getRegenCooldown());
        result.putFloat(arcana, instance.getArcana());
        result.putFloat(maxArcana, instance.getMaxArcana());

        return result;
    }

    @Override
    public void readNBT(Capability<IArcana> capability, IArcana instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT) nbt;

        if (tag != null) {
            instance.setMaxArcana(tag.getFloat(maxArcana));
            instance.setRegenCooldown(tag.getInt(regenDelay));
            instance.setArcana(tag.getFloat(arcana), true);
        }
    }
}
