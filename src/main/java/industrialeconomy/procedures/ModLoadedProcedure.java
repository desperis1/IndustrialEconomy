package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class ModLoadedProcedure {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			executeProcedure(Collections.emptyMap());
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {

		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		String owner = "";
		File playerConfig = new File("");
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "prices.json");
		if (!playerConfig.exists()) {
			try {
				playerConfig.getParentFile().mkdirs();
				playerConfig.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
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

}
