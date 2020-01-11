package divinerpg.world.structure;

import divinerpg.DivineRPG;
import net.minecraft.crash.CrashReport;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class TemplateStructurePart extends TemplateStructurePiece {
    //private static final String templateKey = "TRS";
    protected ResourceLocation location;

    /**
     * Creating piece only from location
     *
     * @param location
     */
    public TemplateStructurePart(ResourceLocation location, TemplateManager manager, BlockPos position) {
        super(getOrRegister("divine_template"), 0);
        init(location, manager, position);
    }

    public TemplateStructurePart(TemplateManager manager, CompoundNBT nbt) {
        super(getOrRegister("divine_template"), nbt);
        //init(new ResourceLocation(nbt.getString(templateKey)), manager, templatePosition);
        init(location, manager, templatePosition);
    }

    /**
     * Register structure once
     *
     * @param name
     * @return
     */
    private static IStructurePieceType getOrRegister(String name) {
        ResourceLocation key = new ResourceLocation(DivineRPG.MODID, name);
        return Registry.STRUCTURE_PIECE.getValue(key).orElse(IStructurePieceType.register(TemplateStructurePart::new, key.toString()));
    }

    private void init(ResourceLocation location, TemplateManager manager, BlockPos position) {
        this.location = location;

        Template template = manager.getTemplate(location);
        if (template == null) {
            CrashReport.makeCrashReport(new IllegalArgumentException(), "Can't find structure NBT from here:" + location.toString());
            return;
        }

        PlacementSettings settings = new PlacementSettings()
                .setChunk(new ChunkPos(position))
                .addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);

        setup(template, position, settings);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);

//        if (location != null)
//            tagCompound.putString(templateKey, location.toString());
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        /**
         * Adds loot table based on meta.
         * For example, we have that part of structure NBT:
         *             nbt: {
         *                 Items: []
         *                 id: "divinerpg:te_iceika_chest"
         *                 Lock: ""
         *                 metadata: "chest"
         *             }
         *             pos: [
         *                 18,
         *                 1,
         *                 1,
         *             ]
         *             state: 2 <-- That is for chest
         *
         * The string in 'metadata' will be used as key to loot tables
         */

        ResourceLocation location = StructureConstants.STRUCTURE_LOOT_TABLES.get(function);

        if (location != null)
            LockableLootTileEntity.setLootTable(worldIn, rand, pos, location);
    }
}
