
package industrialeconomy.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.item.Rarity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import industrialeconomy.procedures.LiquidHydrogenMobplayerCollidesBlockProcedure;
import industrialeconomy.procedures.LiquidHydrogenClientDisplayRandomTickProcedure;

import industrialeconomy.IndustrialEconomyModElements;

@IndustrialEconomyModElements.ModElement.Tag
public class LiquidHydrogenBlock extends IndustrialEconomyModElements.ModElement {
	@ObjectHolder("industrial_economy:liquid_hydrogen")
	public static final FlowingFluidBlock block = null;
	@ObjectHolder("industrial_economy:liquid_hydrogen_bucket")
	public static final Item bucket = null;
	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;
	private ForgeFlowingFluid.Properties fluidproperties = null;
	public LiquidHydrogenBlock(IndustrialEconomyModElements instance) {
		super(instance, 498);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegisterHandler());
	}
	private static class FluidRegisterHandler {
		@SubscribeEvent
		public void registerFluids(RegistryEvent.Register<Fluid> event) {
			event.getRegistry().register(still);
			event.getRegistry().register(flowing);
		}
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing,
				FluidAttributes
						.builder(new ResourceLocation("industrial_economy:blocks/liquid_hydrogen_still"),
								new ResourceLocation("industrial_economy:blocks/liquid_hydrogen_still"))
						.luminosity(0).density(200).viscosity(1000).temperature(10).rarity(Rarity.COMMON)).explosionResistance(100f).tickRate(5)
								.levelDecreasePerBlock(1).slopeFindDistance(4).bucket(() -> bucket).block(() -> block);
		still = (FlowingFluid) new ForgeFlowingFluid.Source(fluidproperties).setRegistryName("liquid_hydrogen");
		flowing = (FlowingFluid) new ForgeFlowingFluid.Flowing(fluidproperties).setRegistryName("liquid_hydrogen_flowing");
		elements.blocks.add(() -> new FlowingFluidBlock(still,
				Block.Properties.create(Material.WATER, MaterialColor.ICE).hardnessAndResistance(100f).setLightLevel(s -> 0)) {
			@Override
			public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
				return 1;
			}

			@Override
			public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
				return true;
			}

			@Override
			public void onEntityCollision(BlockState blockstate, World world, BlockPos pos, Entity entity) {
				super.onEntityCollision(blockstate, world, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					LiquidHydrogenMobplayerCollidesBlockProcedure.executeProcedure($_dependencies);
				}
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public void animateTick(BlockState blockstate, World world, BlockPos pos, Random random) {
				super.animateTick(blockstate, world, pos, random);
				PlayerEntity entity = Minecraft.getInstance().player;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					LiquidHydrogenClientDisplayRandomTickProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("liquid_hydrogen"));
		elements.items.add(() -> new BucketItem(still,
				new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.COMMON))
						.setRegistryName("liquid_hydrogen_bucket"));
	}
}
