package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;

import java.util.Map;

import industrialeconomy.IndustrialEconomyMod;

public class PlasmaShootrangeditemWhileBulletFlyingTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure PlasmaShootrangeditemWhileBulletFlyingTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure PlasmaShootrangeditemWhileBulletFlyingTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure PlasmaShootrangeditemWhileBulletFlyingTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure PlasmaShootrangeditemWhileBulletFlyingTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.1, 0);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0.1, 0, 0);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0, (-0.1), 0);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, (-0.1));
	}
}
