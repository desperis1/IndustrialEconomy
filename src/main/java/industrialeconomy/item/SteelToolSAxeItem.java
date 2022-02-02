
package industrialeconomy.item;

import net.minecraft.entity.ai.attributes.Attributes;

@IndustrialEconomyModElements.ModElement.Tag
public class SteelToolSAxeItem extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:steel_tool_s_axe")
	public static final Item block = null;

	public SteelToolSAxeItem(IndustrialEconomyModElements instance) {
		super(instance, 547);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2505;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 6f;
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

		}.setRegistryName("steel_tool_s_axe"));
	}

}
