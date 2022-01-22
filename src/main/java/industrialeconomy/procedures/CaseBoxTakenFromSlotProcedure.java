package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CaseBoxTakenFromSlotProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CaseBoxTakenFromSlot!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		{
			boolean _setval = (false);
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.player_rolling_case = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}

}
