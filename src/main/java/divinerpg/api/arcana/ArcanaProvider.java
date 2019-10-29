package divinerpg.api.arcana;

import divinerpg.arcana.Arcana;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArcanaProvider implements ICapabilitySerializable<INBT> {

    private static final IArcana instance = new Arcana();
    private final LazyOptional<IArcana> lazyOptional = LazyOptional.of(() -> instance);


    @CapabilityInject(IArcana.class)
    public static final Capability<IArcana> ARCANA_CAPABILITY = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ARCANA_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public INBT serializeNBT() {
        return ARCANA_CAPABILITY.writeNBT(instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        ARCANA_CAPABILITY.readNBT(instance, null, nbt);
    }

}
