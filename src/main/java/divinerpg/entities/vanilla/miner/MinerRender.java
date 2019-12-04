package divinerpg.entities.vanilla.miner;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class MinerRender extends DivineRender<Miner, BipedModel<Miner>> {

    public MinerRender(EntityRendererManager manager) {
        super(manager, new MinerModel<>(), 0, "miner");

        this.addLayer(new BipedArmorLayer<>(this,
                new MinerModel<>(0.5F, true), new MinerModel<>(1.0F, true)));
    }
}
