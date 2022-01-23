package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class PlayerJoinWorldProcedure {

	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure PlayerJoinWorld!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		File playerConfig = new File("");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + ".json"));
		if (!playerConfig.exists()) {
			try {
				playerConfig.getParentFile().mkdirs();
				playerConfig.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			mainObject.addProperty("MinerLevel", 1);
			mainObject.addProperty("Money", 0);
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
		}
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
				mainObject.addProperty("isOnline", (true));

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
	}

}
