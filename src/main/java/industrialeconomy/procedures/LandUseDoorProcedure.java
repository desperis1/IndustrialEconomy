package industrialeconomy.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.google.common.collect.ImmutableMap;

public class LandUseDoorProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
			PlayerEntity entity = event.getPlayer();
			if (event.getHand() != entity.getActiveHand()) {
				return;
			}
			double i = event.getPos().getX();
			double j = event.getPos().getY();
			double k = event.getPos().getZ();
			IWorld world = event.getWorld();
			BlockState state = world.getBlockState(event.getPos());
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("direction", event.getFace());
			dependencies.put("blockstate", state);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure LandUseDoor!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure LandUseDoor!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure LandUseDoor!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure LandUseDoor!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure LandUseDoor!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double grid_X = 0;
		double grid_Z = 0;
		String player_name = "";
		if ((((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).admin_editor) == (false))) {
			if (((entity instanceof PlayerEntity) || (entity instanceof ServerPlayerEntity))) {
				grid_X = (double) Math.floor((x / 20));
				grid_Z = (double) Math.floor((z / 20));
				player_name = (String) (entity.getDisplayName().getString());
				if (((BlockTags.getCollection().getTagByID(new ResourceLocation(("minecraft:doors").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))
						|| (BlockTags.getCollection().getTagByID(new ResourceLocation(("minecraft:trapdoor").toLowerCase(java.util.Locale.ENGLISH)))
								.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())))) {
					if ((!(IndustrialEconomyModVariables.WorldVariables.get(world).lands
							.contains(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (",")))))) {
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont own this land."), (true));
						}
						if (dependencies.get("event") != null) {
							Object _obj = dependencies.get("event");
							if (_obj instanceof Event) {
								Event _evt = (Event) _obj;
								if (_evt.isCancelable())
									_evt.setCanceled(true);
							}
						}
					} else {
						return (true);
					}
				}
				if (((true) == DisallowedToOpenProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world)))) {
					grid_X = (double) Math.floor((x / 20));
					grid_Z = (double) Math.floor((z / 20));
					if (((IndustrialEconomyModVariables.WorldVariables.get(world).is_city
							.contains(((":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))
							&& (!(IndustrialEconomyModVariables.WorldVariables.get(world).lands
									.contains(((player_name) + "" + (":") + "" + (grid_X) + "" + (":") + "" + (grid_Z) + "" + (","))))))) {
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You dont own this land"), (true));
						}
						for (int index0 = 0; index0 < (int) (40); index0++) {
							new Object() {
								private int ticks = 0;
								private float waitTicks;
								private IWorld world;
								public void start(IWorld world, int waitTicks) {
									this.waitTicks = waitTicks;
									MinecraftForge.EVENT_BUS.register(this);
									this.world = world;
								}

								@SubscribeEvent
								public void tick(TickEvent.ServerTickEvent event) {
									if (event.phase == TickEvent.Phase.END) {
										this.ticks += 1;
										if (this.ticks >= this.waitTicks)
											run();
									}
								}

								private void run() {
									if (entity instanceof PlayerEntity)
										((PlayerEntity) entity).closeScreen();
									MinecraftForge.EVENT_BUS.unregister(this);
								}
							}.start(world, (int) 1);
						}
					} else {
						return (true);
					}
				}
				return (false);
			}
		}
		return (false);
	}
}
