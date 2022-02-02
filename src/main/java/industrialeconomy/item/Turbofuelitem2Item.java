
package industrialeconomy.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import industrialeconomy.itemgroup.ProjectMEGAItemGroup;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class Turbofuelitem2Item extends IndustrialEconomyModElements.ModElement {
	@ObjectHolder("industrial_economy:turbofuelitem_2")
	public static final Item block = null;

	public Turbofuelitem2Item(IndustrialEconomyModElements instance) {
		super(instance, 32);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("turbofuelitem_2");
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
