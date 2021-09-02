package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import java.util.Map;

import industrialeconomy.IndustrialEconomyMod;

public class AllowedBlocksInWorldProcedure {
	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure AllowedBlocksInWorld!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure AllowedBlocksInWorld!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure AllowedBlocksInWorld!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure AllowedBlocksInWorld!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((Blocks.TORCH == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
				|| (Blocks.WALL_TORCH == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))) {
			return (true);
		}
		if (((Blocks.SOUL_TORCH == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
				|| (Blocks.SOUL_WALL_TORCH == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))) {
			return (true);
		}
		if (((Blocks.END_PORTAL_FRAME == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
				|| (Blocks.END_PORTAL == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))) {
			return (true);
		}
		if ((Blocks.END_GATEWAY == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.WHITE_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.ORANGE_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.MAGENTA_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.LIGHT_BLUE_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.YELLOW_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.LIME_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.PINK_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.GRAY_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.LIGHT_GRAY_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.CYAN_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.PURPLE_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.BLUE_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.BROWN_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.GREEN_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.RED_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if ((Blocks.BLACK_SHULKER_BOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
			return (true);
		}
		if (((Blocks.DIRT == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) || ((Blocks.OAK_SAPLING == (world
				.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
				|| ((Blocks.SPRUCE_SAPLING == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
						|| ((Blocks.BIRCH_SAPLING == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
								|| ((Blocks.JUNGLE_SAPLING == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
										|| ((Blocks.ACACIA_SAPLING == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
												|| ((Blocks.DARK_OAK_SAPLING == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getBlock())
														|| ((Blocks.SUGAR_CANE == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getBlock())
																|| (Blocks.BAMBOO_SAPLING == (world
																		.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																				.getBlock())))))))))) {
			return (true);
		}
		return (false);
	}
}
