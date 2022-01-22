package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class TestsetCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		File prices = new File("");
		com.google.gson.JsonObject pricesobj = new com.google.gson.JsonObject();
		prices = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "prices.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(prices));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				pricesobj = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				pricesobj.addProperty("name", 0);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(prices);
				fileWriter.write(mainGSONBuilderVariable.toJson(pricesobj));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
