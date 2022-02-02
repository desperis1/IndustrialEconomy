package industrialeconomy.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;

import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class Energyplazmamk2EntityCollidesInTheBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure Energyplazmamk2EntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure Energyplazmamk2EntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure Energyplazmamk2EntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure Energyplazmamk2EntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure Energyplazmamk2EntityCollidesInTheBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		String owner = "";
		double players_hub_x = 0;
		double players_hub_y = 0;
		double players_hub_z = 0;
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		File playerConfig = new File("");
		owner = (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "owner"));
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + ".json"));
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
				if (mainObject.get("Energy").getAsDouble() > 1000
						&& (entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_energy < 15000) {
					{
						double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_energy + 5);
						entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.DSA_energy = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 250));
					if (world instanceof World && !world.isRemote()) {
						((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("industrial_economy:plazma")),
								SoundCategory.NEUTRAL, (float) 0.01, (float) 0.01);
					} else {
						((World) world).playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("industrial_economy:plazma")),
								SoundCategory.NEUTRAL, (float) 0.01, (float) 0.01, false);
					}
					world.addParticle(ParticleTypes.PORTAL, (x + 0.5), y, (z + 0.5), 0, 0.5, 0);
					world.addParticle(ParticleTypes.PORTAL, (x + 0.5), y, (z + 0.5), 0.7, 0.5, 0);
					world.addParticle(ParticleTypes.PORTAL, (x + 0.5), y, (z + 0.5), (-0.7), 0.5, 0);
					world.addParticle(ParticleTypes.PORTAL, (x + 0.5), y, (z + 0.5), 0, 0.5, (-0.7));
					world.addParticle(ParticleTypes.PORTAL, (x + 0.5), y, (z + 0.5), 0, 0.5, 0.7);
				}

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
