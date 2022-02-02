
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class BlackOpalIngotItem extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:black_opal_ingot")
	public static final Item block = null;

	public BlackOpalIngotItem(IndustrialEconomyModElements instance) {
		super(instance, 589);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).isImmuneToFire().rarity(Rarity.RARE));
			setRegistryName("black_opal_ingot");
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
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Coal: 192 456"));
			list.add(new StringTextComponent("Iron: 279 936"));
			list.add(new StringTextComponent("Energy: 20 733 600 MW"));
		}

	}

}
