package divinerpg.tile.ayeraco.beam;

import divinerpg.registry.TileEntityRegistry;
import divinerpg.tile.base.DivineTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;

public class AyeracoBeam extends DivineTileEntity {
    private final static String key = "BeamColor";
    private String color;
    private long time;
    private float beam;

    public AyeracoBeam() {
        this(BossInfo.Color.GREEN);
    }

    public AyeracoBeam(BossInfo.Color color) {
        super(TileEntityRegistry.ayeraco_beam);
        this.color = color.getName();
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return Math.pow(256, 2);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        if (compound.contains(key))
            color = compound.getString(key);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT result = super.write(compound);

        if (color != null)
            result.putString(key, color);

        return result;
    }

    public String getColor() {
        return color;
    }

    public float getScale() {
        int ticks = (int) (this.world.getGameTime() - this.time);
        this.time = this.world.getGameTime();

        if (ticks > 1) {
            this.beam -= (float) ticks / 40.0F;
            if (this.beam < 0.0F)
                this.beam = 0.0F;
        }

        this.beam += 0.025F;

        if (this.beam > 1.0F)
            this.beam = 1.0F;

        return this.beam;
    }
}
