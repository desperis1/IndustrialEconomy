
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class Money50000Item extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:money_50000")
	public static final Item block = null;

	public Money50000Item(IndustrialEconomyModElements instance) {
		super(instance, 519);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.RARE));
			setRegistryName("money_50000");
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
