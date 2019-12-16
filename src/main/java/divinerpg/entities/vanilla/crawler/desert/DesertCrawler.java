package divinerpg.entities.vanilla.crawler.desert;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class DesertCrawler extends DivineMonster {

    public DesertCrawler(World world) {
        this(EntitiesRegistry.desert_crawler, world);
    }

    public DesertCrawler(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world, SoundRegistry.CRAWLER_HURT, SoundRegistry.CRAWLER, 1.15F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(30, 4);
    }
}
