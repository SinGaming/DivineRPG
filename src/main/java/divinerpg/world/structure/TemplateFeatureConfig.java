package divinerpg.world.structure;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

public class TemplateFeatureConfig implements IFeatureConfig {
    public final ResourceLocation template;
    public final BlockState surface;

    public TemplateFeatureConfig(ResourceLocation template, BlockState surface) {
        this.template = template;
        this.surface = surface;
    }

    public TemplateFeatureConfig(Dynamic<?> ops) {
        this(new ResourceLocation(
                ops.get("template").asString("")),
                ops.get("surface").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()));
    }

    public PlacementSettings create(Template template, BlockPos pos) {
        PlacementSettings settings = new PlacementSettings();

        settings.setBoundingBox(new MutableBoundingBox(pos, pos.add(template.getSize())));
        settings.setChunk(new ChunkPos(pos));

        return settings;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(
                ImmutableMap.of(
                        ops.createString("template"),
                        ops.createString(this.template.toString()),
                        ops.createString("surface"),
                        BlockState.serialize(ops, this.surface).getValue()
                )
        ));
    }
}
