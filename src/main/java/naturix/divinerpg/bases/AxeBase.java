package naturix.divinerpg.bases;

import naturix.divinerpg.Divine;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class AxeBase extends ItemAxe {

	private String name;

	public AxeBase(ToolMaterial material, String name, float damage, float speed) {
		super(material, damage, speed);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Divine.ItemsTab);
		this.name = name;
	}
	
	public void registerItemModel(Item item) {
		Divine.proxy.registerItemRenderer(this, 0, name);
	}

}