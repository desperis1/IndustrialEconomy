package industrialeconomy.procedures;

import net.minecraftforge.eventbus.api.Event;

public class HazardShopCaseOnClickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure HazardShopCaseOnClick!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money >= 10) {
			{
				double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money - 10);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(CaseBox1Item.block);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
	}

}
