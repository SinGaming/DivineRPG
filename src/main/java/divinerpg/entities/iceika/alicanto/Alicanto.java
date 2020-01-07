package divinerpg.entities.iceika.alicanto;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

// TODO rethink movement
public class Alicanto extends DivineMonster {
    public Alicanto(World world) {
        super(EntitiesRegistry.alicanto, world, SoundRegistry.ALICANTO_HURT, SoundRegistry.ALICANTO, 1.3F);

        setPathPriority(PathNodeType.WATER, -1);
    }
}
