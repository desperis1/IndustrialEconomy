package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class HomeCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure HomeCommandExecuted!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure HomeCommandExecuted!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure HomeCommandExecuted!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure HomeCommandExecuted!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");

		File playerConfig = new File("");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		playerConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + ".json"));
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
				mainObject.addProperty("backX", Math.round(x));
				mainObject.addProperty("backY", Math.round(y));
				mainObject.addProperty("backZ", Math.round(z));
				mainObject.addProperty("backDimension", ("" + entity.world.getDimensionKey()));
				if (!("" + entity.world.getDimensionKey()).equals(mainObject.get("homeDimension").getAsString())
						&& mainObject.get("homeDimension").getAsString().contains("overworld")) {
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
							RegistryKey<World> destinationType = World.OVERWORLD;

							ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
							if (nextWorld != null) {
								((ServerPlayerEntity) _ent).connection
										.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
								((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(),
										nextWorld.getSpawnPoint().getY() + 1, nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
								for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
									((ServerPlayerEntity) _ent).connection
											.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
								}
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
							}
						}
					}
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate(mainObject.get("homeX").getAsDouble(), mainObject.get("homeY").getAsDouble(),
								mainObject.get("homeZ").getAsDouble());
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation(mainObject.get("homeX").getAsDouble(),
									mainObject.get("homeY").getAsDouble(), mainObject.get("homeZ").getAsDouble(), _ent.rotationYaw,
									_ent.rotationPitch, Collections.emptySet());
						}
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Now you are at home."), (true));
					}
				}
				if (!("" + entity.world.getDimensionKey()).equals(mainObject.get("homeDimension").getAsString())
						&& mainObject.get("homeDimension").getAsString().contains("nether")) {
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
							RegistryKey<World> destinationType = World.THE_NETHER;

							ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
							if (nextWorld != null) {
								((ServerPlayerEntity) _ent).connection
										.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
								((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(),
										nextWorld.getSpawnPoint().getY() + 1, nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
								for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
									((ServerPlayerEntity) _ent).connection
											.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
								}
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
							}
						}
					}
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate(mainObject.get("homeX").getAsDouble(), mainObject.get("homeY").getAsDouble(),
								mainObject.get("homeZ").getAsDouble());
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation(mainObject.get("homeX").getAsDouble(),
									mainObject.get("homeY").getAsDouble(), mainObject.get("homeZ").getAsDouble(), _ent.rotationYaw,
									_ent.rotationPitch, Collections.emptySet());
						}
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Now you are at home."), (true));
					}
				}
				if (!("" + entity.world.getDimensionKey()).equals(mainObject.get("homeDimension").getAsString())
						&& mainObject.get("homeDimension").getAsString().contains("end")) {
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
							RegistryKey<World> destinationType = World.THE_END;

							ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
							if (nextWorld != null) {
								((ServerPlayerEntity) _ent).connection
										.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
								((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(),
										nextWorld.getSpawnPoint().getY() + 1, nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
								for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
									((ServerPlayerEntity) _ent).connection
											.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
								}
								((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
							}
						}
					}
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate(mainObject.get("homeX").getAsDouble(), mainObject.get("homeY").getAsDouble(),
								mainObject.get("homeZ").getAsDouble());
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation(mainObject.get("homeX").getAsDouble(),
									mainObject.get("homeY").getAsDouble(), mainObject.get("homeZ").getAsDouble(), _ent.rotationYaw,
									_ent.rotationPitch, Collections.emptySet());
						}
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Now you are at home."), (true));
					}
				} else {
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate(mainObject.get("homeX").getAsDouble(), mainObject.get("homeY").getAsDouble(),
								mainObject.get("homeZ").getAsDouble());
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation(mainObject.get("homeX").getAsDouble(),
									mainObject.get("homeY").getAsDouble(), mainObject.get("homeZ").getAsDouble(), _ent.rotationYaw,
									_ent.rotationPitch, Collections.emptySet());
						}
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Now you are at home."), (true));
					}
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
