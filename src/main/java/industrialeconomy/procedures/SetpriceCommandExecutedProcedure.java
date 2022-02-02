package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class SetpriceCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure SetpriceCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency cmdparams for procedure SetpriceCommandExecuted!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");

		double price = 0;
		ItemStack item_in_hand = ItemStack.EMPTY;
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		String owner = "";
		File playerConfig = new File("");
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
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("You can set price to item in hand." + "Example: /setprice 500")),
						(false));
			}
		}
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "prices.json");
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
				if (!(new Object() {
					public String getText() {
						String param = (String) cmdparams.get("0");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()).equals("") && !((ItemStack.EMPTY)
						.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem())) {
					item_in_hand = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					price = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(new Object() {
						public String getText() {
							String param = (String) cmdparams.get("0");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText());
					mainObject.addProperty(((item_in_hand).getDisplayName().getString()), new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(new Object() {
						public String getText() {
							String param = (String) cmdparams.get("0");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText()));
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
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(
								new StringTextComponent(("You set price for " + (item_in_hand).getDisplayName().getString() + " to :" + new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(new Object() {
									public String getText() {
										String param = (String) cmdparams.get("0");
										if (param != null) {
											return param;
										}
										return "";
									}
								}.getText()) + " coins ")), (false));
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
