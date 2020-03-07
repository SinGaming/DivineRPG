package divinerpg.structure;

import net.minecraft.nbt.NBTTagCompound;
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

    private ResourceLocation location;

    /**
     * NBT ctor
     */
    public DivineStructureComponentTemplate() {

    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start) {
        this(manager, location, start, null);
    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start, @Nullable PlacementSettings settings) {
        super(0);
        this.location = location;

        if (settings == null)
            settings = this.placeSettings;

        Template template = manager.getTemplate(null, location);
        setup(template, start, settings);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager) {
        super.readStructureFromNBT(tagCompound, manager);

        location = new ResourceLocation(tagCompound.getString("TmpRes"));
        setup(manager.getTemplate(null, location), templatePosition, placeSettings);
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        super.writeStructureToNBT(tagCompound);

        tagCompound.setString("TmpRes", location.toString());
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, World world, Random random, StructureBoundingBox structureBoundingBox) {

    }
}
