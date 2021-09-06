package industrialeconomy.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.Util;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.google.common.collect.ImmutableMap;

public class CaseBoxOpenBoxProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CaseBoxOpenBox!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure CaseBoxOpenBox!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double randomNumber = 0;
		double iterator = 0;
		{
			boolean _setval = (boolean) (true);
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.player_rolling_case = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (0))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (1))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (3))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (4))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (5))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (6))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (7))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = ItemInCaseBoxProcedure.executeProcedure(ImmutableMap.of());
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (8))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (0)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_0 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (1)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_1 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (2)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_2 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (3)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_3 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (4)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_4 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (3)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_5 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (6)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_6 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (7)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_7 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			ItemStack _setval = (new Object() {
				public ItemStack getItemStack(int sltid) {
					Entity _ent = entity;
					if (_ent instanceof ServerPlayerEntity) {
						Container _current = ((ServerPlayerEntity) _ent).openContainer;
						if (_current instanceof Supplier) {
							Object invobj = ((Supplier) _current).get();
							if (invobj instanceof Map) {
								return ((Slot) ((Map) invobj).get(sltid)).getStack();
							}
						}
					}
					return ItemStack.EMPTY;
				}
			}.getItemStack((int) (8)));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_8 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 0;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_0 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 1;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_1 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 2;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_2 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 3;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_3 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 4;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_4 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 5;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_5 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 6;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_6 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 7;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_7 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 8;
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.case_item_counter_8 = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		randomNumber = (double) Math.random();
		randomNumber = (double) new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new java.text.DecimalFormat("#.##").format(randomNumber)));
		randomNumber = (double) (randomNumber * 100);
		if ((randomNumber > 51)) {
			randomNumber = (double) (randomNumber - 50);
		}
		if ((randomNumber < 10)) {
			randomNumber = (double) (randomNumber + 11);
		}
		if (!world.isRemote()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().func_232641_a_(new StringTextComponent(("" + (randomNumber))), ChatType.SYSTEM, Util.DUMMY_UUID);
		}
		randomNumber = (double) Math.floor(randomNumber);
		for (int index0 = 0; index0 < (int) (randomNumber); index0++) {
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_0) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_0 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_1) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_1 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_2) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_3) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_3 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_4) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_4 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_5) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_5 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_6) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_6 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_7) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_7 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_8) + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.case_item_counter_8 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_0) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_0 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_1) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_1 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_2) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_2 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_3) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_3 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_4) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_4 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_5) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_5 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_6) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_6 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_7) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_7 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_8) == 9)) {
				{
					double _setval = (double) 0;
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.case_item_counter_8 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			iterator = (double) 0;
			for (int index1 = 0; index1 < (int) (500); index1++) {
				iterator = (double) 1;
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (0))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (1))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (2))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (3))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (4))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (5))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (6))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (7))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Container _current = ((ServerPlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						((Slot) ((Map) invobj).get((int) (8))).putStack(ItemStack.EMPTY);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_0);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_0)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_1);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_1)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_2);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_2)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_3);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_3)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_4);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_4)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_5);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_5)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_6);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_6)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_7);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_7)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_8);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).case_item_counter_8)))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = (new Object() {
						public ItemStack getItemStack(int sltid) {
							Entity _ent = entity;
							if (_ent instanceof ServerPlayerEntity) {
								Container _current = ((ServerPlayerEntity) _ent).openContainer;
								if (_current instanceof Supplier) {
									Object invobj = ((Supplier) _current).get();
									if (invobj instanceof Map) {
										return ((Slot) ((Map) invobj).get(sltid)).getStack();
									}
								}
							}
							return ItemStack.EMPTY;
						}
					}.getItemStack((int) (4)));
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (9))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		{
			boolean _setval = (boolean) (false);
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.player_rolling_case = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (!world.isRemote()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().func_232641_a_(new StringTextComponent(
						(("Player ") + "" + ((entity.getDisplayName().getString())) + "" + (" just unboxed: ") + "" + (((new Object() {
							public ItemStack getItemStack(int sltid) {
								Entity _ent = entity;
								if (_ent instanceof ServerPlayerEntity) {
									Container _current = ((ServerPlayerEntity) _ent).openContainer;
									if (_current instanceof Supplier) {
										Object invobj = ((Supplier) _current).get();
										if (invobj instanceof Map) {
											return ((Slot) ((Map) invobj).get(sltid)).getStack();
										}
									}
								}
								return ItemStack.EMPTY;
							}
						}.getItemStack((int) (9))).getDisplayName().getString())))), ChatType.SYSTEM, Util.DUMMY_UUID);
		}
	}
}
