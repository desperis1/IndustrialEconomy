package industrialeconomy.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;

import industrialeconomy.block.MinerblockBlock;
import industrialeconomy.block.MarkblockBlock;
import industrialeconomy.block.LimestoneactiveBlock;
import industrialeconomy.block.IronOreNodeBlockBlock;
import industrialeconomy.block.CopperNodeBlock;
import industrialeconomy.block.CoalNodeBlock;
import industrialeconomy.block.CateriumNODEblockBlock;

import industrialeconomy.IndustrialEconomyMod;

public class NodeScannerRightClickedOnBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency itemstack for procedure NodeScannerRightClickedOnBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double mark_height = 0;
		if ((itemstack).getDamage() < 98) {
			if ((itemstack.getOrCreateTag().getString("NodeMaterial")).equals("Iron")) {
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				sx = (-50);
				found = (false);
				for (int index0 = 0; index0 < (int) (100); index0++) {
					sy = (-50);
					for (int index1 = 0; index1 < (int) (100); index1++) {
						sz = (-50);
						for (int index2 = 0; index2 < (int) (100); index2++) {
							if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == IronOreNodeBlockBlock.block
									&& !((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1), (int) (z + sz))))
											.getBlock() == MinerblockBlock.block)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Free Iron Node Found On:" + "  X:"
											+ Math.round(x + sx + 1) + "  Y:" + Math.round(y + sy) + "  Z:" + Math.round(z + sz))), (false));
								}
								mark_height = 3;
								for (int index3 = 0; index3 < (int) (25); index3++) {
									world.setBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1 + mark_height), (int) (z + sz)),
											MarkblockBlock.block.getDefaultState(), 3);
									mark_height = (mark_height + 1);
								}
								found = (true);
							}
							sz = (sz + 1);
						}
						sy = (sy + 1);
					}
					sx = (sx + 1);
				}
				if (found == false) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("No Free Iron Nodes Around"), (false));
					}
				}
			} else if ((itemstack.getOrCreateTag().getString("NodeMaterial")).equals("Caterium")) {
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				sx = (-50);
				found = (false);
				for (int index4 = 0; index4 < (int) (100); index4++) {
					sy = (-50);
					for (int index5 = 0; index5 < (int) (100); index5++) {
						sz = (-50);
						for (int index6 = 0; index6 < (int) (100); index6++) {
							if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == CateriumNODEblockBlock.block
									&& !((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1), (int) (z + sz))))
											.getBlock() == MinerblockBlock.block)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Free Caterium Node Found On:" + "  X:"
											+ Math.round(x + sx + 1) + "  Y:" + Math.round(y + sy) + "  Z:" + Math.round(z + sz))), (false));
								}
								mark_height = 3;
								for (int index7 = 0; index7 < (int) (25); index7++) {
									world.setBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1 + mark_height), (int) (z + sz)),
											MarkblockBlock.block.getDefaultState(), 3);
									mark_height = (mark_height + 1);
								}
								found = (true);
							}
							sz = (sz + 1);
						}
						sy = (sy + 1);
					}
					sx = (sx + 1);
				}
				if (found == false) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("No Free Caterium Nodes Around"), (false));
					}
				}
			} else if ((itemstack.getOrCreateTag().getString("NodeMaterial")).equals("Coal")) {
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				sx = (-50);
				found = (false);
				for (int index8 = 0; index8 < (int) (100); index8++) {
					sy = (-50);
					for (int index9 = 0; index9 < (int) (100); index9++) {
						sz = (-50);
						for (int index10 = 0; index10 < (int) (100); index10++) {
							if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == CoalNodeBlock.block
									&& !((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1), (int) (z + sz))))
											.getBlock() == MinerblockBlock.block)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Free Coal Node Found On:" + "  X:"
											+ Math.round(x + sx + 1) + "  Y:" + Math.round(y + sy) + "  Z:" + Math.round(z + sz))), (false));
								}
								mark_height = 3;
								for (int index11 = 0; index11 < (int) (25); index11++) {
									world.setBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1 + mark_height), (int) (z + sz)),
											MarkblockBlock.block.getDefaultState(), 3);
									mark_height = (mark_height + 1);
								}
								found = (true);
							}
							sz = (sz + 1);
						}
						sy = (sy + 1);
					}
					sx = (sx + 1);
				}
				if (found == false) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("No Free Coal Nodes Around"), (false));
					}
				}
			} else if ((itemstack.getOrCreateTag().getString("NodeMaterial")).equals("Copper")) {
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				sx = (-50);
				found = (false);
				for (int index12 = 0; index12 < (int) (100); index12++) {
					sy = (-50);
					for (int index13 = 0; index13 < (int) (100); index13++) {
						sz = (-50);
						for (int index14 = 0; index14 < (int) (100); index14++) {
							if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == CopperNodeBlock.block
									&& !((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1), (int) (z + sz))))
											.getBlock() == MinerblockBlock.block)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Free Copper Node Found On:" + "  X:"
											+ Math.round(x + sx + 1) + "  Y:" + Math.round(y + sy) + "  Z:" + Math.round(z + sz))), (false));
								}
								mark_height = 3;
								for (int index15 = 0; index15 < (int) (25); index15++) {
									world.setBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1 + mark_height), (int) (z + sz)),
											MarkblockBlock.block.getDefaultState(), 3);
									mark_height = (mark_height + 1);
								}
								found = (true);
							}
							sz = (sz + 1);
						}
						sy = (sy + 1);
					}
					sx = (sx + 1);
				}
				if (found == false) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("No Free Copper Nodes Around"), (false));
					}
				}
			} else if ((itemstack.getOrCreateTag().getString("NodeMaterial")).equals("SandStone")) {
				{
					ItemStack _ist = itemstack;
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				sx = (-50);
				found = (false);
				for (int index16 = 0; index16 < (int) (100); index16++) {
					sy = (-50);
					for (int index17 = 0; index17 < (int) (100); index17++) {
						sz = (-50);
						for (int index18 = 0; index18 < (int) (100); index18++) {
							if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == LimestoneactiveBlock.block
									&& !((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1), (int) (z + sz))))
											.getBlock() == MinerblockBlock.block)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("Free SandStone Node Found On:" + "  X:"
											+ Math.round(x + sx + 1) + "  Y:" + Math.round(y + sy) + "  Z:" + Math.round(z + sz))), (false));
								}
								mark_height = 3;
								for (int index19 = 0; index19 < (int) (25); index19++) {
									world.setBlockState(new BlockPos((int) (x + sx), (int) (y + sy + 1 + mark_height), (int) (z + sz)),
											MarkblockBlock.block.getDefaultState(), 3);
									mark_height = (mark_height + 1);
								}
								found = (true);
							}
							sz = (sz + 1);
						}
						sy = (sy + 1);
					}
					sx = (sx + 1);
				}
				if (found == false) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("No Free SandStone Nodes Around"), (false));
					}
				}
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Low Battery!"), (false));
			}
		}
	}
}
