package divinerpg.entities.vanilla.jack;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class JackOManRender extends DivineRender<JackOMan, JackOManModel> {
    public JackOManRender(EntityRendererManager manager) {
        super(manager, new JackOManModel(), 0, "jackoman");
    }

    @Override
    public void doRender(JackOMan entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
