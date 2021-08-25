package industrialeconomy.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
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
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure SellhandCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double items_amount = 0;
		ItemStack itemforsell = ItemStack.EMPTY;
		itemforsell = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == Items.DIAMOND)) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Items.DIAMOND);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + 100);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent(
								(("You sell ") + "" + ((new ItemStack(Items.DIAMOND).getDisplayName().getString())) + "" + ("for 100\u20AC"))),
						(false));
			}
		}
		if ((BlockTags.getCollection().getTagByID(new ResourceLocation(("industrial_economy:rocks_tag").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((new Object() {
					public BlockState toBlock(ItemStack _stk) {
						if (_stk.getItem() instanceof BlockItem) {
							return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
						}
						return Blocks.AIR.getDefaultState();
					}
				}.toBlock((itemforsell))).getBlock()))) {
			items_amount = (double) ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
					.getCount());
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(
								new StringTextComponent((("You sell some ") + ""
										+ ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getDisplayName().getString()))
										+ "" + (" for ") + "" + ((items_amount * 1)) + "" + ("\u20AC"))),
								(false));
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + (items_amount * 1));
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((BlockTags.getCollection().getTagByID(new ResourceLocation(("industrial_economy:woodlogstag").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((new Object() {
					public BlockState toBlock(ItemStack _stk) {
						if (_stk.getItem() instanceof BlockItem) {
							return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
						}
						return Blocks.AIR.getDefaultState();
					}
				}.toBlock((itemforsell))).getBlock()))) {
			items_amount = (double) ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
					.getCount());
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(
								new StringTextComponent((("You sell some ") + ""
										+ ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getDisplayName().getString()))
										+ "" + (" for ") + "" + ((items_amount * 4)) + "" + ("\u20AC"))),
								(false));
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + (items_amount * 4));
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((BlockTags.getCollection().getTagByID(new ResourceLocation(("industrial_economy:wood_materials").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((new Object() {
					public BlockState toBlock(ItemStack _stk) {
						if (_stk.getItem() instanceof BlockItem) {
							return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
						}
						return Blocks.AIR.getDefaultState();
					}
				}.toBlock((itemforsell))).getBlock()))) {
			items_amount = (double) ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
					.getCount());
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(
								new StringTextComponent((("You sell some ") + ""
										+ ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getDisplayName().getString()))
										+ "" + (" for ") + "" + ((items_amount * 1)) + "" + ("\u20AC"))),
								(false));
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + (items_amount * 1));
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((ItemTags.getCollection().getTagByID(new ResourceLocation(("industrial_economy:junk_items").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((itemforsell).getItem()))) {
			items_amount = (double) ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
					.getCount());
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity)
						.sendStatusMessage(
								new StringTextComponent((("You sell some ") + ""
										+ ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getDisplayName().getString()))
										+ "" + (" for ") + "" + ((items_amount * 0.5)) + "" + ("\u20AC"))),
								(false));
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) items_amount,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			{
				double _setval = (double) (((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + (items_amount * 0.5));
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.player_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
