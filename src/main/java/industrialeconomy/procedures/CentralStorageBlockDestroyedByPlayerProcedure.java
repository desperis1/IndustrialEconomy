package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CentralStorageBlockDestroyedByPlayerProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CentralStorageBlockDestroyedByPlayer!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		File playerStorageConfig = new File("");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + "_storage.json"));
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(playerStorageConfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();

				mainObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				mainObject.addProperty("placed", (false));
				{
					Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();

					try {
						FileWriter fileWriter = new FileWriter(playerStorageConfig);
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
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Storage was destroyed"), (false));
		}
	}

}
