
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class EightCoreCPUItem extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:eight_core_cpu")
	public static final Item block = null;

	public EightCoreCPUItem(IndustrialEconomyModElements instance) {
		super(instance, 627);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).rarity(Rarity.RARE));
			setRegistryName("eight_core_cpu");
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
