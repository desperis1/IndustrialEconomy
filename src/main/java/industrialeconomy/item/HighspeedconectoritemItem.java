
package industrialeconomy.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;

import industrialeconomy.itemgroup.ProjectMEGAItemGroup;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class HighspeedconectoritemItem extends IndustrialEconomyModElements.ModElement {
	@ObjectHolder("industrial_economy:highspeedconectoritem")
	public static final Item block = null;

	public HighspeedconectoritemItem(IndustrialEconomyModElements instance) {
		super(instance, 102);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ProjectMEGAItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("highspeedconectoritem");
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
			list.add(new StringTextComponent("Copper: 44"));
			list.add(new StringTextComponent("Caterium: 43"));
			list.add(new StringTextComponent("Iron: 108"));
			list.add(new StringTextComponent("Coal: 54"));
		}
	}
}
