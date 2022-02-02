package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CentralStorageChestUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure CentralStorageChestUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure CentralStorageChestUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure CentralStorageChestUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure CentralStorageChestUpdateTick!");
			return;
		}

		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");

		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		File playerStorageConfig = new File("");
		double amount_of_items = 0;
		String itemName = "";
		if (0 < new Object() {
			public int getAmount(IWorld world, BlockPos pos, int sltid) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null) {
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).getCount());
					});
				}
				return _retval.get();
			}
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0))) {
			itemName = ((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
					}
					return _retval.get();
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getDisplayName().getString());
			amount_of_items = (new Object() {
				public int getAmount(IWorld world, BlockPos pos, int sltid) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).getCount());
						});
					}
					return _retval.get();
				}
			}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)));
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + ((new Object() {
				public String getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "owner")) + "_storage.json"));
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
					try {
						BufferedReader playerStorageConfigReader = new BufferedReader(new FileReader(playerStorageConfig));
						String stringiterator = "";
						while ((stringiterator = playerStorageConfigReader.readLine()) != null) {
							if (stringiterator.contains(itemName)) {
								mainObject.addProperty(itemName, (mainObject.get(itemName).getAsDouble() + amount_of_items));
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
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) (0);
										final int _amount = (int) amount_of_items;
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable) {
												ItemStack _stk = capability.getStackInSlot(_sltid).copy();
												_stk.shrink(_amount);
												((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
											}
										});
									}
								}
							}
						}
						playerStorageConfigReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
