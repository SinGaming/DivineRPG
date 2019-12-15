package divinerpg.utils.portal;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.IWorld;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class DivineTeleporter extends Teleporter {
    private static final Logger LOGGER = LogManager.getLogger();
    protected final ServerWorld world;
    protected final Random random;
    protected final Map<ColumnPos, PortalPosition> destinationCoordinateCache = Maps.newHashMapWithExpectedSize(4096);
    private final Block PORTAL;
    private final Block FRAME;
    private final Object2LongMap<ColumnPos> field_222275_f = new Object2LongOpenHashMap<>();

    public DivineTeleporter(ServerWorld worldIn, Block portal, Block frame) {
        super(worldIn);
        this.world = worldIn;
        this.random = new Random(worldIn.getSeed());

        PORTAL = portal;
        FRAME = frame;
    }

    @Override
    public boolean func_222268_a(Entity entity, float yaw) {
        Vec3d vec3d = entity.getLastPortalVec();
        Direction direction = entity.getTeleportDirection();
        BlockPattern.PortalInfo portalInfo = this.func_222272_a(new BlockPos(entity), entity.getMotion(), direction, vec3d.x, vec3d.y, entity instanceof PlayerEntity);
        if (portalInfo == null) {
            return false;
        } else {
            Vec3d vec3d1 = portalInfo.field_222505_a;
            Vec3d vec3d2 = portalInfo.field_222506_b;
            entity.setMotion(vec3d2);
            entity.rotationYaw = yaw + (float) portalInfo.field_222507_c;
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity).connection.setPlayerLocation(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
                ((ServerPlayerEntity) entity).connection.captureCurrentPosition();
            } else {
                entity.setLocationAndAngles(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
            }

            return true;
        }
    }

    @Override
    @Nullable
    public BlockPattern.PortalInfo func_222272_a(BlockPos p_222272_1_, Vec3d p_222272_2_, Direction p_222272_3_, double p_222272_4_, double p_222272_6_, boolean p_222272_8_) {
        int i = 128;
        boolean flag = true;
        BlockPos blockpos = null;
        ColumnPos columnpos = new ColumnPos(p_222272_1_);
        if (!p_222272_8_ && this.field_222275_f.containsKey(columnpos)) {
            return null;
        } else {
            PortalPosition portalPosition = this.destinationCoordinateCache.get(columnpos);
            if (portalPosition != null) {
                blockpos = portalPosition.field_222267_a;
                portalPosition.lastUpdateTime = this.world.getGameTime();
                flag = false;
            } else {
                double d0 = Double.MAX_VALUE;

                for (int j = -128; j <= 128; ++j) {
                    BlockPos blockpos2;
                    for (int k = -128; k <= 128; ++k) {
                        for (BlockPos blockpos1 = p_222272_1_.add(j, this.world.getActualHeight() - 1 - p_222272_1_.getY(), k); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                            blockpos2 = blockpos1.down();
                            if (this.world.getBlockState(blockpos1).getBlock() == PORTAL) {
                                for (blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == PORTAL; blockpos2 = blockpos2.down()) {
                                    blockpos1 = blockpos2;
                                }

                                double d1 = blockpos1.distanceSq(p_222272_1_);
                                if (d0 < 0.0D || d1 < d0) {
                                    d0 = d1;
                                    blockpos = blockpos1;
                                }
                            }
                        }
                    }
                }
            }

            if (blockpos == null) {
                long l = this.world.getGameTime() + 300L;
                this.field_222275_f.put(columnpos, l);
                return null;
            } else {
                if (flag) {
                    this.destinationCoordinateCache.put(columnpos, new PortalPosition(blockpos, this.world.getGameTime()));
                    Logger logger = LOGGER;
                    Supplier[] asupplier = new Supplier[2];
                    Dimension dimension = this.world.getDimension();
                    asupplier[0] = dimension::getType;
                    asupplier[1] = () -> {
                        return columnpos;
                    };
                    logger.debug("Adding divine portal ticket for {}:{}", asupplier);
                    this.world.getChunkProvider().func_217228_a(TicketType.PORTAL, new ChunkPos(blockpos), 3, columnpos);
                }

                BlockPattern.PatternHelper patternHelper = createPatternHelper(this.world, blockpos);
                return patternHelper.func_222504_a(p_222272_3_, blockpos, p_222272_6_, p_222272_2_, p_222272_4_);
            }
        }
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.posX);
        int k = MathHelper.floor(entityIn.posY);
        int l = MathHelper.floor(entityIn.posZ);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int j2 = j - 16; j2 <= j + 16; ++j2) {
            double d1 = (double) j2 + 0.5D - entityIn.posX;

            for (int l2 = l - 16; l2 <= l + 16; ++l2) {
                double d2 = (double) l2 + 0.5D - entityIn.posZ;

                label276:
                for (int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (this.world.isAirBlock(mutableBlockPos.setPos(j2, j3, l2))) {
                        while (j3 > 0 && this.world.isAirBlock(mutableBlockPos.setPos(j2, j3 - 1, l2))) {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int l4 = -1; l4 < 4; ++l4) {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        mutableBlockPos.setPos(i5, j5, k5);
                                        if (l4 < 0 && !this.world.getBlockState(mutableBlockPos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(mutableBlockPos)) {
                                            continue label276;
                                        }
                                    }
                                }
                            }

                            double d5 = (double) j3 + 0.5D - entityIn.posY;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;
                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int l5 = j - 16; l5 <= j + 16; ++l5) {
                double d3 = (double) l5 + 0.5D - entityIn.posX;

                for (int j6 = l - 16; j6 <= l + 16; ++j6) {
                    double d4 = (double) j6 + 0.5D - entityIn.posZ;

                    label214:
                    for (int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (this.world.isAirBlock(mutableBlockPos.setPos(l5, i7, j6))) {
                            while (i7 > 0 && this.world.isAirBlock(mutableBlockPos.setPos(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for (int l7 = i2; l7 < i2 + 2; ++l7) {
                                int l8 = l7 % 2;
                                int k9 = 1 - l8;

                                for (int i10 = 0; i10 < 4; ++i10) {
                                    for (int k10 = -1; k10 < 4; ++k10) {
                                        int i11 = l5 + (i10 - 1) * l8;
                                        int j11 = i7 + k10;
                                        int k11 = j6 + (i10 - 1) * k9;
                                        mutableBlockPos.setPos(i11, j11, k11);
                                        if (k10 < 0 && !this.world.getBlockState(mutableBlockPos).getMaterial().isSolid() || k10 >= 0 && !this.world.isAirBlock(mutableBlockPos)) {
                                            continue label214;
                                        }
                                    }
                                }

                                double d6 = (double) i7 + 0.5D - entityIn.posY;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = l7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;
        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int i8 = 1; i8 < 3; ++i8) {
                    for (int i9 = -1; i9 < 3; ++i9) {
                        int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
                        int j10 = k2 + i9;
                        int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
                        boolean flag = i9 < 0;
                        mutableBlockPos.setPos(l9, j10, l10);
                        this.world.setBlockState(mutableBlockPos, flag ? FRAME.getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for (int k7 = -1; k7 < 3; ++k7) {
            for (int j8 = -1; j8 < 4; ++j8) {
                if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
                    mutableBlockPos.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
                    this.world.setBlockState(mutableBlockPos, FRAME.getDefaultState(), 3);
                }
            }
        }

        BlockState blockstate = PORTAL.getDefaultState();

        Direction.Axis axis = l6 == 0
                ? Direction.Axis.Z
                : Direction.Axis.X;

        if (blockstate.has(BlockStateProperties.HORIZONTAL_AXIS)) {
            blockstate = blockstate.with(BlockStateProperties.HORIZONTAL_AXIS, axis);
        }

        if (blockstate.has(BlockStateProperties.AXIS)) {
            blockstate = blockstate.with(BlockStateProperties.AXIS, axis);
        }

        for (int k8 = 0; k8 < 2; ++k8) {
            for (int j9 = 0; j9 < 3; ++j9) {
                mutableBlockPos.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
                this.world.setBlockState(mutableBlockPos, blockstate, 18);
            }
        }

        return true;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    @Override
    public void tick(long worldTime) {
        if (worldTime % 100L == 0L) {
            this.func_222270_b(worldTime);
            this.func_222269_c(worldTime);
        }

    }

    protected BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos pos) {
        Direction.Axis direction$axis = Direction.Axis.Z;
        DivinePortalSize portalSize = new DivinePortalSize(worldIn, pos, Direction.Axis.X, PORTAL, FRAME);
        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
        if (!portalSize.isValid()) {
            direction$axis = Direction.Axis.X;
            portalSize = new DivinePortalSize(worldIn, pos, Direction.Axis.Z, PORTAL, FRAME);
        }

        if (!portalSize.isValid()) {
            return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction direction = portalSize.rightDir.rotateYCCW();
            BlockPos blockpos = portalSize.bottomLeft.up(portalSize.getHeight() - 1);

            for (Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper patternHelper = new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);

                for (int i = 0; i < portalSize.getWidth(); ++i) {
                    for (int j = 0; j < portalSize.getHeight(); ++j) {
                        CachedBlockInfo cachedblockinfo = patternHelper.translateOffset(i, j, 1);
                        if (!cachedblockinfo.getBlockState().isAir()) {
                            ++aint[direction$axisdirection.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

            for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
                if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
                    direction$axisdirection1 = direction$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);
        }
    }

    private void func_222270_b(long p_222270_1_) {
        LongIterator longiterator = this.field_222275_f.values().iterator();

        while (longiterator.hasNext()) {
            long i = longiterator.nextLong();
            if (i <= p_222270_1_) {
                longiterator.remove();
            }
        }

    }

    private void func_222269_c(long p_222269_1_) {
        long i = p_222269_1_ - 300L;
        Iterator<Map.Entry<ColumnPos, PortalPosition>> iterator = this.destinationCoordinateCache.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<ColumnPos, PortalPosition> entry = iterator.next();
            PortalPosition teleporter$portalposition = entry.getValue();
            if (teleporter$portalposition.lastUpdateTime < i) {
                ColumnPos columnpos = entry.getKey();
                Logger logger = LOGGER;
                Supplier[] asupplier = new Supplier[2];
                Dimension dimension = this.world.getDimension();
                asupplier[0] = dimension::getType;
                asupplier[1] = () -> {
                    return columnpos;
                };
                logger.debug("Removing divine portal ticket for {}:{}", asupplier);
                this.world.getChunkProvider().func_217222_b(TicketType.PORTAL, new ChunkPos(teleporter$portalposition.field_222267_a), 3, columnpos);
                iterator.remove();
            }
        }

    }

    public void placePlayer(Entity traveler) {
        this.func_222268_a(traveler, traveler.getRotationYawHead());
    }

    public static class PortalPosition {
        public final BlockPos field_222267_a;
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdateTime) {
            this.field_222267_a = pos;
            this.lastUpdateTime = lastUpdateTime;
        }
    }

    public static class DivinePortalSize {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private final Block PORTAL;
        private final Block FRAME;

        private final Block ACTIVATION_BLOCK = null;

        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public DivinePortalSize(IWorld world, BlockPos pos, Direction.Axis axis, Block portal, Block frame) {
            this.world = world;
            this.axis = axis;
            PORTAL = portal;
            FRAME = frame;

            if (axis == Direction.Axis.X) {
                this.leftDir = Direction.EAST;
                this.rightDir = Direction.WEST;
            } else {
                this.leftDir = Direction.NORTH;
                this.rightDir = Direction.SOUTH;
            }

            for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.func_196900_a(world.getBlockState(pos.down())); pos = pos.down()) {
            }

            int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
            if (i >= 0) {
                this.bottomLeft = pos.offset(this.leftDir, i);
                this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
                if (this.width < 2 || this.width > 21) {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }

            if (this.bottomLeft != null) {
                this.height = this.calculatePortalHeight();
            }

        }

        protected int getDistanceUntilEdge(BlockPos p_180120_1_, Direction p_180120_2_) {
            int i;
            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
                if (!this.func_196900_a(this.world.getBlockState(blockpos)) || this.world.getBlockState(blockpos.down()).getBlock() != FRAME) {
                    break;
                }
            }

            Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == FRAME ? i : 0;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        protected int calculatePortalHeight() {
            label56:
            for (this.height = 0; this.height < 21; ++this.height) {
                for (int i = 0; i < this.width; ++i) {
                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
                    BlockState blockstate = this.world.getBlockState(blockpos);
                    if (!this.func_196900_a(blockstate)) {
                        break label56;
                    }

                    Block block = blockstate.getBlock();
                    if (block == PORTAL) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
                        if (block != FRAME) {
                            break label56;
                        }
                    } else if (i == this.width - 1) {
                        block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
                        if (block != FRAME) {
                            break label56;
                        }
                    }
                }
            }

            for (int j = 0; j < this.width; ++j) {
                if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != FRAME) {
                    this.height = 0;
                    break;
                }
            }

            if (this.height <= 21 && this.height >= 3) {
                return this.height;
            } else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        protected boolean func_196900_a(BlockState state) {
            Block block = state.getBlock();
            return state.isAir() || block == ACTIVATION_BLOCK || block == PORTAL;
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

                for (int j = 0; j < this.height; ++j) {
                    BlockState blockstate = PORTAL.getDefaultState();

                    if (blockstate.has(BlockStateProperties.HORIZONTAL_AXIS)) {
                        blockstate = blockstate.with(BlockStateProperties.HORIZONTAL_AXIS, axis);
                    }

                    if (blockstate.has(BlockStateProperties.AXIS)) {
                        blockstate = blockstate.with(BlockStateProperties.AXIS, axis);
                    }

                    this.world.setBlockState(blockpos.up(j), blockstate, 18);
                }
            }

        }

        private boolean func_196899_f() {
            return this.portalBlockCount >= this.width * this.height;
        }

        public boolean func_208508_f() {
            return this.isValid() && this.func_196899_f();
        }
    }
}

