package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class CaseBoxOpenCaseButtonProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure CaseBoxOpenCaseButton!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure CaseBoxOpenCaseButton!");
			return;
		}

		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");

		ItemStack winning = ItemStack.EMPTY;
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CaseBox1Item.block)) : false)
				&& ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CaseKeyItem.block)) : false)) {
			{
				boolean _setval = (true);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_rolling_case = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(CaseKeyItem.block);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(CaseBox1Item.block);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			winning =

					ItemInCaseBoxProcedure.executeProcedure(Collections.EMPTY_MAP);
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = (winning);
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) (0))).putStack(_setstack);
						_current.detectAndSendChanges();
					}
				}
			}
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (winning));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList()
							.func_232641_a_(new StringTextComponent(
									("Player " + entity.getDisplayName().getString() + " just unboxed: " + (winning).getDisplayName().getString())),
									ChatType.SYSTEM, Util.DUMMY_UUID);
			}
			{
				boolean _setval = (false);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_rolling_case = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}

}
