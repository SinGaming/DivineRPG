package naturix.divinerpg.bases.items.serenade;

import java.util.List;

import naturix.divinerpg.DivineRPG;
import naturix.divinerpg.bases.items.ItemBase;
import naturix.divinerpg.entities.projectile.serenade.EntityDeath;
import naturix.divinerpg.entities.projectile.serenade.EntitySerenadeOfIce;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SerenadeDeath extends ItemBase {

	public SerenadeDeath(String name) {
		super(name);
		setCreativeTab(DivineRPG.CombatTab);
		setMaxDamage(500);
		setMaxStackSize(1);
		showDurabilityBar(getDefaultInstance());
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand) {

		ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
		if(!world.isRemote){
			//world.playSoundAtEntity(entity, Sounds.serenade.getPrefixedName(), 1, 1);
			world.spawnEntity(new EntityDeath(world, entity));

			entity.getHeldItem(hand).damageItem(1, entity);
}
		return ar;
}}