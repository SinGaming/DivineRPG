package divinerpg.blocks.base;

import divinerpg.tile.furnace.DivineFurnaceContainer;
import divinerpg.tile.furnace.DivineFurnaceTileEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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

public class DivineFurnace extends AbstractFurnaceBlock {
    private final float speed;
    private boolean isInfinite;

    public DivineFurnace(MaterialColor color, float speed, boolean isInfinite) {
        super(Block.Properties.create(Material.ROCK, color).hardnessAndResistance(3.5F).lightValue(13));
        this.speed = speed;
        this.isInfinite = isInfinite;
    }

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof DivineFurnaceTileEntity && player instanceof ServerPlayerEntity) {
            NetworkHooks.openGui(
                    ((ServerPlayerEntity) player),
                    ((INamedContainerProvider) tileentity),
                    DivineFurnaceContainer.DivineFurnaceFactory.create(this, ((DivineFurnaceTileEntity) tileentity))
            );
            player.addStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        String name = String.format("tile.%s.name", getRegistryName().getPath());
        return new DivineFurnaceTileEntity(name, speed, isInfinite);
    }
}
