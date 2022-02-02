package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class PlaceToolRightClickedOnBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure PlaceToolRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure PlaceToolRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure PlaceToolRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure PlaceToolRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure PlaceToolRightClickedOnBlock!");
			return;
		}

		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");

		Direction looking_at_side = Direction.NORTH;
		looking_at_side = (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getFace());
		if (Direction.UP == looking_at_side) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PresserMK2Block.block.getDefaultState(), 3);
		} else if (Direction.DOWN == looking_at_side) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), PresserMK2Block.block.getDefaultState(), 3);
		} else if (Direction.NORTH == looking_at_side) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), PresserMK2Block.block.getDefaultState(), 3);
		} else if (Direction.SOUTH == looking_at_side) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), PresserMK2Block.block.getDefaultState(), 3);
		} else if (Direction.WEST == looking_at_side) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), PresserMK2Block.block.getDefaultState(), 3);
		} else if (Direction.EAST == looking_at_side) {
			world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), PresserMK2Block.block.getDefaultState(), 3);
		}
	}

}
