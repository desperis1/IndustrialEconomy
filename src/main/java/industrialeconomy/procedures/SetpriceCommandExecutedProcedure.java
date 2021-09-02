package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

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
		File jsonfile = new File("");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals(""))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent((("You can set price to item in hand.") + "" + ("Example: /setprice 500"))), (false));
			}
		}
		price = (double) new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()));
		item_in_hand = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		jsonfile = new File(((FMLPaths.GAMEDIR.get().toString()) + "" + ("\\\\prices")),
				File.separator + (((((item_in_hand).getDisplayName().getString()).replace(" ", ""))) + "" + (".json")));
		if (!jsonfile.exists()) {
			try {
				jsonfile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			JsonObject gson = new JsonObject();
			gson.addProperty("price", price);
			try {
				FileWriter jsonfilefw = new FileWriter(jsonfile);
				jsonfilefw.write(mainGSONBuilderVariable.toJson(gson));
				jsonfilefw.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
					(("You set price for ") + "" + (((item_in_hand).getDisplayName().getString())) + "" + (" to ") + "" + (price))), (false));
		}
	}
}
