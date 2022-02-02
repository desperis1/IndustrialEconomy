package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class LiquidHydrogenMobplayerCollidesBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure LiquidHydrogenMobplayerCollidesBlock!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		entity.attackEntityFrom(DamageSource.GENERIC, (float) 5);

	}

}
