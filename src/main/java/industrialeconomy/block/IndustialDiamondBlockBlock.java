
package industrialeconomy.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@IndustrialEconomyModElements.ModElement.Tag
public class IndustialDiamondBlockBlock extends IndustrialEconomyModElements.ModElement {

	@ObjectHolder("industrial_economy:industial_diamond_block")
	public static final Block block = null;

	public IndustialDiamondBlockBlock(IndustrialEconomyModElements instance) {
		super(instance, 593);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ProjectMEGAItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(8f, 25f).setLightLevel(s -> 0));

			setRegistryName("industial_diamond_block");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

	}

}
