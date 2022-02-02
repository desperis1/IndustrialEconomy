
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class CrystalchunkItem extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:crystalchunk")
	public static final Item block = null;

	public CrystalchunkItem(IndustrialEconomyModElements instance) {
		super(instance, 502);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("crystalchunk");
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

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

	}

}
