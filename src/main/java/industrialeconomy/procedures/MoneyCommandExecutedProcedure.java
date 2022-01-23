package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class MoneyCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure MoneyCommandExecuted!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure MoneyCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency cmdparams for procedure MoneyCommandExecuted!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		double amount = 0;
		if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("")) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(
								new StringTextComponent(
										("You have: "
												+ new java.text.DecimalFormat("#")
														.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
																.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money)
												+ " \uFFFD")),
								(false));
			}
		} else if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("add") && entity.hasPermissionLevel((int) 2)) {
			{
				double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(new Object() {
							public String getText() {
								String param = (String) cmdparams.get("1");
								if (param != null) {
									return param;
								}
								return "";
							}
						}.getText()));
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("1");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()) + " \uFFFD " + "sucessfully added.")), (false));
			}
		} else if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("pay")) {
			if ((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("")) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Example usage: " + "/money pay playername amount")), (false));
				}
			}
			if (!(new Object() {
				public String getText() {
					String param = (String) cmdparams.get("2");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("") && !(new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("")) {
				amount = Math.abs(new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(new Object() {
					public String getText() {
						String param = (String) cmdparams.get("2");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()));
				if ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money >= amount) {
					{
						List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
						for (Entity entityiterator : _players) {
							if ((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("1");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText()).equals(entityiterator.getDisplayName().getString())) {
								{
									double _setval = ((entityiterator.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + amount);
									entityiterator.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.ifPresent(capability -> {
												capability.player_money = _setval;
												capability.syncPlayerVariables(entityiterator);
											});
								}
								{
									double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money - amount);
									entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.player_money = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
									((PlayerEntity) entityiterator).sendStatusMessage(
											new StringTextComponent(("You receive " + amount + "\uFFFD from " + entity.getDisplayName().getString())),
											(false));
								}
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
											("You send " + amount + "\uFFFD to " + entityiterator.getDisplayName().getString())), (false));
								}
							}
						}
					}
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont have enough money!"), (false));
					}
				}
			}
		} else if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("info")) {
			if ((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("")) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Example:  /money info playerName"), (false));
				}
			}
			if (!(new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("") && (new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).equals("info")) {
				{
					List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
					for (Entity entityiterator : _players) {
						if ((new Object() {
							public String getText() {
								String param = (String) cmdparams.get("1");
								if (param != null) {
									return param;
								}
								return "";
							}
						}.getText()).equals(entityiterator.getDisplayName().getString())) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Player " + (new Object() {
									public String getText() {
										String param = (String) cmdparams.get("1");
										if (param != null) {
											return param;
										}
										return "";
									}
								}.getText()) + " have "
										+ Math.round((entityiterator.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money)
										+ " \uFFFD")), (false));
							}
						}
					}
				}
			}
		}
	}
}
