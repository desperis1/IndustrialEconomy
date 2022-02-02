package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.block.BlockState;

import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.block.MinerblockBlock;
import industrialeconomy.block.LimestoneactiveBlock;
import industrialeconomy.block.IronOreNodeBlockBlock;
import industrialeconomy.block.CopperNodeBlock;
import industrialeconomy.block.CoalNodeBlock;
import industrialeconomy.block.CateriumNODEblockBlock;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.Gson;

public class MinerinactiveUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure MinerinactiveUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure MinerinactiveUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure MinerinactiveUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure MinerinactiveUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		String owner = "";
		double players_hub_x = 0;
		double players_hub_y = 0;
		double players_hub_z = 0;
		double miners_level = 0;
		File playerConfig = new File("");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		owner = (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "owner"));
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + ".json"));
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(playerConfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				mainObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (mainObject.get("Energy").getAsDouble() >= 15 * miners_level
						&& (LimestoneactiveBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()
								|| CoalNodeBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()
								|| IronOreNodeBlockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()
								|| CateriumNODEblockBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()
								|| CopperNodeBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock())) {
					{
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockState _bs = MinerblockBlock.block.getDefaultState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
							if (_property != null && _bs.get(_property) != null)
								try {
									_bs = _bs.with(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						TileEntity _te = world.getTileEntity(_bp);
						CompoundNBT _bnbt = null;
						if (_te != null) {
							_bnbt = _te.write(new CompoundNBT());
							_te.remove();
						}
						world.setBlockState(_bp, _bs, 3);
						if (_bnbt != null) {
							_te = world.getTileEntity(_bp);
							if (_te != null) {
								try {
									_te.read(_bso, _bnbt);
								} catch (Exception ignored) {
								}
							}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
