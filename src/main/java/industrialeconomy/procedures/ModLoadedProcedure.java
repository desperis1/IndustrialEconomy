package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class ModLoadedProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldLoad(WorldEvent.Load event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		File Prices = new File("");
		com.google.gson.JsonObject MainObject = new com.google.gson.JsonObject();
		Prices = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "prices.json");
		if (!Prices.exists()) {
			try {
				Prices.getParentFile().mkdirs();
				Prices.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			MainObject.addProperty("Loaded", (true));
		}
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(Prices);
				fileWriter.write(mainGSONBuilderVariable.toJson(MainObject));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
