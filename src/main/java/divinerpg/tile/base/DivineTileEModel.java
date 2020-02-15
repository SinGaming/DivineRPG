package divinerpg.tile.base;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LazyValue;

import java.util.List;

public class DivineTileEModel<T extends TileEntity> extends Model {
    protected LazyValue<List<ModelRenderer>> parts;

    protected DivineTileEModel() {
        parts = new LazyValue<>(() -> ReflectionHelper.getDeclaredFieldsValues(this, ModelRenderer.class));
    }

    public void render(T tile, float scale) {
        parts.getValue().forEach(x -> x.render(scale));
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
