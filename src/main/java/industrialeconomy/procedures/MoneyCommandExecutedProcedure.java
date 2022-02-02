package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

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
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		File playerConfig = new File("");
		double amount = 0;
		double wanttosend = 0;
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + ".json"));
		if ((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()).equals("")) {
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
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(
								new StringTextComponent(
										("You have: " + new java.text.DecimalFormat("#").format(mainObject.get("Money").getAsDouble()) + " coins.")),
								(false));
					}

				} catch (IOException e) {
					e.printStackTrace();
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
		}.getText()).equals("add") && entity.hasPermissionLevel((int) 2)) {
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
					mainObject.addProperty("Money", (mainObject.get("Money").getAsDouble() + new Object() {
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
					}.getText())));
					{
						Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(playerConfig);
							fileWriter.write(mainGSONBuilderVariable.toJson(mainObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
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
				}.getText()) + " coins " + "sucessfully added.")), (false));
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
						wanttosend = mainObject.get("Money").getAsDouble();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (wanttosend >= amount) {
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
								playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
										File.separator + (entityiterator.getDisplayName().getString() + ".json"));
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
										mainObject.addProperty("Money", (mainObject.get("Money").getAsDouble() + amount));

									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(playerConfig);
										fileWriter.write(mainGSONBuilderVariable.toJson(mainObject));
										fileWriter.close();
									} catch (IOException exception) {
										exception.printStackTrace();
									}
								}
								playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
										File.separator + (entity.getDisplayName().getString() + ".json"));
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
										mainObject.addProperty("Money", (mainObject.get("Money").getAsDouble() - amount));

									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								{
									Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
									try {
										FileWriter fileWriter = new FileWriter(playerConfig);
										fileWriter.write(mainGSONBuilderVariable.toJson(mainObject));
										fileWriter.close();
									} catch (IOException exception) {
										exception.printStackTrace();
									}
								}
								if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
									((PlayerEntity) entityiterator).sendStatusMessage(
											new StringTextComponent(("You receive " + amount + " coins from " + entity.getDisplayName().getString())),
											(false));
								}
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
											("You send " + amount + " coins to " + entityiterator.getDisplayName().getString())), (false));
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
		}
	}
}
