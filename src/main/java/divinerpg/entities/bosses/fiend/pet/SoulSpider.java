package divinerpg.entities.bosses.fiend.pet;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class SoulSpider extends DivineMonster {
    protected static final DataParameter<Integer> LIVE_DURATION = EntityDataManager.createKey(SoulSpider.class, DataSerializers.VARINT);

    public SoulSpider(World world) {
        super(EntitiesRegistry.soul_spider, world, SoundRegistry.GROWL_HURT, SoundRegistry.GROWL, 0.35F);
    }

    @Override
    protected void registerData() {
        super.registerData();

        getDataManager().register(LIVE_DURATION, 20);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(35, 12);
    }

    @Override
    public void tick() {
        super.tick();

        if (!world.isRemote() && ticksExisted > getMaxLiveInTicks()) {
            remove();
        }
    }

    public int getMaxLiveInTicks() {
        return getDataManager().get(LIVE_DURATION);
    }

    public void setMaxLiveInTicks(int ticks) {
        getDataManager().set(LIVE_DURATION, ticks);
    }
}
