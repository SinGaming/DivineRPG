package divinerpg.entities.vanilla.watcher;

import divinerpg.entities.base.DivineModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class WatcherModel<T extends Entity> extends DivineModel<T> {
    RendererModel Head;
    RendererModel Jaw;
    RendererModel Tentacle11;
    RendererModel Tentacle12;
    RendererModel Tentacle21;
    RendererModel Tentacle22;
    RendererModel Tentacle31;
    RendererModel Tentacle32;

    public WatcherModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new RendererModel(this, 0, 0);
        this.Head.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10);
        this.Head.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, -((float) Math.PI / 2F), 0.0F);
        this.Jaw = new RendererModel(this, 0, 20);
        this.Jaw.addBox(-5.0F, 0.0F, -10.0F, 10, 1, 10);
        this.Jaw.setRotationPoint(0.0F, 21.0F, 5.0F);
        this.Jaw.setTextureSize(64, 32);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.1745329F, 0.0F, 0.0F);
        this.Tentacle11 = new RendererModel(this, 40, 0);
        this.Tentacle11.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
        this.Tentacle11.setRotationPoint(5.0F, 16.0F, 0.0F);
        this.Tentacle11.setTextureSize(64, 32);
        this.Tentacle11.mirror = true;
        this.setRotation(this.Tentacle11, 0.0F, ((float) Math.PI / 2F), 0.0F);
        this.Tentacle12 = new RendererModel(this, 40, 6);
        this.Tentacle12.addBox(-4.0F, -1.0F, -1.0F, 5, 2, 2);
        this.Tentacle12.setRotationPoint(9.0F, 16.0F, 0.0F);
        this.Tentacle12.setTextureSize(64, 32);
        this.Tentacle12.mirror = true;
        this.setRotation(this.Tentacle12, 0.0F, ((float) Math.PI * 3F / 2F), 0.0F);
        this.Tentacle21 = new RendererModel(this, 40, 0);
        this.Tentacle21.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
        this.Tentacle21.setRotationPoint(-5.0F, 16.0F, 0.0F);
        this.Tentacle21.setTextureSize(64, 32);
        this.Tentacle21.mirror = true;
        this.setRotation(this.Tentacle21, 0.0F, ((float) Math.PI * 3F / 2F), 0.0F);
        this.Tentacle22 = new RendererModel(this, 40, 6);
        this.Tentacle22.addBox(-4.0F, -1.0F, -1.0F, 5, 2, 2);
        this.Tentacle22.setRotationPoint(-9.0F, 16.0F, 0.0F);
        this.Tentacle22.setTextureSize(64, 32);
        this.Tentacle22.mirror = true;
        this.setRotation(this.Tentacle22, 0.0F, ((float) Math.PI * 3F / 2F), 0.0F);
        this.Tentacle31 = new RendererModel(this, 52, 0);
        this.Tentacle31.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2);
        this.Tentacle31.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.Tentacle31.setTextureSize(64, 32);
        this.Tentacle31.mirror = true;
        this.setRotation(this.Tentacle31, 0.0F, 0.0F, 0.0F);
        this.Tentacle32 = new RendererModel(this, 40, 6);
        this.Tentacle32.addBox(-4.0F, -1.0F, -1.0F, 5, 2, 2);
        this.Tentacle32.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.Tentacle32.setTextureSize(64, 32);
        this.Tentacle32.mirror = true;
        this.setRotation(this.Tentacle32, 0.0F, ((float) Math.PI * 3F / 2F), 0.0F);
    }
}
