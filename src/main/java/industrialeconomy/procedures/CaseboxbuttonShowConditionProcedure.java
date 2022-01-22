package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CaseboxbuttonShowConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CaseboxbuttonShowCondition!");
			return false;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_rolling_case == false) {
			return true;
		}
		return false;
	}

}
