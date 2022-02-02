package industrialeconomy.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class DiamondSteelpickaxeToolInHandTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure DiamondSteelpickaxeToolInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).diamondsteel_itembuffer == true) {
			if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.COBBLESTONE)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.COBBLESTONE);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.GRANITE)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.GRANITE);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.DIORITE)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.DIORITE);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.ANDESITE)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.ANDESITE);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.GRAVEL)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.GRAVEL);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Blocks.STONE)) : false) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.STONE);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_minnig_level + 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_minnig_level = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
