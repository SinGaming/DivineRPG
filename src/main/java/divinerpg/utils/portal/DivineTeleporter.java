package divinerpg.utils.portal;

import divinerpg.blocks.twilight.DivinePortalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static divinerpg.utils.portal.PortalHelper.createNetherLikePattern;

/**
 * Full Legacy
 * Supporting custom frame/portal creation
 */
public class DivineTeleporter extends Teleporter {
    public final ResourceLocation id;
    protected final List<TeleportPlacement> destinationCoordinateCache = new ArrayList<>(4096);
    private final Block frame;
    private final DivinePortalBlock portal;

    private DivineTeleporter(ResourceLocation id, ServerWorld worldIn, Block frame, DivinePortalBlock portal) {
        super(worldIn);
        this.id = id;
        this.frame = frame;
        this.portal = portal;
    }

    public static DivineTeleporter register(ResourceLocation portalName, ServerWorld world, Block frame, DivinePortalBlock portal) {
        Optional<Teleporter> teleporter = world.customTeleporters.stream()
                .filter(x -> x instanceof DivineTeleporter && ((DivineTeleporter) x).id.equals(portalName)).findFirst();

        if (!teleporter.isPresent()) {
            teleporter = Optional.of(new DivineTeleporter(portalName, world, frame, portal));
            world.customTeleporters.add(teleporter.get());
        }

        return (DivineTeleporter) teleporter.get();
    }

    public void placePlayer(Entity entity) {
        func_222268_a(entity, entity.rotationYaw);
    }

    @Override
    public boolean func_222268_a(Entity entity, float yaw) {
        BlockPos pos = new BlockPos(entity);
        Optional<TeleportPlacement> first = destinationCoordinateCache.stream().filter(x -> x.isNear(pos, 128)).findFirst();

        if (!first.isPresent()) {
            BlockPos portalPos = findPlaceToSpawnPortal(world, entity, 128);

            // Force load
            this.world.getChunkProvider().func_217228_a(TicketType.PORTAL, new ChunkPos(portalPos), 3, new ColumnPos(portalPos));

            PortalHelper.placeNetherLikePortal(world, portalPos, entity.getHorizontalFacing(), Direction.UP, frame, portal);
            BlockPattern pattern = createNetherLikePattern(frame, portal);
            BlockPattern.PatternHelper match = pattern.match(world, portalPos);
            if (match == null) {
                CrashReport.makeCrashReport(new RuntimeException(), String.format("cannot detect portal from %s", pos.toString()));
            }

            first = Optional.of(new TeleportPlacement(pattern, match));
            destinationCoordinateCache.add(first.get());
        }

        TeleportPlacement placement = first.get();
        placement.placeInside(world, entity);

        return true;
    }

    @Override
    public void tick(long worldTime) {
        // super.tick(worldTime);

        // checking once a minute
        if (worldTime % (20 * 60) != 0) {
            return;
        }

        Iterator<TeleportPlacement> iterator = destinationCoordinateCache.iterator();

        while (iterator.hasNext()) {
            TeleportPlacement placement = iterator.next();
            if (!placement.isValidStill(this.world)) {
                destinationCoordinateCache.remove(placement);
            }
        }
    }

    public BlockPos findPlaceToSpawnPortal(World world, Entity entity, int radius) {

        // TODO implement search algoruthm
//        int height = world.getActualHeight();
//
//        BlockPos pos = new BlockPos(entity.posX, height, entity.posZ);
//
//        Vec3d left = entity.getPositionVec().add(-radius, -radius, -radius);
//        Vec3d right = entity.getPositionVec().add(radius, radius, radius);

//        BlockRayTraceResult traceResult = world.getChunk(entity.getPosition()).rayTraceBlocks(
//                new RayTraceContext(left, right, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, entity));

//        for (int x = 0; x < radius; x++) {
//            for (int z = 0; z < radius; z++) {
//                for (int y = 0; y < radius; y++) {
//                    BlockPos solid = checkBlock(world, pos, x, y, z);
//                    if (solid == null) {
//                        solid = checkBlock(world, pos, -x, y, z);
//                        if (solid == null) {
//                            solid = checkBlock(world, pos, x, y, -z);
//                            if (solid == null) {
//                                solid = checkBlock(world, pos, -x, y, -z);
//                            }
//                        }
//                    }
//
//                    if (solid != null)
//                        return solid;
//                }
//            }
//        }

        return entity.getPosition();
    }

