package divinerpg.tile.statue;

import divinerpg.registry.TileEntityRegistry;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStatue extends TileEntity {
    private final static String key = "StatueID";
    private String name;

    public TileEntityStatue() {
        this("");
    }

    public TileEntityStatue(String name) {
        super(TileEntityRegistry.statue);
        this.name = name;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putString(key, name);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        if (compound.contains(key)) {
            name = compound.getString(key);
        }
    }

    public String getName() {
        return name;
    }
}
