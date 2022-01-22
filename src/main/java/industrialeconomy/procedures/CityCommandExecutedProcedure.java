package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class CityCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure CityCommandExecuted!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure CityCommandExecuted!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure CityCommandExecuted!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure CityCommandExecuted!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CityCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency cmdparams for procedure CityCommandExecuted!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		double local_x = 0;
		double local_z = 0;
		double local_y = 0;
		double sync_X_with_grid = 0;
		double sync_Z_with_grid = 0;
		double X_multipler = 0;
		double Z_multipler = 0;
		double land_X_grid = 0;
		double land_Z_grid = 0;
		String cityname = "";
		String playername = "";
		String online_players = "";
		if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("create") && (entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money >= 1000000) {
			{
				double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money - 1000000);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			cityname = (new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText());
			X_multipler = Math.round(x / 20);
			sync_X_with_grid = (X_multipler * 20);
			Z_multipler = Math.round(z / 20);
			sync_Z_with_grid = (Z_multipler * 20);
			{
				String _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).players_city_name + "," + cityname);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.players_city_name = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(new StringTextComponent(("synced X: " + sync_X_with_grid + "synced Z: " + sync_Z_with_grid)), (false));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("You Created City with name: " + cityname)), (false));
			}
			local_z = (sync_Z_with_grid + 100);
			for (int index0 = 0; index0 < (int) (10); index0++) {
				local_x = (sync_X_with_grid + 100);
				for (int index1 = 0; index1 < (int) (10); index1++) {
					while (world.canBlockSeeSky(new BlockPos((int) local_x, (int) local_y, (int) local_z)) == false) {
						local_y = (local_y + 1);
					}
					while (world.canBlockSeeSky(new BlockPos((int) local_x, (int) local_y, (int) local_z)) == true
							&& world.isAirBlock(new BlockPos((int) local_x, (int) (local_y - 1), (int) local_z))) {
						local_y = (local_y - 1);
					}
					if (world.canBlockSeeSky(new BlockPos((int) local_x, (int) local_y, (int) local_z)) == true
							&& !world.isAirBlock(new BlockPos((int) local_x, (int) (local_y - 1), (int) local_z))) {
						world.setBlockState(new BlockPos((int) local_x, (int) local_y, (int) local_z), Blocks.RED_WOOL.getDefaultState(), 3);
						land_X_grid = (local_x / 20);
						land_Z_grid = (local_z / 20);
						IndustrialEconomyModVariables.WorldVariables
								.get(world).is_city = (IndustrialEconomyModVariables.WorldVariables.get(world).is_city + ""
										+ (cityname + ":" + land_X_grid + ":" + land_Z_grid + ","));
						IndustrialEconomyModVariables.WorldVariables.get(world).syncData(world);
						local_y = (y + 1);
					}
					local_x = (local_x - 20);
				}
				local_z = (local_z - 20);
			}
		}
		if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("join")) {
			cityname = (new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText());
			playername = (new Object() {
				public String getText() {
					String param = (String) cmdparams.get("2");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText());
			{
				List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
				for (Entity entityiterator : _players) {
					online_players = (online_players + "" + entityiterator.getDisplayName().getString() + ",");
				}
			}
			if ((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("") || (new Object() {
				public String getText() {
					String param = (String) cmdparams.get("2");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("")) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Example of usage: /city join cityname playername"), (false));
				}
			}
			if (!((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).players_city_name).contains(new Object() {
						public String getText() {
							String param = (String) cmdparams.get("1");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You are not owner of this city or this city doesnt not exist"),
							(false));
				}
			}
			if (!online_players.contains(new Object() {
				public String getText() {
					String param = (String) cmdparams.get("2");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Player doesnt not exist or is not online"), (false));
				}
			}
			if (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).players_city_name).contains(new Object() {
						public String getText() {
							String param = (String) cmdparams.get("1");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText()) && online_players.contains(new Object() {
						public String getText() {
							String param = (String) cmdparams.get("2");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText())) {
				{
					List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
					for (Entity entityiterator : _players) {
						if ((entityiterator.getDisplayName().getString()).equals(new Object() {
							public String getText() {
								String param = (String) cmdparams.get("2");
								if (param != null) {
									return param;
								}
								return "";
							}
						}.getText())) {
							{
								String _setval = (new Object() {
									public String getText() {
										String param = (String) cmdparams.get("1");
										if (param != null) {
											return param;
										}
										return "";
									}
								}.getText());
								entityiterator.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.ifPresent(capability -> {
											capability.belong_to_city = _setval;
											capability.syncPlayerVariables(entityiterator);
										});
							}
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
										("Player " + entityiterator.getDisplayName().getString() + " was added to this city")), (false));
							}
						}
					}
				}
			}
		}
	}
}
