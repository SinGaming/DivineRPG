package divinerpg.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;

public class DivineStructureComponentTemplate extends StructureComponentTemplate {

    /**
     * NBT ctor
     */
    DivineStructureComponentTemplate() {

    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start) {
        this(manager, location, start, null);
    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start, @Nullable PlacementSettings settings) {
        super(0);

        if (settings == null)
            settings = this.placeSettings;

        Template template = manager.getTemplate(null, location);
        setup(template, start, settings);
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, World world, Random random, StructureBoundingBox structureBoundingBox) {

    }
}
