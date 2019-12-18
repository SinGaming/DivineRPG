package divinerpg.tile.base;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LazyLoadBase;

import java.util.List;

public class DivineTileEModel<T extends TileEntity> extends Model {
    protected LazyLoadBase<List<RendererModel>> parts;

    protected DivineTileEModel() {
        parts = new LazyLoadBase<>(() -> ReflectionHelper.getDeclaredFieldsValues(this, RendererModel.class));
    }

    public void render(T tile, float scale) {
        parts.getValue().forEach(x -> x.render(scale));
    }

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
