package industrialeconomy.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

import industrialeconomy.IndustrialEconomyMod;

public class AdminmenuDayNightProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure AdminmenuDayNight!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (12000 < world.getWorldInfo().getDayTime()) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).setDayTime((int) 0);
		} else if (12000 > world.getWorldInfo().getDayTime()) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).setDayTime((int) 18000);
		}
	}
}
