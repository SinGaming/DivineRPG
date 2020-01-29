package divinerpg.world.arcana;

import divinerpg.world.arcana.maze.RoomDescription;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class ArcanaMazeRoomPiece extends TemplateStructurePiece {
    private ResourceLocation loc;

    public ArcanaMazeRoomPiece(IStructurePieceType type, TemplateManager manager, RoomDescription description) {
        super(type, 0);

        this.templatePosition = description.getPos();
        this.loc = description.getLocation();

        init(manager);
    }

    public ArcanaMazeRoomPiece(IStructurePieceType type,TemplateManager manager, CompoundNBT tag) {
        super(type, tag);

        loc = new ResourceLocation(tag.getString("Loc"));

        init(manager);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);

        tagCompound.putString("Loc", loc.toString());
    }

    private void init(TemplateManager manager){
        Template template = manager.getTemplateDefaulted(loc);
        this.setup(template, this.templatePosition, new PlacementSettings());
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {

    }
}
