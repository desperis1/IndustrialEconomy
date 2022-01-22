package industrialeconomy.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class WorldGridCheckProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
			Entity entity = event.getEntity();
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", event.getPos().getX());
			dependencies.put("y", event.getPos().getY());
			dependencies.put("z", event.getPos().getZ());
			dependencies.put("px", entity.getPosX());
			dependencies.put("py", entity.getPosY());
			dependencies.put("pz", entity.getPosZ());
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("blockstate", event.getState());
			dependencies.put("placedagainst", event.getPlacedAgainst());
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure WorldGridCheck!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure WorldGridCheck!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure WorldGridCheck!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure WorldGridCheck!");
			return false;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure WorldGridCheck!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		String player_name = "";
		double grid_X = 0;
		double grid_Z = 0;
		if ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).admin_editor == false) {
			if (entity instanceof PlayerEntity || entity instanceof ServerPlayerEntity) {
				player_name = (entity.getDisplayName().getString());
				grid_X = Math.floor(x / 20);
				grid_Z = Math.floor(z / 20);
				if (!IndustrialEconomyModVariables.WorldVariables.get(world).lands.contains(player_name + ":" + grid_X + ":" + grid_Z + ",")
						&& IndustrialEconomyModVariables.WorldVariables.get(world).is_city.contains(":" + grid_X + ":" + grid_Z + ",")) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont own this Land"), (true));
					}
					if (new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
							}
							return false;
						}
					}.checkGamemode(entity) || new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.ADVENTURE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.ADVENTURE;
							}
							return false;
						}
					}.checkGamemode(entity)) {
						if (world instanceof World) {
							Block.spawnDrops(world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z)))),
									(World) world, new BlockPos((int) x, (int) y, (int) z));
							world.destroyBlock(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z))), false);
						}
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
					}
				}
				if (AllowedBlocksInWorldProcedure
						.executeProcedure(Stream
								.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
										new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
								.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)) == true
						&& !IndustrialEconomyModVariables.WorldVariables.get(world).is_city.contains(":" + grid_X + ":" + grid_Z + ",")) {
					return true;
				} else if (IndustrialEconomyModVariables.WorldVariables.get(world).lands.contains(player_name + ":" + grid_X + ":" + grid_Z + ",")
						&& IndustrialEconomyModVariables.WorldVariables.get(world).is_city.contains(":" + grid_X + ":" + grid_Z + ",")) {
					return true;
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont own this Land"), (true));
					}
					if (new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
							}
							return false;
						}
					}.checkGamemode(entity) || new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.ADVENTURE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.ADVENTURE;
							}
							return false;
						}
					}.checkGamemode(entity)) {
						if (world instanceof World) {
							Block.spawnDrops(world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z)))),
									(World) world, new BlockPos((int) x, (int) y, (int) z));
							world.destroyBlock(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z))), false);
						}
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
					}
				}
			}
			return false;
		}
		return false;
	}
}
