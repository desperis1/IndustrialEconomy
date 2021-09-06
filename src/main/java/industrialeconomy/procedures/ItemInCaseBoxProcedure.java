package industrialeconomy.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;

import java.util.Map;

import industrialeconomy.item.ScrewItem;
import industrialeconomy.item.IndustrialDiamondItem;
import industrialeconomy.item.DiamondscrewitemItem;
import industrialeconomy.item.ControlUnitItem;

import industrialeconomy.block.SolarpanelblockinactiveBlock;
import industrialeconomy.block.HardenedcobbleBlock;
import industrialeconomy.block.GeneratorMK1inactiveBlock;

public class ItemInCaseBoxProcedure {
	public static ItemStack executeProcedure(Map<String, Object> dependencies) {
		if ((Math.random() < 0.01)) {
			return new ItemStack(ControlUnitItem.block);
		}
		if ((Math.random() < 0.05)) {
			return new ItemStack(SolarpanelblockinactiveBlock.block);
		}
		if ((Math.random() < 0.05)) {
			return new ItemStack(DiamondscrewitemItem.block);
		}
		if ((Math.random() < 0.07)) {
			return new ItemStack(GeneratorMK1inactiveBlock.block);
		}
		if ((Math.random() < 0.1)) {
			return new ItemStack(IndustrialDiamondItem.block);
		}
		if ((Math.random() < 0.25)) {
			return new ItemStack(Blocks.COBBLESTONE);
		}
		if ((Math.random() < 0.15)) {
			return new ItemStack(ScrewItem.block);
		}
		return new ItemStack(HardenedcobbleBlock.block);
	}
}
