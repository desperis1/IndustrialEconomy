package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class LandCommandExecutedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure LandCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency cmdparams for procedure LandCommandExecuted!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure LandCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		IWorld world = (IWorld) dependencies.get("world");
		double grid_X = 0;
		double grid_Z = 0;
		String player_name = "";
		String city_checker = "";
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("buy"))) {
			player_name = (String) (entity.getDisplayName().getString());
			grid_X = (double) Math.floor(((entity.getPosX()) / 20));
			grid_Z = (double) Math.floor(((entity.getPosZ()) / 20));
			city_checker = (String) ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).belong_to_city)) + "" + (":") + "" + (grid_X) + "" + (":") + ""
					+ (grid_Z) + "" + (","));
			if ((IndustrialEconomyModVariables.WorldVariables.get(world).is_city.contains(city_checker))) {
				if ((IndustrialEconomyModVariables.WorldVariables.get(world).lands
						.contains(((":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("This land is owned by someone else"), (true));
					}
				} else if ((IndustrialEconomyModVariables.WorldVariables.get(world).lands
						.contains(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("This land is owned by you"), (true));
					}
				} else {
					if (((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) > 10000)
							&& (IndustrialEconomyModVariables.WorldVariables.get(world).is_city
									.contains(((":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (",")))))) {
						{
							double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) - 10000);
							entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.player_money = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						IndustrialEconomyModVariables.WorldVariables
								.get(world).lands = (String) ((IndustrialEconomyModVariables.WorldVariables.get(world).lands) + ""
										+ (((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))));
						IndustrialEconomyModVariables.WorldVariables.get(world).syncData(world);
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You Bought this Land !"), (true));
						}
						{
							double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_number_of_land) + 1);
							entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.player_number_of_land = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity)
									.sendStatusMessage(
											new StringTextComponent((("You now own: ") + ""
													+ ((new java.text.DecimalFormat("#").format(((entity
															.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
															.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_number_of_land))))
													+ "" + (" lands."))),
											(false));
						}
					} else {
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity)
									.sendStatusMessage(new StringTextComponent("You Dont Have Enough Money! Or This Land is not in CITY"), (true));
						}
					}
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont belong to this city"), (true));
				}
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("sell"))) {
			if (((IndustrialEconomyModVariables.WorldVariables.get(world).lands
					.contains(((":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))
					&& (!(IndustrialEconomyModVariables.WorldVariables.get(world).lands
							.contains(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))))) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("This land is owned by someone else, you cannot sell it"),
							(true));
				}
			} else {
				player_name = (String) (entity.getDisplayName().getString());
				grid_X = (double) Math.floor(((entity.getPosX()) / 20));
				grid_Z = (double) Math.floor(((entity.getPosZ()) / 20));
				IndustrialEconomyModVariables.WorldVariables
						.get(world).lands = (String) (IndustrialEconomyModVariables.WorldVariables.get(world).lands
								.replace(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (",")), " "));
				IndustrialEconomyModVariables.WorldVariables.get(world).syncData(world);
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You Sell this Land !"), (true));
				}
				{
					double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_number_of_land) - 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_number_of_land = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent(
											(("You now own: ") + ""
													+ ((new java.text.DecimalFormat("#").format(((entity
															.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
															.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_number_of_land))))
													+ "" + (" lands."))),
									(false));
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent((IndustrialEconomyModVariables.WorldVariables.get(world).lands
											.replace(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (",")), ""))),
									(false));
				}
			}
		}
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("info"))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent((("lands") + "" + (IndustrialEconomyModVariables.WorldVariables.get(world).lands))), (false));
			}
		}
	}
}
