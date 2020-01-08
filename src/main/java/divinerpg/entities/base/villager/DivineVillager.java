package divinerpg.entities.base.villager;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.util.Pair;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ReflectionHelper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.ChangeJobTask;
import net.minecraft.entity.ai.brain.task.ShowWaresTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.VillagerTasks;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// todo natural gen don't saving own profession
public class DivineVillager extends VillagerEntity {
    private IVillagerType type;
    private VillagerProfession profession;

    // STOLEN FROM VILLAGER
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES;
    private static final ImmutableList<SensorType<? extends Sensor<? super VillagerEntity>>> SENSOR_TYPES;

    // exluded tasks classes
    private static final List<Class> exludedTasks = Arrays.asList(ChangeJobTask.class, ShowWaresTask.class);

    static {
        MEMORY_TYPES = ReflectionHelper.getFieldValue(null, VillagerEntity.class, "MEMORY_TYPES", ImmutableList.class);
        SENSOR_TYPES = ReflectionHelper.getFieldValue(null, VillagerEntity.class, "SENSOR_TYPES", ImmutableList.class);
    }


    private DivineVillager(World p_i50182_2_) {
        super(EntityType.VILLAGER, p_i50182_2_);
    }

    public DivineVillager(EntityType<? extends VillagerEntity> entityType, World world, IVillagerType type, VillagerProfession profession) {
        super(entityType, world, type);
        this.type = type;
        this.profession = profession;

        this.setVillagerData(getVillagerData().withProfession(profession).withType(type));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void populateTradeData() {
        VillagerData data = getVillagerData();
        Int2ObjectMap<VillagerTrades.ITrade[]> allTrades = VillagerTrades.field_221239_a.get(data.getProfession());
        if (allTrades != null && !allTrades.isEmpty()) {
            List<VillagerTrades.ITrade> trades = Arrays.asList(allTrades.get(data.getLevel()));
            MerchantOffers offers = this.getOffers();
            trades.stream().map(x -> x.getOffer(this, rand)).forEach(offers::add);
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000);
    }

    /**
     * Returns GUI texture location
     *
     * @return
     */
    protected ResourceLocation getGUI() {
        // using same name for GUI image
        return CachedTexture.GUI.getTexture(getType().getRegistryName().getPath());
    }

    @Override
    public void func_213707_a(PlayerEntity player, ITextComponent name, int var1) {
        if (player instanceof ServerPlayerEntity) {
            ResourceLocation gui = getGUI();

            NetworkHooks.openGui(((ServerPlayerEntity) player),
                    new SimpleNamedContainerProvider((windowId, inventory, playerEntity)
                            -> new DivineMerchantContainer(windowId, inventory, this, gui), name),
                    packetBuffer -> packetBuffer.writeString(gui.toString()));

            MerchantOffers merchantoffers = this.getOffers();
            if (!merchantoffers.isEmpty()) {
                player.func_213818_a(((ServerPlayerEntity) player).currentWindowId, merchantoffers, var1, this.getXp(), this.func_213705_dZ(), this.func_223340_ej());
            }
        }
    }

    /**
     * Never cahgne type/profession
     *
     * @param data
     */
    @Override
    public void setVillagerData(VillagerData data) {

        if (type != null && data.getType() != type) {
            data = data.withType(type);
        }

        if (profession != null && profession != data.getProfession()) {
            data = data.withProfession(profession);
        }

        super.setVillagerData(data);
    }

    @Override
    protected Brain<?> createBrain(Dynamic<?> p_213364_1_) {
        Brain<VillagerEntity> brain = new Brain<>(MEMORY_TYPES, SENSOR_TYPES, p_213364_1_);
        this.initBrain(brain);
        return brain;
    }

    @Override
    public void resetBrain(ServerWorld world) {
        Brain<VillagerEntity> brain = this.getBrain();
        brain.stopAllTasks(world, this);
        this.brain = brain.copy();
        this.initBrain(this.getBrain());
    }

    private void initBrain(Brain<VillagerEntity> brain) {
        VillagerProfession villagerprofession = this.getVillagerData().getProfession();
        float f = (float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();

        if (this.isChild()) {
            brain.setSchedule(Schedule.VILLAGER_BABY);
            brain.registerActivity(Activity.PLAY, restrict(VillagerTasks.play(f)));
        } else {
            brain.setSchedule(Schedule.VILLAGER_DEFAULT);
            brain.registerActivity(Activity.WORK, restrict(VillagerTasks.work(villagerprofession, f)), ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryModuleStatus.VALUE_PRESENT)));
        }

        brain.registerActivity(Activity.CORE, restrict(VillagerTasks.core(villagerprofession, f)));
        brain.registerActivity(Activity.MEET, restrict(VillagerTasks.meet(villagerprofession, f)), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT)));
        brain.registerActivity(Activity.REST, restrict(VillagerTasks.rest(villagerprofession, f)));
        brain.registerActivity(Activity.IDLE, restrict(VillagerTasks.idle(villagerprofession, f)));
        brain.registerActivity(Activity.PANIC, restrict(VillagerTasks.panic(villagerprofession, f)));
        brain.registerActivity(Activity.PRE_RAID, restrict(VillagerTasks.preRaid(villagerprofession, f)));
        brain.registerActivity(Activity.RAID, restrict(VillagerTasks.raid(villagerprofession, f)));
        brain.registerActivity(Activity.HIDE, restrict(VillagerTasks.hide(villagerprofession, f)));
        brain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
        brain.setFallbackActivity(Activity.IDLE);
        brain.switchTo(Activity.IDLE);
        brain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
    }

    /**
     * Removes restricted tasks
     *
     * @param base - original collection
     * @return
     */
    private ImmutableList<Pair<Integer, ? extends Task<? super VillagerEntity>>> restrict(ImmutableList<Pair<Integer, ? extends Task<? super VillagerEntity>>> base) {

        List<Pair<Integer, ? extends Task<? super VillagerEntity>>> result = new ArrayList<>();

        for (Pair<Integer, ? extends Task<? super VillagerEntity>> pair : base) {
            // except selected tasks
            if (!exludedTasks.contains(pair.getSecond().getClass())) {
                result.add(pair);
            }
        }

        return ImmutableList.copyOf(result);
    }
}
