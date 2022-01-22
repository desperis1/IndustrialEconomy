package industrialeconomy.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import java.util.Map;

import industrialeconomy.enchantment.BuildrangeEnchantment;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class BuildertoolRightClickedInAirProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure BuildertoolRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure BuildertoolRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure BuildertoolRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure BuildertoolRightClickedInAir!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure BuildertoolRightClickedInAir!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double result = 0;
		double a = 0;
		double b = 0;
		double c = 0;
		a = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(new java.text.DecimalFormat("##").format(Math.abs(Math
				.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_x)
				- Math.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_x))));
		b = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(new java.text.DecimalFormat("##").format(Math.abs(Math
				.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_y)
				- Math.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_y))));
		c = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(new java.text.DecimalFormat("##").format(Math.abs(Math
				.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_z)
				- Math.abs((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_z))));
		if (Math.round(a) == 0) {
			a = 1;
		}
		if (Math.round(b) == 0) {
			b = 1;
		}
		if (Math.round(c) == 0) {
			c = 1;
		}
		result = (a * b * c);
		if (EnchantmentHelper.getEnchantmentLevel(BuildrangeEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 0 && result <= 64) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("fill " + new java.text.DecimalFormat("##")
								.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_z)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_z)
								+ " " + "industrial_economy:builder_previewblock" + " replace air"));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Preview Activated! You can Clear it in build gun menu."), (false));
			}
		}
		if (EnchantmentHelper.getEnchantmentLevel(BuildrangeEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 1 && result <= 90) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("fill " + new java.text.DecimalFormat("##")
								.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_z)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_z)
								+ " " + "industrial_economy:builder_previewblock" + " replace air"));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Preview Activated! You can Clear it in build gun menu."), (false));
			}
		}
		if (EnchantmentHelper.getEnchantmentLevel(BuildrangeEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) == 2 && result <= 128) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("fill " + new java.text.DecimalFormat("##")
								.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos1_z)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_x)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_y)
								+ " "
								+ new java.text.DecimalFormat("##")
										.format((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new IndustrialEconomyModVariables.PlayerVariables())).builder_pos2_z)
								+ " " + "industrial_economy:builder_previewblock" + " replace air"));
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Preview Activated! You can Clear it in build gun menu."), (false));
			}
		}
		if (result > 129) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Cant Show Preview. Too many blocks selected."), (false));
			}
		}
	}
}
