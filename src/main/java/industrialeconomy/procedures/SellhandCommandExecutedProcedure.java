package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class SellhandCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure SellhandCommandExecuted!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure SellhandCommandExecuted!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemforsell = ItemStack.EMPTY;
		double items_amount = 0;
		double price_from_server = 0;
		itemforsell = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		items_amount = ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount());
		if (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world,
				new BlockPos((int) IndustrialEconomyModVariables.WorldVariables.get(world).server_x,
						(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_y,
						(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_z),
				((itemforsell).getDisplayName().getString() + "_price")) > 0) {
			price_from_server = (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world,
					new BlockPos((int) IndustrialEconomyModVariables.WorldVariables.get(world).server_x,
							(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_y,
							(int) IndustrialEconomyModVariables.WorldVariables.get(world).server_z),
					((itemforsell).getDisplayName().getString() + "_price")));
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent(("You sell some "
								+ (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getDisplayName().getString())
								+ " for " + items_amount * price_from_server + "\u010F\u017C\u02DD")),
						(false));
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * price_from_server);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			if (BlockTags.getCollection().getTagByID(new ResourceLocation("industrial_economy:rocks_tag")).contains((new Object() {
				public BlockState toBlock(ItemStack _stk) {
					if (_stk.getItem() instanceof BlockItem) {
						return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
					}
					return Blocks.AIR.getDefaultState();
				}
			}.toBlock((itemforsell))).getBlock())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()) + " for " + items_amount * 1 + "\u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (BlockTags.getCollection().getTagByID(new ResourceLocation("industrial_economy:woodlogstag")).contains((new Object() {
				public BlockState toBlock(ItemStack _stk) {
					if (_stk.getItem() instanceof BlockItem) {
						return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
					}
					return Blocks.AIR.getDefaultState();
				}
			}.toBlock((itemforsell))).getBlock())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()) + " for " + items_amount * 4 + "\u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 4);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (BlockTags.getCollection().getTagByID(new ResourceLocation("industrial_economy:wood_materials")).contains((new Object() {
				public BlockState toBlock(ItemStack _stk) {
					if (_stk.getItem() instanceof BlockItem) {
						return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
					}
					return Blocks.AIR.getDefaultState();
				}
			}.toBlock((itemforsell))).getBlock())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()) + " for " + items_amount * 1 + "\u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 1);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("industrial_economy:junk_items")).contains((itemforsell).getItem())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()) + " for " + items_amount * 0.5 + "\u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 0.5);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (ItemTags.getCollection().getTagByID(new ResourceLocation("industrial_economy:foodforsale")).contains((itemforsell).getItem())) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell some " + (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getDisplayName().getString()) + " for " + items_amount * 2 + "\u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 2);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((itemforsell).getItem() == Items.DIAMOND) {
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 500);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell " + (itemforsell).getDisplayName().getString() + " for " + items_amount * 500 + " \u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = (itemforsell);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if ((itemforsell).getItem() == Items.EMERALD) {
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 850);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell " + (itemforsell).getDisplayName().getString() + " for " + items_amount * 850 + " \u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = (itemforsell);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if ((itemforsell).getItem() == Items.GOLD_INGOT) {
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 50);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(
							new StringTextComponent(
									("You sell " + (itemforsell).getDisplayName().getString() + " for " + items_amount * 50 + " \u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = (itemforsell);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if ((itemforsell).getItem() == Items.IRON_INGOT) {
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 2.5);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell " + (itemforsell).getDisplayName().getString() + " for " + items_amount * 2.5 + " \u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = (itemforsell);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if ((itemforsell).getItem() == Items.COAL) {
				{
					double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money + items_amount * 1.5);
					entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.player_money = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							("You sell " + (itemforsell).getDisplayName().getString() + " for " + items_amount * 1.5 + " \u010F\u017C\u02DD")),
							(false));
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = (itemforsell);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		}
	}
}
