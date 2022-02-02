package industrialeconomy.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import industrialeconomy.item.IronplateItem;
import industrialeconomy.item.HighspeedconectoritemItem;
import industrialeconomy.item.EmptymoduleItem;
import industrialeconomy.item.DiamondsteelplateItem;
import industrialeconomy.item.DiamondscrewitemItem;
import industrialeconomy.item.CpuItem;
import industrialeconomy.item.CopperSheetItemItem;
import industrialeconomy.item.ControlUnitItem;
import industrialeconomy.item.CircuitBoarditemItem;
import industrialeconomy.item.CateriumwireitemItem;
import industrialeconomy.item.AiLimiteritemItem;

import industrialeconomy.block.SteelBlockBlock;
import industrialeconomy.block.HeavyironblockBlock;
import industrialeconomy.block.DiamondSteelblockBlock;
import industrialeconomy.block.CateriumBlockBlock;

import industrialeconomy.IndustrialEconomyMod;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class ManufacturerUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure ManufacturerUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure ManufacturerUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure ManufacturerUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure ManufacturerUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		com.google.gson.JsonObject mainObject = new com.google.gson.JsonObject();
		String owner = "";
		File playerStorageConfig = new File("");
		File playerConfig = new File("");
		double Energy = 0;
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
				Energy = mainObject.get("Energy").getAsDouble();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("HighSpeedConnector")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(CopperSheetItemItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() > 3
							&& mainObject.get((new ItemStack(CateriumBlockBlock.block).getDisplayName().getString())).getAsDouble() > 1
							&& mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() > 1
							&& mainObject.get((new ItemStack(SteelBlockBlock.block).getDisplayName().getString())).getAsDouble() > 2
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64
									&& HighspeedconectoritemItem.block == (new Object() {
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
									}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(CopperSheetItemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CopperSheetItemItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() - 3));
						mainObject.addProperty((new ItemStack(CateriumBlockBlock.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CateriumBlockBlock.block).getDisplayName().getString())).getAsDouble() - 1));
						mainObject.addProperty((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() - 1));
						mainObject.addProperty((new ItemStack(SteelBlockBlock.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(SteelBlockBlock.block).getDisplayName().getString())).getAsDouble() - 2));
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
								final ItemStack _setstack = new ItemStack(HighspeedconectoritemItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("DiamondSteelPlate")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(DiamondscrewitemItem.block).getDisplayName().getString())).getAsDouble() > 4
							&& mainObject.get((new ItemStack(IronplateItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(DiamondSteelblockBlock.block).getDisplayName().getString())).getAsDouble() > 1
							&& mainObject.get((new ItemStack(HeavyironblockBlock.block).getDisplayName().getString())).getAsDouble() > 2
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64
									&& DiamondsteelplateItem.block == (new Object() {
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
									}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(DiamondscrewitemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(DiamondscrewitemItem.block).getDisplayName().getString())).getAsDouble() - 4));
						mainObject.addProperty((new ItemStack(IronplateItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(IronplateItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(DiamondSteelblockBlock.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(DiamondSteelblockBlock.block).getDisplayName().getString())).getAsDouble() - 1));
						mainObject.addProperty((new ItemStack(HeavyironblockBlock.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(HeavyironblockBlock.block).getDisplayName().getString())).getAsDouble() - 2));
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
								final ItemStack _setstack = new ItemStack(DiamondsteelplateItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("AiLimiter")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() > 4
							&& mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(SteelBlockBlock.block).getDisplayName().getString())).getAsDouble() > 1
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64 && AiLimiteritemItem.block == (new Object() {
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
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() - 4));
						mainObject.addProperty((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(SteelBlockBlock.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(SteelBlockBlock.block).getDisplayName().getString())).getAsDouble() - 1));
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
								final ItemStack _setstack = new ItemStack(AiLimiteritemItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("ControlUnit")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString())).getAsDouble() > 2
							&& mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() > 3
							&& mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() > 1
							&& mainObject.get((new ItemStack(DiamondsteelplateItem.block).getDisplayName().getString())).getAsDouble() > 1
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64 && ControlUnitItem.block == (new Object() {
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
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString())).getAsDouble() - 2));
						mainObject.addProperty((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CateriumwireitemItem.block).getDisplayName().getString())).getAsDouble() - 3));
						mainObject.addProperty((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CircuitBoarditemItem.block).getDisplayName().getString())).getAsDouble() - 1));
						mainObject.addProperty((new ItemStack(DiamondsteelplateItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(DiamondsteelplateItem.block).getDisplayName().getString())).getAsDouble() - 1));
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
								final ItemStack _setstack = new ItemStack(ControlUnitItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("CPU")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() > 4
							&& mainObject.get((new ItemStack(ControlUnitItem.block).getDisplayName().getString())).getAsDouble() > 4
							&& mainObject.get((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString())).getAsDouble() > 1
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64 && CpuItem.block == (new Object() {
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
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(HighspeedconectoritemItem.block).getDisplayName().getString())).getAsDouble() - 4));
						mainObject.addProperty((new ItemStack(ControlUnitItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(ControlUnitItem.block).getDisplayName().getString())).getAsDouble() - 4));
						mainObject.addProperty((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(AiLimiteritemItem.block).getDisplayName().getString())).getAsDouble() - 1));
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
								final ItemStack _setstack = new ItemStack(CpuItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
		if (Energy > 2500 && (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Recipe")).equals("EmptyModule")) {
			playerStorageConfig = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + (owner + "_storage.json"));
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
					if (mainObject.get((new ItemStack(CpuItem.block).getDisplayName().getString())).getAsDouble() > 1
							&& mainObject.get((new ItemStack(ControlUnitItem.block).getDisplayName().getString())).getAsDouble() > 7
							&& (new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) == 0 || new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) < 64 && CpuItem.block == (new Object() {
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
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (true));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						mainObject.addProperty((new ItemStack(CpuItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(CpuItem.block).getDisplayName().getString())).getAsDouble() - 1));
						mainObject.addProperty((new ItemStack(ControlUnitItem.block).getDisplayName().getString()),
								(mainObject.get((new ItemStack(ControlUnitItem.block).getDisplayName().getString())).getAsDouble() - 7));
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
								final ItemStack _setstack = new ItemStack(EmptymoduleItem.block);
								_setstack.setCount((int) (new Object() {
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
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0)) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Working", (false));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Working")) == true) {
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
						mainObject.addProperty("Energy", (mainObject.get("Energy").getAsDouble() - 2500));

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
	}
}
