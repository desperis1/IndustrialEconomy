package industrialeconomy.procedures;

import net.minecraft.item.ItemStack;

import java.util.Random;
import java.util.Map;

import industrialeconomy.IndustrialEconomyMod;

public class OxygencanisterItemIsCraftedsmeltedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency itemstack for procedure OxygencanisterItemIsCraftedsmelted!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) 990, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
	}
}
