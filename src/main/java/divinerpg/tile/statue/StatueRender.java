package divinerpg.tile.statue;

import divinerpg.tile.base.DivineTileEModel;
import divinerpg.tile.statue.models.AncientEntityModel;
import divinerpg.tile.statue.models.VamacheronModel;
import divinerpg.utils.CachedTexture;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatueRender extends TileEntityRenderer<TileEntityStatue> {
    private static final HashMap<String, DivineTileEModel<TileEntityStatue>> models = new HashMap<String, DivineTileEModel<TileEntityStatue>>() {{
        put("ancient_entity_statue", new AncientEntityModel());
        put("vamacheron_statue", new VamacheronModel());
    }};

    public static List<String> getStatueNames() {
        return new ArrayList<>(models.keySet());
    }

    @Override
    public void render(TileEntityStatue te, double x, double y, double z, float partialTicks, int destroyStage) {
        super.render(te, x, y, z, partialTicks, destroyStage);

        World world = te.getWorld();
        if (world == null)
            return;

        float rotation = 0;

        BlockState state = world.getBlockState(te.getPos());
        if (state.has(BlockStateProperties.FACING)) {
            Direction direction = state.get(BlockStateProperties.FACING);
            rotation = direction.getHorizontalAngle();
        }

        Minecraft.getInstance().getTextureManager().bindTexture(findResource(te));

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.7F, (float) z + 0.5F);
        GL11.glScaled(0.5, 0.5, 0.5);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);

        findModel(te).render(te, 0.0625F);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    private ResourceLocation findResource(TileEntityStatue te) {
        return CachedTexture.MODEL.getTexture(te.getName());
    }

    private DivineTileEModel<TileEntityStatue> findModel(TileEntityStatue te) {
        return models.getOrDefault(te.getName(), models.entrySet().stream().findFirst().get().getValue());
    }
}
