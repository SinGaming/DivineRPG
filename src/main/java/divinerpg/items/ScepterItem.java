package divinerpg.items;

import divinerpg.utils.RayTraceHelper;
import divinerpg.utils.TooltipUtil;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IBlockHit;
import divinerpg.utils.properties.item.IHitEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ScepterItem extends TieredItem {

    private final IHitEntity onHit;
    private final IBlockHit onBlockHit;
    private final float range;
    private final IParticleData particle;

    public ScepterItem(IItemTier tierIn, ExtendedItemProperties builder, float range, IParticleData particle) {
        super(tierIn, builder);

        onHit = builder.onHit;
        onBlockHit = builder.onBlockHit;
        this.range = range;
        this.particle = particle;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);

        if (result.getType() != ActionResultType.FAIL) {
            performBlockTrace(worldIn, playerIn, result.getResult(), range);
            performEntityRayTrace(playerIn, result.getResult(), range);

            result = ActionResult.newResult(ActionResultType.SUCCESS, result.getResult());
        }

        return result;
    }

    protected void performBlockTrace(World world, LivingEntity entity, ItemStack weapon, float range) {
        if (weapon.isEmpty() || onBlockHit == null)
            return;

        Vec3d start = entity.getPositionVec();
        Vec3d end = start.add(entity.getLookVec().scale(range));

        RayTraceContext context = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER,
                RayTraceContext.FluidMode.SOURCE_ONLY, entity);

        BlockRayTraceResult blockTrace = world.rayTraceBlocks(context);

        if (blockTrace != null
                && blockTrace.getType() == RayTraceResult.Type.BLOCK) {
            this.onBlockHit.onHit(world, entity, weapon, blockTrace);

            spawnParticle(world, start, end);

            damageStack(weapon, entity);
        }
    }

    protected void performEntityRayTrace(PlayerEntity entity, ItemStack weapon, float range) {
        if (onHit == null || weapon.isEmpty())
            return;

        Entity victim = RayTraceHelper.rayTrace(entity, range);
        if (victim != null) {
            onHit.onLeftClickEntity(weapon, entity, victim);

            spawnParticle(entity.getEntityWorld(), entity.getPositionVec(), victim.getPositionVector());

            damageStack(weapon, entity);
        }
    }

    protected void damageStack(ItemStack weapon, LivingEntity e) {
        weapon.damageItem(1, e, player -> player.sendBreakAnimation(Hand.MAIN_HAND));
    }

    protected void spawnParticle(World world, Vec3d start, Vec3d end) {
        if (particle == null)
            return;

        Vec3d step = end.subtract(start).normalize();

        while (start.distanceTo(end) > 1) {
            world.addParticle(particle, start.x, start.y, start.z, step.x, step.y, step.z);
            start = start.add(step);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (worldIn == null)
            return;

        TooltipUtil.uses(stack);
    }
}
