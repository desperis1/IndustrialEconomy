package industrialeconomy.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class PlayerJoinWorldProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure PlayerJoinWorld!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure PlayerJoinWorld!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) IndustrialEconomyModVariables.WorldVariables.get(world).server_x,
					(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_y,
					(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putBoolean((((entity.getDisplayName().getString())) + "" + ("_") + "" + ("isOnline")), (true));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("Hello ") + "" + ((entity.getDisplayName().getString())))), (false));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(
					new StringTextComponent((("You have: ") + ""
							+ (Math.round(((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money)))
							+ "" + (" \u010F\u017C\u02DD"))),
					(false));
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) IndustrialEconomyModVariables.WorldVariables.get(world).server_x,
					(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_y,
					(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble((((entity.getDisplayName().getString())) + "" + ("_minerLevel")),
						((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new IndustrialEconomyModVariables.PlayerVariables())).miners_level));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		{
			double _setval = (double) Math.round(((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money));
			entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.player_money = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
