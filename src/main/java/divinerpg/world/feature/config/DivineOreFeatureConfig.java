package divinerpg.world.feature.config;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class DivineOreFeatureConfig implements IFeatureConfig {

    public final CustomFillerBlockType canReplace;
    public final BlockState state;
    public final int size;

    public DivineOreFeatureConfig(CustomFillerBlockType canReplace, BlockState state, int size) {
        this.canReplace = canReplace;
        this.state = state;
        this.size = size;
    }

    public static DivineOreFeatureConfig deserialize(Dynamic<?> opts) {
        int i = opts.get("size").asInt(0);
        CustomFillerBlockType type = CustomFillerBlockType.find(opts.get("target").asString(""));
        BlockState blockstate = opts.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new DivineOreFeatureConfig(type, blockstate, i);
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("size"),
                ops.createInt(this.size), ops.createString("target"),
                ops.createString(this.canReplace.id),
                ops.createString("state"),
                BlockState.serialize(ops, this.state).getValue())));
    }
}
