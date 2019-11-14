package divinerpg.items;

import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IBlockHit;
import divinerpg.utils.properties.item.IHitEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ScepterItem extends TieredItem {

    private final IHitEntity onHit;
    private final IBlockHit onBlockHit;
    private final float range;

    public ScepterItem(IItemTier tierIn, ExtendedItemProperties builder, float range) {
        super(tierIn, builder);

        onHit = builder.onHit;
        onBlockHit = builder.onBlockHit;
        this.range = range;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);

        if (result.getType() != ActionResultType.FAIL) {
            if (!worldIn.isRemote()) {

                performBlockTrace(worldIn, playerIn, result.getResult(), range);
                performEntityRayTrace(worldIn, playerIn, result.getResult(), range);
            }
        }

        return result;
    }

    protected void performBlockTrace(World world, LivingEntity entity, ItemStack weapon, float range) {
        if (weapon.isEmpty() || onBlockHit == null)
            return;

        RayTraceContext context = new RayTraceContext(entity.getPositionVec(), entity.getLookVec().scale(range), RayTraceContext.BlockMode.COLLIDER,
                RayTraceContext.FluidMode.SOURCE_ONLY, entity);

        BlockRayTraceResult blockTrace = world.rayTraceBlocks(context);

        if (blockTrace != null
                && blockTrace.getType() == RayTraceResult.Type.BLOCK) {
            this.onBlockHit.onHit(world, entity, weapon, blockTrace);

            damageStack(weapon, entity);
        }
    }

    protected void performEntityRayTrace(World world, PlayerEntity entity, ItemStack weapon, float range) {
        if (onHit == null || weapon.isEmpty())
            return;

        EntityRayTraceResult traceResult = ProjectileHelper.func_221269_a(world, entity, entity.getPositionVector(),
                entity.getLookVec().scale(range), entity.getBoundingBox().grow(1),
                target -> target.canBeCollidedWith() && target != entity, range);

        if (traceResult != null
                && traceResult.getType() == RayTraceResult.Type.ENTITY) {
            onHit.onLeftClickEntity(weapon, entity, traceResult.getEntity());

            damageStack(weapon, entity);
        }
    }

    protected void damageStack(ItemStack weapon, LivingEntity e) {
        weapon.damageItem(1, e, player -> player.sendBreakAnimation(Hand.MAIN_HAND));
    }
}
