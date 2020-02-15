package divinerpg.entities.bosses.ayeraco;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;

import javax.annotation.Nullable;

public class AyeracoRender extends DivineRender<Ayeraco, AyeracoModel> {
    public AyeracoRender(EntityRendererManager manager) {
        super(manager, new AyeracoModel(), 0, "", 2F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(Ayeraco entity) {
        BossInfo.Color color = entity.getColor();
        if (!AyeracoManager.beamLocations.containsKey(entity.getColor())) {
            // use default color
            color = AyeracoManager.beamLocations.keySet().stream().findAny().get();
        }

        return CachedTexture.ENTITIES.getTexture(String.format("ayeraco_%s", color.getName()));
    }
}
