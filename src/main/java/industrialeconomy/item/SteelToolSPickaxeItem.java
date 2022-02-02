
package industrialeconomy.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import industrialeconomy.itemgroup.ProjectMEGAItemGroup;

import industrialeconomy.block.SteelBlockBlock;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class SteelToolSPickaxeItem extends IndustrialEconomyModElements.ModElement {
	@ObjectHolder("industrial_economy:steel_tool_s_pickaxe")
	public static final Item block = null;

	public SteelToolSPickaxeItem(IndustrialEconomyModElements instance) {
		super(instance, 546);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2505;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 5;
			}

			public int getEnchantability() {
				return 15;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SteelBlockBlock.block));
			}
		}, 1, -3f, new Item.Properties().group(ProjectMEGAItemGroup.tab)) {
		}.setRegistryName("steel_tool_s_pickaxe"));
	}
}
