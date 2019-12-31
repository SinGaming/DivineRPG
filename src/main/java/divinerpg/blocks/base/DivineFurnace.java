package divinerpg.blocks.base;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class DivineFurnace extends AbstractFurnaceBlock {
    private final Supplier<TileEntity> createTile;

    public DivineFurnace(Properties properties, Supplier<TileEntity> createTile) {
        super(properties);
        this.createTile = createTile;
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof INamedContainerProvider && player instanceof ServerPlayerEntity) {
            NetworkHooks.openGui(((ServerPlayerEntity) player), ((INamedContainerProvider) tileentity));
            player.addStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return createTile.get();
    }
}
