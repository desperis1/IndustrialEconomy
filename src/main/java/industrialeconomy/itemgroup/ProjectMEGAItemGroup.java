
package industrialeconomy.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import industrialeconomy.item.CircuitBoarditemItem;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class ProjectMEGAItemGroup extends IndustrialEconomyModElements.ModElement {
	public ProjectMEGAItemGroup(IndustrialEconomyModElements instance) {
		super(instance, 218);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabproject_mega") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CircuitBoarditemItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
