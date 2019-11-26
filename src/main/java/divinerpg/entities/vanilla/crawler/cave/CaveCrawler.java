package divinerpg.entities.vanilla.crawler.cave;

import divinerpg.entities.vanilla.crawler.desert.DesertCrawler;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class CaveCrawler extends DesertCrawler {
    public CaveCrawler(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public CaveCrawler(World world) {
        super(EntitiesRegistry.cave_crawler, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 35){
            return -1;
        }

        return super.getBlockPathWeight(pos, worldIn);
    }
}
