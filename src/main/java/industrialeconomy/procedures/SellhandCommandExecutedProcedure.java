package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class SellhandCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure SellhandCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemforsell = ItemStack.EMPTY;
		double items_amount = 0;
		double price_from_server = 0;
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		File playerConfig = new File("");
		File prices = new File("");
		itemforsell = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		items_amount = ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount());
		prices = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "prices.json");
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + ".json"));
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(prices));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				mainObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (mainObject.get((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getDisplayName().getString())).getAsDouble() > 0) {
					price_from_server = mainObject
							.get((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()))
							.getAsDouble();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
					("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getDisplayName().getString()) + " for " + items_amount * price_from_server + " coins")),
					(false));
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
					((PlayerEntity) entity).container.func_234641_j_());
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
				mainObject.addProperty("Money", (mainObject.get("Money").getAsDouble() + items_amount * price_from_server));
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
	}
}
