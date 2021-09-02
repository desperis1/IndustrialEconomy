package industrialeconomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.JsonObject;
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
		File jsonfile = new File("");
		itemforsell = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		items_amount = (double) ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount());
		jsonfile = new File(((FMLPaths.GAMEDIR.get().toString()) + "" + ("\\\\prices")),
				File.separator + (((((itemforsell).getDisplayName().getString()).replace(" ", ""))) + "" + (".json")));
		if (!jsonfile.exists()) {
			try {
				jsonfile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		{
			try {
				BufferedReader jsonfileReader = new BufferedReader(new FileReader(jsonfile));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = jsonfileReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				jsonfileReader.close();
				JsonObject gson = new Gson().fromJson(jsonstringbuilder.toString(), JsonObject.class);
				price_from_server = (double) gson.get("price").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		{
			double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + (items_amount * price_from_server));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.player_money = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("You Sell ") + "" + (((itemforsell).getDisplayName().getString()))
					+ "" + (" for: ") + "" + ((items_amount * price_from_server)) + "" + (" \u20AC"))), (false));
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = (itemforsell);
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
					((PlayerEntity) entity).container.func_234641_j_());
		}
	}
}
