package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ItemInCaseBoxProcedure {

	public static ItemStack executeProcedure(Map<String, Object> dependencies) {

		if (Math.random() < 0.0005) {
			return new ItemStack(Money100000Item.block);
		}
		if (Math.random() < 0.0005) {
			return new ItemStack(CrystalchunkItem.block);
		}
		if (Math.random() < 0.0006) {
			return new ItemStack(Money50000Item.block);
		}
		if (Math.random() < 0.0009) {
			return new ItemStack(Money10000Item.block);
		}
		if (Math.random() < 0.001) {
			return new ItemStack(EmptymoduleItem.block);
		}
		if (Math.random() < 0.005) {
			return new ItemStack(CpuItem.block);
		}
		if (Math.random() < 0.005) {
			return new ItemStack(Blocks.DIAMOND_BLOCK);
		}
		if (Math.random() < 0.01) {
			return new ItemStack(ControlUnitItem.block);
		}
		if (Math.random() < 0.01) {
			return new ItemStack(DiamondsteelplateItem.block);
		}
		if (Math.random() < 0.01) {
			return new ItemStack(SolarpanelblockinactiveBlock.block);
		}
		if (Math.random() < 0.01) {
			return new ItemStack(Items.DIAMOND);
		}
		if (Math.random() < 0.02) {
			return new ItemStack(GeneratorMK2inactiveBlock.block);
		}
		if (Math.random() < 0.05) {
			return new ItemStack(DiamondscrewitemItem.block);
		}
		if (Math.random() < 0.06) {
			return new ItemStack(MinerinactiveBlock.block);
		}
		if (Math.random() < 0.07) {
			return new ItemStack(GeneratorMK1inactiveBlock.block);
		}
		if (Math.random() < 0.1) {
			return new ItemStack(IndustrialDiamondItem.block);
		}
		if (Math.random() < 0.15) {
			return new ItemStack(Money100Item.block);
		}
		if (Math.random() < 0.15) {
			return new ItemStack(ScrewItem.block);
		}
		if (Math.random() < 0.25) {
			return new ItemStack(HardenedcobbleBlock.block);
		}
		if (Math.random() < 0.25) {
			return new ItemStack(Blocks.SPRUCE_LOG);
		}
		if (Math.random() < 0.25) {
			return new ItemStack(Blocks.WARPED_STEM);
		}
		if (Math.random() < 0.25) {
			return new ItemStack(Blocks.LADDER);
		}
		if (Math.random() < 0.25) {
			return new ItemStack(Items.FEATHER);
		}
		return new ItemStack(Blocks.DIRT);
	}

}
