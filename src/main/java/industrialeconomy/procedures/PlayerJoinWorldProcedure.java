package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

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
			mainObject.addProperty("Money", 1000);
			mainObject.addProperty("Energy", 0);
			mainObject.addProperty("Coal", 0);
			mainObject.addProperty("Iron", 0);
			mainObject.addProperty("minerLevels", 1);
			mainObject.addProperty("Copper", 0);
			mainObject.addProperty("Caterium", 0);
			mainObject.addProperty("SandStone", 0);
			mainObject.addProperty("Money", 0);
			mainObject.addProperty("EnergyForMinerUpgrade", 1000000000);
			mainObject.addProperty("Eup", 0);
			mainObject.addProperty("Edown", 0);
			mainObject.addProperty("mamSteel", (false));
			mainObject.addProperty("mamDiamondSteel", (false));
			mainObject.addProperty("mam2CoreCPU", (false));
			mainObject.addProperty("mam4CoreCPU", (false));
			mainObject.addProperty("mam8CoreCPU", (false));
			mainObject.addProperty("mam16CoreCPU", (false));
			mainObject.addProperty("mam32CoreCPU", (false));
			mainObject.addProperty("mam64CoreCPU", (false));
			mainObject.addProperty("mam128CoreCPU", (false));
			mainObject.addProperty("mam256CoreCPU", (false));
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
