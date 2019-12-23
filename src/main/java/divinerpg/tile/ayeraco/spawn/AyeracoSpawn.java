package divinerpg.tile.ayeraco.spawn;

import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
import divinerpg.registry.TileEntityRegistry;
import divinerpg.tile.base.DivineTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;

import java.util.HashMap;
import java.util.Map;

public class AyeracoSpawn extends DivineTileEntity implements ITickableTileEntity {
    private final Map<BossInfo.Color, Integer> colorToTick = new HashMap<>();
    private int ticks;

    public AyeracoSpawn(int spawnTicks) {
        super(TileEntityRegistry.ayeraco_spawn);
        // max summinig time
        ticks = spawnTicks;

        int maxSummonogTime = ticks;
        int byOne = maxSummonogTime / AyeracoManager.beamLocations.size();

        for (BossInfo.Color color : AyeracoManager.beamLocations.keySet()) {
            colorToTick.put(color, maxSummonogTime);

            maxSummonogTime -= byOne;
        }
    }

    @Override
    public void tick() {
        Map.Entry<BossInfo.Color, Integer> entry = colorToTick.entrySet().stream().filter(x -> x.getValue() >= ticks).findFirst().orElse(null);
        if (entry != null) {
            BlockPos location = AyeracoManager.getBeamLocation(this.world, getPos(), entry.getKey());

            setBlock(location, entry.getKey());
            colorToTick.remove(entry.getKey());

            log("message.ayeraco.spawn", TextFormatting.getValueByName(entry.getKey().getName()));
        }

        if (ticks <= 0) {
            if (!world.isRemote()) {
                AyeracoManager.summonGang(world, getPos()).forEach(x -> world.addEntity(x));
            }

            world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
        }

        ticks--;
    }

    private void setBlock(BlockPos pos, BossInfo.Color color) {
        // todo deam block
    }

    private void log(String text, TextFormatting color) {
        // todo logger
    }
}
