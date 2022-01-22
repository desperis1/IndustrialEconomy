package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import java.util.Map;

import industrialeconomy.block.PresserblockBlock;
import industrialeconomy.block.IronextractorBlock;
import industrialeconomy.block.HubBlock;
import industrialeconomy.block.FoundyblockBlock;
import industrialeconomy.block.ElectricFurnanceBlockBlock;
import industrialeconomy.block.CopperExtractorBlock;
import industrialeconomy.block.ConstructorblockBlock;
import industrialeconomy.block.ConcretemixerBlock;
import industrialeconomy.block.CoalExtractorBlock;
import industrialeconomy.block.ChargerblockBlock;
import industrialeconomy.block.CateriumExtractorBlock;
import industrialeconomy.block.AssemblerblockBlock;

import industrialeconomy.IndustrialEconomyMod;

public class DisallowedToOpenProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure DisallowedToOpen!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure DisallowedToOpen!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure DisallowedToOpen!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure DisallowedToOpen!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (Blocks.HOPPER == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.CHEST == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (HubBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.FURNACE == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.BLAST_FURNACE == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (ElectricFurnanceBlockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (ConcretemixerBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (FoundyblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (PresserblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (ChargerblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (AssemblerblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.BARREL == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (ConstructorblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (IronextractorBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (CoalExtractorBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (CateriumExtractorBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (CopperExtractorBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.DISPENSER == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.DROPPER == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		if (Blocks.JUKEBOX == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			return true;
		}
		return false;
	}
}
