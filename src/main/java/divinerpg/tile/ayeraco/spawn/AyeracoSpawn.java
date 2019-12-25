package divinerpg.tile.ayeraco.spawn;

import divinerpg.blocks.twilight.AyeracoBeamBlock;
import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.TileEntityRegistry;
import divinerpg.tile.base.DivineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class AyeracoSpawn extends DivineTileEntity implements ITickableTileEntity {
    private final Map<BossInfo.Color, Integer> colorToTick = new LinkedHashMap<>();
    private BossInfo.Color currentColor;
    private int ticks;

    public AyeracoSpawn() {
        this(600);
    }

    public AyeracoSpawn(int spawnTicks) {
        super(TileEntityRegistry.ayeraco_spawner);
        // max summinig time
        ticks = spawnTicks;

        int maxSummonogTime = ticks;
        int byOne = maxSummonogTime / AyeracoManager.beamLocations.size();

        for (BossInfo.Color color : AyeracoManager.beamLocations.keySet()) {
            colorToTick.put(color, maxSummonogTime);

            maxSummonogTime -= byOne;
        }

        currentColor = colorToTick.keySet().stream().findFirst().get();
    }

    @Override
    public void tick() {
        Map.Entry<BossInfo.Color, Integer> entry = colorToTick.entrySet().stream().filter(x -> x.getValue() >= ticks).findFirst().orElse(null);
        if (entry != null) {
            BlockPos location = AyeracoManager.getBeamLocation(this.world, getPos(), entry.getKey());

            setBlock(location, entry.getKey());
            colorToTick.remove(entry.getKey());

            log("message.ayeraco.spawn", TextFormatting.getValueByName(entry.getKey().getName()));
            currentColor = entry.getKey();
        }

        if (ticks <= 0) {
            if (!world.isRemote()) {
                AyeracoManager.summonGang(world, getPos());
            }

            world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
        }

        ticks--;
    }

    private void setBlock(BlockPos pos, BossInfo.Color color) {
        Block block = BlockRegistry.find(AyeracoBeamBlock.getName(color));

        if (block != null)
            world.setBlockState(pos, block.getDefaultState());
    }

    private void log(String text, TextFormatting color) {
        // todo logger
    }

    String genSummoningColorName() {
        return currentColor != null
                ? currentColor.getName()
                : "";
    }
}
