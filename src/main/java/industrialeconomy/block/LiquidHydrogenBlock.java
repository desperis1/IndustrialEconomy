
package industrialeconomy.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.attributes.Attributes;

import net.minecraftforge.common.property.Properties;

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
						.luminosity(0).density(200).viscosity(1000).temperature(10)

						.rarity(Rarity.COMMON)).explosionResistance(100f)

								.tickRate(5).levelDecreasePerBlock(1).slopeFindDistance(4).bucket(() -> bucket).block(() -> block);

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

				LiquidHydrogenMobplayerCollidesBlockProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public void animateTick(BlockState blockstate, World world, BlockPos pos, Random random) {
				super.animateTick(blockstate, world, pos, random);
				PlayerEntity entity = Minecraft.getInstance().player;
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				LiquidHydrogenClientDisplayRandomTickProcedure.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			}

		}.setRegistryName("liquid_hydrogen"));

		elements.items.add(() -> new BucketItem(still,
				new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.COMMON))
						.setRegistryName("liquid_hydrogen_bucket"));
	}

}
