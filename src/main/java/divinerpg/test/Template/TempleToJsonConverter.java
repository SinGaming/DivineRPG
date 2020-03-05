package divinerpg.test.Template;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class TempleToJsonConverter {
    private final List<Template.BlockInfo> blocks = Lists.newArrayList();
    private final List<Template.EntityInfo> entities = Lists.newArrayList();
    private final String author = "Dash_Legacy_Template_Converter";
    private BlockPos size;

    public TempleToJsonConverter(FakeWorld world, ChunkPos chunk) {
        List<BlockPos> poses = Lists.newArrayList(world.getPosIterator(chunk));

        int x = chunk.getXStart();
        int z = chunk.getZStart();

        poses.forEach(pos -> {
            IBlockState state = world.getBlockState(pos);
            TileEntity tile = world.getTileEntity(pos);
            Entity entity = world.getEntity(pos);

            // do not need offsetm only use 0-15 numbers
            BlockPos correctedPos = pos.add(-x, 0, -z);

            if (entity != null) {
                entities.add(new Template.EntityInfo(entity.getLookVec(), correctedPos, entity.serializeNBT()));
            }

            if (state != null) {
                NBTTagCompound nbt = tile == null
                        ? null
                        : tile.serializeNBT();

                blocks.add(new Template.BlockInfo(correctedPos, state, nbt));
            }
        });

        size = new BlockPos(
                poses.stream().map(Vec3i::getX).max(Integer::compareTo).orElse(x)
                        - poses.stream().map(Vec3i::getX).min(Integer::compareTo).orElse(0)
                        + 1,

                poses.stream().map(Vec3i::getY).max(Integer::compareTo).orElse(0)
                        - poses.stream().map(Vec3i::getY).min(Integer::compareTo).orElse(0)
                        + 1,

                poses.stream().map(Vec3i::getZ).max(Integer::compareTo).orElse(z)
                        - poses.stream().map(Vec3i::getZ).min(Integer::compareTo).orElse(0)
                        + 1);
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();

        BasicPalette template$basicpalette = new BasicPalette();
        NBTTagList nbttaglist = new NBTTagList();

        NBTTagCompound nbttagcompound;
        for (Iterator var4 = this.blocks.iterator(); var4.hasNext(); nbttaglist.appendTag(nbttagcompound)) {
            Template.BlockInfo template$blockinfo = (Template.BlockInfo) var4.next();
            nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("pos", this.writeInts(template$blockinfo.pos.getX(), template$blockinfo.pos.getY(), template$blockinfo.pos.getZ()));
            nbttagcompound.setInteger("state", template$basicpalette.idFor(template$blockinfo.blockState));
            if (template$blockinfo.tileentityData != null) {
                nbttagcompound.setTag("nbt", template$blockinfo.tileentityData);
            }
        }

        NBTTagList nbttaglist1 = new NBTTagList();

        NBTTagCompound nbttagcompound1;
        for (Iterator var9 = this.entities.iterator(); var9.hasNext(); nbttaglist1.appendTag(nbttagcompound1)) {
            Template.EntityInfo template$entityinfo = (Template.EntityInfo) var9.next();
            nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setTag("pos", this.writeDoubles(template$entityinfo.pos.x, template$entityinfo.pos.y, template$entityinfo.pos.z));
            nbttagcompound1.setTag("blockPos", this.writeInts(template$entityinfo.blockPos.getX(), template$entityinfo.blockPos.getY(), template$entityinfo.blockPos.getZ()));
            if (template$entityinfo.entityData != null) {
                nbttagcompound1.setTag("nbt", template$entityinfo.entityData);
            }
        }

        NBTTagList nbttaglist2 = new NBTTagList();
        Iterator var12 = template$basicpalette.iterator();

        while (var12.hasNext()) {
            IBlockState iblockstate = (IBlockState) var12.next();
            nbttaglist2.appendTag(NBTUtil.writeBlockState(new NBTTagCompound(), iblockstate));
        }

        FMLCommonHandler.instance().getDataFixer().writeVersionData(nbt);
        nbt.setTag("palette", nbttaglist2);
        nbt.setTag("blocks", nbttaglist);
        nbt.setTag("entities", nbttaglist1);
        nbt.setTag("size", this.writeInts(this.size.getX(), this.size.getY(), this.size.getZ()));
        nbt.setString("author", author);
        nbt.setInteger("DataVersion", 1343);
        return nbt;
    }

    // region Helping conversion

    private NBTTagList writeInts(int... values) {
        NBTTagList nbttaglist = new NBTTagList();
        int[] var3 = values;
        int var4 = values.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int i = var3[var5];
            nbttaglist.appendTag(new NBTTagInt(i));
        }

        return nbttaglist;
    }

    private NBTTagList writeDoubles(double... values) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] var3 = values;
        int var4 = values.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            double d0 = var3[var5];
            nbttaglist.appendTag(new NBTTagDouble(d0));
        }

        return nbttaglist;
    }

    public static class BasicPalette implements Iterable<IBlockState> {
        public static final IBlockState DEFAULT_BLOCK_STATE;

        static {
            DEFAULT_BLOCK_STATE = Blocks.AIR.getDefaultState();
        }

        final ObjectIntIdentityMap<IBlockState> ids;
        private int lastId;

        private BasicPalette() {
            this.ids = new ObjectIntIdentityMap(16);
        }

        public int idFor(IBlockState state) {
            int i = this.ids.get(state);
            if (i == -1) {
                i = this.lastId++;
                this.ids.put(state, i);
            }

            return i;
        }

        @Nullable
        public IBlockState stateFor(int id) {
            IBlockState iblockstate = this.ids.getByValue(id);
            return iblockstate == null ? DEFAULT_BLOCK_STATE : iblockstate;
        }

        public Iterator<IBlockState> iterator() {
            return this.ids.iterator();
        }

        public void addMapping(IBlockState p_189956_1_, int p_189956_2_) {
            this.ids.put(p_189956_1_, p_189956_2_);
        }
    }

    // endregion
}
