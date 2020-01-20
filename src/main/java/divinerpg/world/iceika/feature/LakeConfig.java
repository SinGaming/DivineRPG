package divinerpg.world.iceika.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class LakeConfig implements IFeatureConfig {

    public final BlockState liquid;
    public final BlockState topBlock;

    public LakeConfig(BlockState liquid, BlockState topBlock) {
        this.liquid = liquid;
        this.topBlock = topBlock;
    }

    public LakeConfig(Dynamic<?> opts){
        this(
                opts.get("liquid").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()),
                opts.get("config.topBlock").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState())
        );
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(
                ops.createString("liquid"), BlockState.serialize(ops, this.liquid).getValue(),
                ops.createString("topBlock"), BlockState.serialize(ops, this.topBlock).getValue()
        )));
    }
}
