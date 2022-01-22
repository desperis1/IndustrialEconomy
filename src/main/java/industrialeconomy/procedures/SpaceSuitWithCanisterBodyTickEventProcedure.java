package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class SpaceSuitWithCanisterBodyTickEventProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure SpaceSuitWithCanisterBodyTickEvent!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
				.getItem() == SpaceSuitItem.helmet
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY)
						.getItem() == SpaceSuitWithCanisterItem.body
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
						.getItem() == SpaceSuitItem.legs
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET) : ItemStack.EMPTY)
						.getItem() == SpaceSuitItem.boots) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY))
					.getDamage() < 990) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(OxygenEffectPotionEffect.potion, (int) 60, (int) 1, (false), (false)));
				if (Math.random() < 0.05) {
					{
						ItemStack _ist = ((entity instanceof LivingEntity)
								? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST)
								: ItemStack.EMPTY);
						if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
							_ist.shrink(1);
							_ist.setDamage(0);
						}
					}
				}
			}
		}
	}

}
