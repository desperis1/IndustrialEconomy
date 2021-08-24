package industrialeconomy.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.Map;

import industrialeconomy.IndustrialEconomyMod;

public class DiamondsteelaxeBlockDestroyedWithToolProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure DiamondsteelaxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure DiamondsteelaxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure DiamondsteelaxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure DiamondsteelaxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure DiamondsteelaxeBlockDestroyedWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getBoolean("TreeChopper")) == (true))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OAK_LOG)) {
				sx = (double) (-2);
				found = (boolean) (false);
				for (int index0 = 0; index0 < (int) (5); index0++) {
					sy = (double) (-1);
					for (int index1 = 0; index1 < (int) (15); index1++) {
						sz = (double) (-2);
						for (int index2 = 0; index2 < (int) (5); index2++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)))).getBlock() == Blocks.OAK_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BIRCH_LOG)) {
				sx = (double) (-1);
				found = (boolean) (false);
				for (int index3 = 0; index3 < (int) (3); index3++) {
					sy = (double) (-1);
					for (int index4 = 0; index4 < (int) (15); index4++) {
						sz = (double) (-1);
						for (int index5 = 0; index5 < (int) (3); index5++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == Blocks.BIRCH_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.JUNGLE_LOG)) {
				sx = (double) (-1);
				found = (boolean) (false);
				for (int index6 = 0; index6 < (int) (3); index6++) {
					sy = (double) (-1);
					for (int index7 = 0; index7 < (int) (30); index7++) {
						sz = (double) (-1);
						for (int index8 = 0; index8 < (int) (3); index8++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == Blocks.JUNGLE_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.SPRUCE_LOG)) {
				sx = (double) (-1);
				found = (boolean) (false);
				for (int index9 = 0; index9 < (int) (3); index9++) {
					sy = (double) (-1);
					for (int index10 = 0; index10 < (int) (15); index10++) {
						sz = (double) (-1);
						for (int index11 = 0; index11 < (int) (3); index11++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == Blocks.SPRUCE_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.ACACIA_LOG)) {
				sx = (double) (-1);
				found = (boolean) (false);
				for (int index12 = 0; index12 < (int) (3); index12++) {
					sy = (double) (-1);
					for (int index13 = 0; index13 < (int) (15); index13++) {
						sz = (double) (-1);
						for (int index14 = 0; index14 < (int) (3); index14++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == Blocks.ACACIA_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DARK_OAK_LOG)) {
				sx = (double) (-1);
				found = (boolean) (false);
				for (int index15 = 0; index15 < (int) (3); index15++) {
					sy = (double) (-1);
					for (int index16 = 0; index16 < (int) (15); index16++) {
						sz = (double) (-1);
						for (int index17 = 0; index17 < (int) (3); index17++) {
							if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))))
									.getBlock() == Blocks.DARK_OAK_LOG)) {
								if (world instanceof World) {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz))), (World) world,
											new BlockPos((int) x, (int) y, (int) z));
									world.destroyBlock(new BlockPos((int) (x + sx), (int) (y + sy), (int) (z + sz)), false);
								}
								{
									ItemStack _ist = ((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getHeldItemMainhand()
											: ItemStack.EMPTY);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
							sz = (double) (sz + 1);
						}
						sy = (double) (sy + 1);
					}
					sx = (double) (sx + 1);
				}
			}
		}
	}
}
