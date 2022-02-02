package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CentralStorageBlockIsPlacedByProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure CentralStorageBlockIsPlacedBy!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure CentralStorageBlockIsPlacedBy!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure CentralStorageBlockIsPlacedBy!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure CentralStorageBlockIsPlacedBy!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CentralStorageBlockIsPlacedBy!");
			return;
		}

		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");

		String owner = "";
		File playerStorageConfig = new File("");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("owner", (entity.getDisplayName().getString()));

			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"),
				File.separator + (entity.getDisplayName().getString() + "_storage.json"));
		if (!playerStorageConfig.exists()) {
			mainObject.addProperty("placed", (true));
			mainObject.addProperty((new ItemStack(CateriumIngotItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CopperIngotItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CopperBlockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(RedstoneingotitemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CopperSheetItemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(Turbofuelitem3Item.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(TurbofuelitemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(Turbofuelitem2Item.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(GeneratorMK1Block.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ConstructorblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(DiamondsteelitemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(DiamondSteelblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(SteelingotItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(SteelBlockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(PresserblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(FoundyblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(Generatormk2Block.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ElectricFunanceinactiveBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(IronextractorBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CoalExtractorBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CateriumExtractorBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CopperExtractorBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ScrewItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(IronplateItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(HardenedcobbleBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(DiamondsteelplateItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(HeavyironblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(DiamondscrewitemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CateriumroditemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(SolarpanelblockinactiveBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(HeavyframeBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ModularframeBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(AssemblerblockBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CpuItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ConcreteItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(SandstoneextractorBlock.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(ControlUnitItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(EmptymoduleItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(IndustrialDiamondItem.block).getDisplayName().getString()), 1);
			mainObject.addProperty((new ItemStack(CateriumBlockBlock.block).getDisplayName().getString()), 1);
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
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Storage Sucessfuly loaded."), (false));
		}
	}

}
