package divinerpg.items;

import divinerpg.DivineRPG;
import divinerpg.api.DivineAPI;
import divinerpg.api.arcana.IArcana;
import divinerpg.messages.ArcanaMessage;
import divinerpg.messages.TeleportMessage;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TeleportCrystal extends Item {
    private final static String dimKey = "dimension";
    private final static String posKey = "position";

    private final float arcana;
    private final boolean canChange;

    public TeleportCrystal(Properties properties, float arcana) {
        super(properties.maxDamage(10));
        this.arcana = arcana;

        canChange = arcana > 0;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);
        if (result.getType() != ActionResultType.FAIL) {
            ItemStack stack = result.getResult();

            ActionResultType type = playerIn.isSneaking()
                    ? remember(playerIn, stack)
                    : teleportEntity(playerIn, stack, handIn);

            return ActionResult.newResult(type, stack);
        }

        return result;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        super.onCreated(stack, worldIn, playerIn);

        BlockPos bedLocation = playerIn.getBedLocation(playerIn.dimension);
        if (bedLocation == null) {
            bedLocation = worldIn.getSpawnPoint();
        }

        setPos(stack, bedLocation);
        setDim(stack, playerIn.dimension);
    }

    @Nullable
    private DimensionType getDim(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains(dimKey)) {
            return DimensionType.byName(new ResourceLocation(tag.getString(dimKey)));
        }

        return null;
    }

    private void setDim(ItemStack stack, @Nonnull DimensionType dimension) {
        CompoundNBT tag = getTag(stack);
        tag.putString(dimKey, dimension.getRegistryName().toString());
    }

    @Nullable
    private BlockPos getPos(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains(posKey)) {
            return BlockPos.fromLong(tag.getLong(posKey));
        }

        return null;
    }

    private void setPos(ItemStack stack, @Nonnull BlockPos pos) {
        CompoundNBT tag = getTag(stack);
        tag.putLong(posKey, pos.toLong());
    }

    private CompoundNBT getTag(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag == null) {
            tag = new CompoundNBT();
            stack.setTag(tag);
        }

        return tag;
    }

    private ActionResultType teleportEntity(PlayerEntity player, ItemStack crystal, Hand hand) {
        DimensionType dim = getDim(crystal);
        BlockPos pos = getPos(crystal);

        if (dim != null && pos != null) {

            if (this.arcana > 0) {
                IArcana arcana = DivineAPI.getPlayerArcana(player);
                if (!arcana.tryConsume(this.arcana))
                    return ActionResultType.FAIL;

                DivineRPG.CHANNEL.sendToServer(new ArcanaMessage(arcana));
            }

            DivineRPG.CHANNEL.sendToServer(new TeleportMessage(dim, pos));

            if (!player.isCreative())
                crystal.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));

            return ActionResultType.PASS;
        }

        return ActionResultType.FAIL;
    }

    private ActionResultType remember(PlayerEntity player, ItemStack crystal) {
        DimensionType dim = getDim(crystal);
        BlockPos pos = getPos(crystal);

        if (dim == null || pos == null || canChange) {
            setDim(crystal, player.dimension);
            setPos(crystal, player.getPosition());

            return ActionResultType.PASS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        BlockPos pos = getPos(stack);
        DimensionType dim = getDim(stack);

        if (dim != null && pos != null) {
            tooltip.add(new TranslationTextComponent("tooltip.teleport_crystal.dim").appendText(": ")
                    .appendSibling(new StringTextComponent(dim.getRegistryName().toString()).applyTextStyle(TextFormatting.RED)));
            tooltip.add(new TranslationTextComponent("tooltip.teleport_crystal.pos").appendText(": ")
                    .appendSibling(new StringTextComponent(pos.getX() + ";" + pos.getY() + ";" + pos.getZ()).applyTextStyle(TextFormatting.GRAY)));
        }
    }
}
