package divinerpg.api.arcana;

import divinerpg.arcana.Arcana;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArcanaProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IArcana.class)
    public static final Capability<IArcana> ARCANA_CAPABILITY = null;
    private LazyOptional<IArcana> instance = LazyOptional.of(new NonNullSupplier<IArcana>() {
        @Nonnull
        @Override
        public IArcana get() {
            return getInstance();
        }
    });

    /**
     * Some functions
     */
    private IArcana getInstance() {
        return new Arcana();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ARCANA_CAPABILITY
                ? instance.cast()
                : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return ARCANA_CAPABILITY.getStorage().writeNBT(ARCANA_CAPABILITY,
                this.instance.orElse(getInstance()), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        ARCANA_CAPABILITY.getStorage().readNBT(ARCANA_CAPABILITY,
                this.instance.orElse(getInstance()), null, nbt);
    }
}
