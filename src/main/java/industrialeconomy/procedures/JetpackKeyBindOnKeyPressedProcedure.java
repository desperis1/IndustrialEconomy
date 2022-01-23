package industrialeconomy.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import industrialeconomy.item.DiamondSteelArmorItem;

import industrialeconomy.IndustrialEconomyMod;

public class JetpackKeyBindOnKeyPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure JetpackKeyBindOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (DiamondSteelArmorItem.body == ((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
				: ItemStack.EMPTY).getItem()) {
			entity.getPersistentData().putBoolean("Jetpack", (true));
		}
	}
}
