
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class TurboFuelMK4Item extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:turbo_fuel_mk_4")
	public static final Item block = null;

	public TurboFuelMK4Item(IndustrialEconomyModElements instance) {
		super(instance, 656);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("turbo_fuel_mk_4");
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
