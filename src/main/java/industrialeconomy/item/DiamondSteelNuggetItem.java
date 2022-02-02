
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class DiamondSteelNuggetItem extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:diamond_steel_nugget")
	public static final Item block = null;

	public DiamondSteelNuggetItem(IndustrialEconomyModElements instance) {
		super(instance, 652);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("diamond_steel_nugget");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

	}

}