    private BlockPos checkBlock(World world, BlockPos entityPos, int x, int y, int z) {
        BlockPos pos = entityPos.add(x, -y, z);
        if (world.isAirBlock(pos))
            return null;

        return world.getBlockState(pos).getMaterial().isSolid()
                ? pos
                : null;
    }
}

//    private static final Logger LOGGER = LogManager.getLogger();
//    private final DivinePortalBlock PORTAL;
//    private final Block FRAME;
//    protected final ServerWorld world;
//    protected final Random random;
//
//    private final Object2LongMap<ColumnPos> columns = new Object2LongOpenHashMap<>();
//
//    public DivineTeleporter(ServerWorld worldIn, DivinePortalBlock portal, Block frame) {
//        super(worldIn);
//        this.world = worldIn;
//        this.random = new Random(worldIn.getSeed());
//
//        PORTAL = portal;
//        FRAME = frame;
//    }
//
//    @Override
//    public boolean func_222268_a(Entity entity, float yaw) {
//        Vec3d vec3d = entity.getLastPortalVec();
//        Direction direction = entity.getTeleportDirection();
//        BlockPattern.PortalInfo portalInfo = this.func_222272_a(new BlockPos(entity), entity.getMotion(), direction, vec3d.x, vec3d.y, entity instanceof PlayerEntity);
//        if (portalInfo == null) {
//            return false;
//        } else {
//            Vec3d vec3d1 = portalInfo.field_222505_a;
//            Vec3d vec3d2 = portalInfo.field_222506_b;
//            entity.setMotion(vec3d2);
//            entity.rotationYaw = yaw + (float)portalInfo.field_222507_c;
//            if (entity instanceof ServerPlayerEntity) {
//                ((ServerPlayerEntity)entity).connection.setPlayerLocation(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
//                ((ServerPlayerEntity)entity).connection.captureCurrentPosition();
//            } else {
//                entity.setLocationAndAngles(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
//            }
//
//            return true;
//        }
//    }
//
//    @Override
//    @Nullable
//    public BlockPattern.PortalInfo func_222272_a(BlockPos pos, Vec3d vec, Direction dir, double x, double y, boolean isPlayer) {
//        int i = 128;
//        boolean flag = true;
//        BlockPos blockpos = null;
//        ColumnPos columnpos = new ColumnPos(pos);
//        if (!isPlayer && this.columns.containsKey(columnpos)) {
//            return null;
//        } else {
//            DivineTeleporter.PortalPosition portalPosition = this.destinationCoordinateCache.get(columnpos);
//            if (portalPosition != null) {
//                blockpos = portalPosition.field_222267_a;
//                portalPosition.lastUpdateTime = this.world.getGameTime();
//                flag = false;
//            } else {
//                double d0 = Double.MAX_VALUE;
//
//                for(int j = -128; j <= 128; ++j) {
//                    BlockPos blockpos2;
//                    for(int k = -128; k <= 128; ++k) {
//                        for(BlockPos blockpos1 = pos.add(j, this.world.getActualHeight() - 1 - pos.getY(), k); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
//                            blockpos2 = blockpos1.down();
//                            if (this.world.getBlockState(blockpos1).getBlock() == PORTAL) {
//                                for(blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == PORTAL; blockpos2 = blockpos2.down()) {
//                                    blockpos1 = blockpos2;
//                                }
//
//                                double d1 = blockpos1.distanceSq(pos);
//                                if (d0 < 0.0D || d1 < d0) {
//                                    d0 = d1;
//                                    blockpos = blockpos1;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (blockpos == null) {
//                long l = this.world.getGameTime() + 300L;
//                this.columns.put(columnpos, l);
//                return null;
//            } else {
//                if (flag) {
//                    this.destinationCoordinateCache.put(columnpos, new DivineTeleporter.PortalPosition(blockpos, this.world.getGameTime()));
//                    Logger logger = LOGGER;
//                    Supplier[] asupplier = new Supplier[2];
//                    Dimension dimension = this.world.getDimension();
//                    asupplier[0] = dimension::getType;
//                    asupplier[1] = () -> {
//                        return columnpos;
//                    };
//                    logger.debug("Adding divine portal ticket for {}:{}", asupplier);
//                    this.world.getChunkProvider().func_217228_a(TicketType.PORTAL, new ChunkPos(blockpos), 3, columnpos);
//                }
//
//                BlockPattern.PatternHelper patternHelper = PortalHelper.createHelperForTeleporter(world, pos, FRAME, PORTAL);
//                return patternHelper.func_222504_a(dir, blockpos, y, vec, x);
//            }
//        }
//    }
//
//    public boolean makePortal(Entity entityIn) {
//        int i = 16;
//        double d0 = -1.0D;
//        int j = MathHelper.floor(entityIn.posX);
//        int k = MathHelper.floor(entityIn.posY);
//        int l = MathHelper.floor(entityIn.posZ);
//        int i1 = j;
//        int j1 = k;
//        int k1 = l;
//        int l1 = 0;
//        int i2 = this.random.nextInt(4);
//        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
//
//        for(int j2 = j - 16; j2 <= j + 16; ++j2) {
//            double d1 = (double)j2 + 0.5D - entityIn.posX;
//
//            for(int l2 = l - 16; l2 <= l + 16; ++l2) {
//                double d2 = (double)l2 + 0.5D - entityIn.posZ;
//
//                label276:
//                for(int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
//                    if (this.world.isAirBlock(blockPos.setPos(j2, j3, l2))) {
//                        while(j3 > 0 && this.world.isAirBlock(blockPos.setPos(j2, j3 - 1, l2))) {
//                            --j3;
//                        }
//
//                        for(int k3 = i2; k3 < i2 + 4; ++k3) {
//                            int l3 = k3 % 2;
//                            int i4 = 1 - l3;
//                            if (k3 % 4 >= 2) {
//                                l3 = -l3;
//                                i4 = -i4;
//                            }
//
//                            for(int j4 = 0; j4 < 3; ++j4) {
//                                for(int k4 = 0; k4 < 4; ++k4) {
//                                    for(int l4 = -1; l4 < 4; ++l4) {
//                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
//                                        int j5 = j3 + l4;
//                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
//                                        blockPos.setPos(i5, j5, k5);
//                                        if (l4 < 0 && !this.world.getBlockState(blockPos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockPos)) {
//                                            continue label276;
//                                        }
//                                    }
//                                }
//                            }
//
//                            double d5 = (double)j3 + 0.5D - entityIn.posY;
//                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;
//                            if (d0 < 0.0D || d7 < d0) {
//                                d0 = d7;
//                                i1 = j2;
//                                j1 = j3;
//                                k1 = l2;
//                                l1 = k3 % 4;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (d0 < 0.0D) {
//            for(int l5 = j - 16; l5 <= j + 16; ++l5) {
//                double d3 = (double)l5 + 0.5D - entityIn.posX;
//
//                for(int j6 = l - 16; j6 <= l + 16; ++j6) {
//                    double d4 = (double)j6 + 0.5D - entityIn.posZ;
//
//                    label214:
//                    for(int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
//                        if (this.world.isAirBlock(blockPos.setPos(l5, i7, j6))) {
//                            while(i7 > 0 && this.world.isAirBlock(blockPos.setPos(l5, i7 - 1, j6))) {
//                                --i7;
//                            }
//
//                            for(int l7 = i2; l7 < i2 + 2; ++l7) {
//                                int l8 = l7 % 2;
//                                int k9 = 1 - l8;
//
//                                for(int i10 = 0; i10 < 4; ++i10) {
//                                    for(int k10 = -1; k10 < 4; ++k10) {
//                                        int i11 = l5 + (i10 - 1) * l8;
//                                        int j11 = i7 + k10;
//                                        int k11 = j6 + (i10 - 1) * k9;
//                                        blockPos.setPos(i11, j11, k11);
//                                        if (k10 < 0 && !this.world.getBlockState(blockPos).getMaterial().isSolid() || k10 >= 0 && !this.world.isAirBlock(blockPos)) {
//                                            continue label214;
//                                        }
//                                    }
//                                }
//
//                                double d6 = (double)i7 + 0.5D - entityIn.posY;
//                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
//                                if (d0 < 0.0D || d8 < d0) {
//                                    d0 = d8;
//                                    i1 = l5;
//                                    j1 = i7;
//                                    k1 = j6;
//                                    l1 = l7 % 2;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        int i6 = i1;
//        int k2 = j1;
//        int k6 = k1;
//        int l6 = l1 % 2;
//        int i3 = 1 - l6;
//        if (l1 % 4 >= 2) {
//            l6 = -l6;
//            i3 = -i3;
//        }
//
//        if (d0 < 0.0D) {
//            j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
//            k2 = j1;
//
//            for(int j7 = -1; j7 <= 1; ++j7) {
//                for(int i8 = 1; i8 < 3; ++i8) {
//                    for(int i9 = -1; i9 < 3; ++i9) {
//                        int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
//                        int j10 = k2 + i9;
//                        int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
//                        boolean flag = i9 < 0;
//                        blockPos.setPos(l9, j10, l10);
//                        this.world.setBlockState(blockPos, flag ? this.FRAME.getDefaultState() : Blocks.AIR.getDefaultState());
//                    }
//                }
//            }
//        }
//
//        for(int k7 = -1; k7 < 3; ++k7) {
//            for(int j8 = -1; j8 < 4; ++j8) {
//                if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
//                    blockPos.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
//                    this.world.setBlockState(blockPos, this.FRAME.getDefaultState(), 3);
//                }
//            }
//        }
//
//        BlockState blockstate = PORTAL.getDefaultState().with(BlockStateProperties.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);
//
//        for(int k8 = 0; k8 < 2; ++k8) {
//            for(int j9 = 0; j9 < 3; ++j9) {
//                blockPos.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
//                this.world.setBlockState(blockPos, blockstate, 18);
//            }
//        }
//
//        return true;
//    }
//
//    /**
//     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
//     * WorldServer.getTotalWorldTime() value.
//     */
//    public void tick(long worldTime) {
//        if (worldTime % 100L == 0L) {
//            this.func_222270_b(worldTime);
//            this.func_222269_c(worldTime);
//        }
//
//    }
//
//    private void func_222270_b(long p_222270_1_) {
//        LongIterator longiterator = this.columns.values().iterator();
//
//        while(longiterator.hasNext()) {
//            long i = longiterator.nextLong();
//            if (i <= p_222270_1_) {
//                longiterator.remove();
//            }
//        }
//
//    }
//
//    private void func_222269_c(long p_222269_1_) {
//        long i = p_222269_1_ - 300L;
//        Iterator<Map.Entry<ColumnPos, DivineTeleporter.PortalPosition>> iterator = this.destinationCoordinateCache.entrySet().iterator();
//
//        while(iterator.hasNext()) {
//            Map.Entry<ColumnPos, DivineTeleporter.PortalPosition> entry = iterator.next();
//            DivineTeleporter.PortalPosition teleporter$portalposition = entry.getValue();
//            if (teleporter$portalposition.lastUpdateTime < i) {
//                ColumnPos columnpos = entry.getKey();
//                Logger logger = LOGGER;
//                Supplier[] asupplier = new Supplier[2];
//                Dimension dimension = this.world.getDimension();
//                asupplier[0] = dimension::getType;
//                asupplier[1] = () -> {
//                    return columnpos;
//                };
//                logger.debug("Removing divine portal ticket for {}:{}", asupplier);
//                this.world.getChunkProvider().func_217222_b(TicketType.PORTAL, new ChunkPos(teleporter$portalposition.field_222267_a), 3, columnpos);
//                iterator.remove();
//            }
//        }
//
//    }
//
//    static class PortalPosition {
//        public final BlockPos field_222267_a;
//        public long lastUpdateTime;
//
//        public PortalPosition(BlockPos p_i50813_1_, long p_i50813_2_) {
//            this.field_222267_a = p_i50813_1_;
//            this.lastUpdateTime = p_i50813_2_;
//        }
//    }

