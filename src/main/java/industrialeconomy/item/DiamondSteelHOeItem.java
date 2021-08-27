
package industrialeconomy.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import industrialeconomy.procedures.DiamondSteelHOeRightClickedOnBlockProcedure;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class DiamondSteelHOeItem extends IndustrialEconomyModElements.ModElement {
	@ObjectHolder("industrial_economy:diamond_steel_h_oe")
	public static final Item block = null;
	public DiamondSteelHOeItem(IndustrialEconomyModElements instance) {
		super(instance, 444);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 85000;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 5;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(DiamondsteelplateItem.block));
			}
		}, 0, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
			@Override
			public ActionResultType onItemUse(ItemUseContext context) {
				ActionResultType retval = super.onItemUse(context);
				World world = context.getWorld();
				BlockPos pos = context.getPos();
				PlayerEntity entity = context.getPlayer();
				Direction direction = context.getFace();
				BlockState blockstate = world.getBlockState(pos);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				ItemStack itemstack = context.getItem();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("blockstate", blockstate);
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					DiamondSteelHOeRightClickedOnBlockProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("diamond_steel_h_oe"));
	}
}
