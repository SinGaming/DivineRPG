package divinerpg.world.feature.config;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.HashMap;

public class FlowerFeatureConfig extends NoFeatureConfig {
    public final int flowerW;
    public final BlockState flower;
    public final int bushW;
    public final BlockState bush;
    public final int grassW;
    public final BlockState grass;

    public FlowerFeatureConfig(int flowerW, BlockState flower, int bushW, BlockState bush, int grassW, BlockState grass) {
        this.flowerW = flowerW;
        this.flower = flower;
        this.bushW = bushW;
        this.bush = bush;
        this.grassW = grassW;
        this.grass = grass;
    }

    public static <T> FlowerFeatureConfig deserialize(Dynamic<T> opts) {
        return new FlowerFeatureConfig(
                opts.get("flowerW").asInt(0),
                opts.get("flower").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()),

                opts.get("bushW").asInt(0),
                opts.get("bush").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()),

                opts.get("grassW").asInt(0),
                opts.get("grass").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState())
        );
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        HashMap<T, T> map = new HashMap<T, T>() {{
            put(ops.createString("flowerW"), ops.createInt(flowerW));
            put(ops.createString("bushW"), ops.createInt(bushW));
            put(ops.createString("grassW"), ops.createInt(grassW));

            put(ops.createString("flower"), BlockState.serialize(ops, flower).getValue());
            put(ops.createString("bush"), BlockState.serialize(ops, bush).getValue());
            put(ops.createString("grass"), BlockState.serialize(ops, grass).getValue());
        }};

        return new Dynamic<T>(ops, ops.createMap(map));
    }
}
