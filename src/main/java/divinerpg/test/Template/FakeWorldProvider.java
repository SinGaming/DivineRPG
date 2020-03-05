package divinerpg.test.Template;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class FakeWorldProvider extends WorldProvider {
    @Override
    public DimensionType getDimensionType() {
        return DimensionType.OVERWORLD;
    }
}
