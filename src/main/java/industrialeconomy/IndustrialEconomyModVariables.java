package industrialeconomy;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import java.io.File;

public class IndustrialEconomyModVariables {
	public IndustrialEconomyModVariables(IndustrialEconomyModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	public static File prices = new File("");

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "industrial_economy_worldvars";
		public String lands = "\"\"";
		public String is_city = "\"\"";
		public double server_x = 0;
		public double server_y = 0;
		public double server_z = 0;

		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			lands = nbt.getString("lands");
			is_city = nbt.getString("is_city");
			server_x = nbt.getDouble("server_x");
			server_y = nbt.getDouble("server_y");
			server_z = nbt.getDouble("server_z");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putString("lands", lands);
			nbt.putString("is_city", is_city);
			nbt.putDouble("server_x", server_x);
			nbt.putDouble("server_y", server_y);
			nbt.putDouble("server_z", server_z);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(((World) world)::getDimensionKey),
						new WorldSavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(IWorld world) {
			if (world instanceof ServerWorld) {
				return ((ServerWorld) world).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "industrial_economy_mapvars";

		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(IWorld world) {
			if (world instanceof IServerWorld) {
				return ((IServerWorld) world).getWorld().getServer().getWorld(World.OVERWORLD).getSavedData().getOrCreate(MapVariables::new,
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;

		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;

	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("industrial_economy", "player_variables"), new PlayerVariablesProvider());
	}

	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("hub_caterium_save", instance.hub_caterium_save);
			nbt.putBoolean("DSA_NightVision", instance.DSA_NightVision);
			nbt.putBoolean("DSA_Jetpack", instance.DSA_Jetpack);
			nbt.putBoolean("DSA_Speed", instance.DSA_Speed);
			nbt.putBoolean("DSA_SlowFalling", instance.DSA_SlowFalling);
			nbt.putBoolean("DSA_DolphinGrace", instance.DSA_DolphinGrace);
			nbt.putBoolean("DSA_PlasmaShoot", instance.DSA_PlasmaShoot);
			nbt.putBoolean("DSA_WaterBreathe", instance.DSA_WaterBreathe);
			nbt.putBoolean("placed_hub", instance.placed_hub);
			nbt.putDouble("hub_energy_save", instance.hub_energy_save);
			nbt.putDouble("hub_z", instance.hub_z);
			nbt.putDouble("hub_x", instance.hub_x);
			nbt.putDouble("hub_y", instance.hub_y);
			nbt.putDouble("player_back_y", instance.player_back_y);
			nbt.putDouble("player_home_z", instance.player_home_z);
			nbt.putDouble("player_back_x", instance.player_back_x);
			nbt.putDouble("player_home_y", instance.player_home_y);
			nbt.putDouble("hub_copper_save", instance.hub_copper_save);
			nbt.putDouble("player_home_x", instance.player_home_x);
			nbt.putDouble("player_back_z", instance.player_back_z);
			nbt.putDouble("hub_coal_save", instance.hub_coal_save);
			nbt.putDouble("hub_iron_save", instance.hub_iron_save);
			nbt.putBoolean("diamondsteel_itembuffer", instance.diamondsteel_itembuffer);
			nbt.putDouble("player_minnig_level", instance.player_minnig_level);
			nbt.putDouble("miners_level", instance.miners_level);
			nbt.putDouble("hub_sandstone_save", instance.hub_sandstone_save);
			nbt.put("buildgun_material", instance.buildgun_material.write(new CompoundNBT()));
			nbt.putDouble("build_gun_pos_num", instance.build_gun_pos_num);
			nbt.putDouble("builder_pos2_z", instance.builder_pos2_z);
			nbt.putDouble("builder_pos1_z", instance.builder_pos1_z);
			nbt.putDouble("builder_pos2_y", instance.builder_pos2_y);
			nbt.putDouble("builder_pos1_y", instance.builder_pos1_y);
			nbt.putDouble("builder_pos2_x", instance.builder_pos2_x);
			nbt.putDouble("builder_pos1_x", instance.builder_pos1_x);
			nbt.putDouble("DSA_energy", instance.DSA_energy);
			nbt.putDouble("DSA_PlasmaShoot_Cooldown", instance.DSA_PlasmaShoot_Cooldown);
			nbt.putString("player_home_dimension", instance.player_home_dimension);
			nbt.putDouble("player_number_of_land", instance.player_number_of_land);
			nbt.putDouble("player_money", instance.player_money);
			nbt.putString("players_city_name", instance.players_city_name);
			nbt.putString("belong_to_city", instance.belong_to_city);
			nbt.putBoolean("admin_editor", instance.admin_editor);
			nbt.putString("player_back_dimension", instance.player_back_dimension);
			nbt.putDouble("energy_required_for_next_level", instance.energy_required_for_next_level);
			nbt.putDouble("hub_energy_for_upgrade_save", instance.hub_energy_for_upgrade_save);
			nbt.putDouble("case_item_counter_8", instance.case_item_counter_8);
			nbt.putBoolean("player_rolling_case", instance.player_rolling_case);
			nbt.putDouble("case_item_counter_1", instance.case_item_counter_1);
			nbt.putDouble("case_item_counter_0", instance.case_item_counter_0);
			nbt.putDouble("case_item_counter_3", instance.case_item_counter_3);
			nbt.putDouble("case_item_counter_2", instance.case_item_counter_2);
			nbt.putDouble("case_item_counter_5", instance.case_item_counter_5);
			nbt.putDouble("case_item_counter_4", instance.case_item_counter_4);
			nbt.putDouble("case_item_counter_7", instance.case_item_counter_7);
			nbt.putDouble("case_item_counter_6", instance.case_item_counter_6);
			nbt.put("case_item_5", instance.case_item_5.write(new CompoundNBT()));
			nbt.put("case_item_6", instance.case_item_6.write(new CompoundNBT()));
			nbt.put("case_item_7", instance.case_item_7.write(new CompoundNBT()));
			nbt.put("case_item_8", instance.case_item_8.write(new CompoundNBT()));
			nbt.put("case_item_0", instance.case_item_0.write(new CompoundNBT()));
			nbt.put("case_item_1", instance.case_item_1.write(new CompoundNBT()));
			nbt.putBoolean("is_player_in_dimension1", instance.is_player_in_dimension1);
			nbt.put("case_item_2", instance.case_item_2.write(new CompoundNBT()));
			nbt.put("case_item_3", instance.case_item_3.write(new CompoundNBT()));
			nbt.put("case_item_4", instance.case_item_4.write(new CompoundNBT()));
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.hub_caterium_save = nbt.getDouble("hub_caterium_save");
			instance.DSA_NightVision = nbt.getBoolean("DSA_NightVision");
			instance.DSA_Jetpack = nbt.getBoolean("DSA_Jetpack");
			instance.DSA_Speed = nbt.getBoolean("DSA_Speed");
			instance.DSA_SlowFalling = nbt.getBoolean("DSA_SlowFalling");
			instance.DSA_DolphinGrace = nbt.getBoolean("DSA_DolphinGrace");
			instance.DSA_PlasmaShoot = nbt.getBoolean("DSA_PlasmaShoot");
			instance.DSA_WaterBreathe = nbt.getBoolean("DSA_WaterBreathe");
			instance.placed_hub = nbt.getBoolean("placed_hub");
			instance.hub_energy_save = nbt.getDouble("hub_energy_save");
			instance.hub_z = nbt.getDouble("hub_z");
			instance.hub_x = nbt.getDouble("hub_x");
			instance.hub_y = nbt.getDouble("hub_y");
			instance.player_back_y = nbt.getDouble("player_back_y");
			instance.player_home_z = nbt.getDouble("player_home_z");
			instance.player_back_x = nbt.getDouble("player_back_x");
			instance.player_home_y = nbt.getDouble("player_home_y");
			instance.hub_copper_save = nbt.getDouble("hub_copper_save");
			instance.player_home_x = nbt.getDouble("player_home_x");
			instance.player_back_z = nbt.getDouble("player_back_z");
			instance.hub_coal_save = nbt.getDouble("hub_coal_save");
			instance.hub_iron_save = nbt.getDouble("hub_iron_save");
			instance.diamondsteel_itembuffer = nbt.getBoolean("diamondsteel_itembuffer");
			instance.player_minnig_level = nbt.getDouble("player_minnig_level");
			instance.miners_level = nbt.getDouble("miners_level");
			instance.hub_sandstone_save = nbt.getDouble("hub_sandstone_save");
			instance.buildgun_material = ItemStack.read(nbt.getCompound("buildgun_material"));
			instance.build_gun_pos_num = nbt.getDouble("build_gun_pos_num");
			instance.builder_pos2_z = nbt.getDouble("builder_pos2_z");
			instance.builder_pos1_z = nbt.getDouble("builder_pos1_z");
			instance.builder_pos2_y = nbt.getDouble("builder_pos2_y");
			instance.builder_pos1_y = nbt.getDouble("builder_pos1_y");
			instance.builder_pos2_x = nbt.getDouble("builder_pos2_x");
			instance.builder_pos1_x = nbt.getDouble("builder_pos1_x");
			instance.DSA_energy = nbt.getDouble("DSA_energy");
			instance.DSA_PlasmaShoot_Cooldown = nbt.getDouble("DSA_PlasmaShoot_Cooldown");
			instance.player_home_dimension = nbt.getString("player_home_dimension");
			instance.player_number_of_land = nbt.getDouble("player_number_of_land");
			instance.player_money = nbt.getDouble("player_money");
			instance.players_city_name = nbt.getString("players_city_name");
			instance.belong_to_city = nbt.getString("belong_to_city");
			instance.admin_editor = nbt.getBoolean("admin_editor");
			instance.player_back_dimension = nbt.getString("player_back_dimension");
			instance.energy_required_for_next_level = nbt.getDouble("energy_required_for_next_level");
			instance.hub_energy_for_upgrade_save = nbt.getDouble("hub_energy_for_upgrade_save");
			instance.case_item_counter_8 = nbt.getDouble("case_item_counter_8");
			instance.player_rolling_case = nbt.getBoolean("player_rolling_case");
			instance.case_item_counter_1 = nbt.getDouble("case_item_counter_1");
			instance.case_item_counter_0 = nbt.getDouble("case_item_counter_0");
			instance.case_item_counter_3 = nbt.getDouble("case_item_counter_3");
			instance.case_item_counter_2 = nbt.getDouble("case_item_counter_2");
			instance.case_item_counter_5 = nbt.getDouble("case_item_counter_5");
			instance.case_item_counter_4 = nbt.getDouble("case_item_counter_4");
			instance.case_item_counter_7 = nbt.getDouble("case_item_counter_7");
			instance.case_item_counter_6 = nbt.getDouble("case_item_counter_6");
			instance.case_item_5 = ItemStack.read(nbt.getCompound("case_item_5"));
			instance.case_item_6 = ItemStack.read(nbt.getCompound("case_item_6"));
			instance.case_item_7 = ItemStack.read(nbt.getCompound("case_item_7"));
			instance.case_item_8 = ItemStack.read(nbt.getCompound("case_item_8"));
			instance.case_item_0 = ItemStack.read(nbt.getCompound("case_item_0"));
			instance.case_item_1 = ItemStack.read(nbt.getCompound("case_item_1"));
			instance.is_player_in_dimension1 = nbt.getBoolean("is_player_in_dimension1");
			instance.case_item_2 = ItemStack.read(nbt.getCompound("case_item_2"));
			instance.case_item_3 = ItemStack.read(nbt.getCompound("case_item_3"));
			instance.case_item_4 = ItemStack.read(nbt.getCompound("case_item_4"));
		}
	}

	public static class PlayerVariables {
		public double hub_caterium_save = 0;
		public boolean DSA_NightVision = false;
		public boolean DSA_Jetpack = false;
		public boolean DSA_Speed = false;
		public boolean DSA_SlowFalling = false;
		public boolean DSA_DolphinGrace = false;
		public boolean DSA_PlasmaShoot = false;
		public boolean DSA_WaterBreathe = false;
		public boolean placed_hub = false;
		public double hub_energy_save = 0;
		public double hub_z = 0;
		public double hub_x = 0;
		public double hub_y = 0;
		public double player_back_y = 0;
		public double player_home_z = 0;
		public double player_back_x = 0;
		public double player_home_y = 0;
		public double hub_copper_save = 0;
		public double player_home_x = 0;
		public double player_back_z = 0;
		public double hub_coal_save = 0;
		public double hub_iron_save = 0;
		public boolean diamondsteel_itembuffer = false;
		public double player_minnig_level = 1.0;
		public double miners_level = 1.0;
		public double hub_sandstone_save = 0;
		public ItemStack buildgun_material = ItemStack.EMPTY;
		public double build_gun_pos_num = 1.0;
		public double builder_pos2_z = 0;
		public double builder_pos1_z = 0;
		public double builder_pos2_y = 0;
		public double builder_pos1_y = 0;
		public double builder_pos2_x = 0;
		public double builder_pos1_x = 0;
		public double DSA_energy = 0;
		public double DSA_PlasmaShoot_Cooldown = 0;
		public String player_home_dimension = "";
		public double player_number_of_land = 0.0;
		public double player_money = 15000.0;
		public String players_city_name = "\"\"";
		public String belong_to_city = "\"\"";
		public boolean admin_editor = false;
		public String player_back_dimension = "\"\"";
		public double energy_required_for_next_level = 0;
		public double hub_energy_for_upgrade_save = 0;
		public double case_item_counter_8 = 0;
		public boolean player_rolling_case = false;
		public double case_item_counter_1 = 0;
		public double case_item_counter_0 = 0;
		public double case_item_counter_3 = 0;
		public double case_item_counter_2 = 0;
		public double case_item_counter_5 = 0;
		public double case_item_counter_4 = 0;
		public double case_item_counter_7 = 0;
		public double case_item_counter_6 = 0;
		public ItemStack case_item_5 = ItemStack.EMPTY;
		public ItemStack case_item_6 = ItemStack.EMPTY;
		public ItemStack case_item_7 = ItemStack.EMPTY;
		public ItemStack case_item_8 = ItemStack.EMPTY;
		public ItemStack case_item_0 = ItemStack.EMPTY;
		public ItemStack case_item_1 = ItemStack.EMPTY;
		public boolean is_player_in_dimension1 = false;
		public ItemStack case_item_2 = ItemStack.EMPTY;
		public ItemStack case_item_3 = ItemStack.EMPTY;
		public ItemStack case_item_4 = ItemStack.EMPTY;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				IndustrialEconomyMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.hub_caterium_save = original.hub_caterium_save;
		clone.placed_hub = original.placed_hub;
		clone.hub_energy_save = original.hub_energy_save;
		clone.hub_z = original.hub_z;
		clone.hub_x = original.hub_x;
		clone.hub_y = original.hub_y;
		clone.player_back_y = original.player_back_y;
		clone.player_home_z = original.player_home_z;
		clone.player_back_x = original.player_back_x;
		clone.player_home_y = original.player_home_y;
		clone.hub_copper_save = original.hub_copper_save;
		clone.player_home_x = original.player_home_x;
		clone.player_back_z = original.player_back_z;
		clone.hub_coal_save = original.hub_coal_save;
		clone.hub_iron_save = original.hub_iron_save;
		clone.diamondsteel_itembuffer = original.diamondsteel_itembuffer;
		clone.player_minnig_level = original.player_minnig_level;
		clone.miners_level = original.miners_level;
		clone.hub_sandstone_save = original.hub_sandstone_save;
		clone.buildgun_material = original.buildgun_material;
		clone.build_gun_pos_num = original.build_gun_pos_num;
		clone.builder_pos2_z = original.builder_pos2_z;
		clone.builder_pos1_z = original.builder_pos1_z;
		clone.builder_pos2_y = original.builder_pos2_y;
		clone.builder_pos1_y = original.builder_pos1_y;
		clone.builder_pos2_x = original.builder_pos2_x;
		clone.builder_pos1_x = original.builder_pos1_x;
		clone.DSA_energy = original.DSA_energy;
		clone.DSA_PlasmaShoot_Cooldown = original.DSA_PlasmaShoot_Cooldown;
		clone.player_home_dimension = original.player_home_dimension;
		clone.player_number_of_land = original.player_number_of_land;
		clone.player_money = original.player_money;
		clone.players_city_name = original.players_city_name;
		clone.belong_to_city = original.belong_to_city;
		clone.admin_editor = original.admin_editor;
		clone.player_back_dimension = original.player_back_dimension;
		clone.energy_required_for_next_level = original.energy_required_for_next_level;
		clone.hub_energy_for_upgrade_save = original.hub_energy_for_upgrade_save;
		clone.case_item_counter_8 = original.case_item_counter_8;
		clone.player_rolling_case = original.player_rolling_case;
		clone.case_item_counter_1 = original.case_item_counter_1;
		clone.case_item_counter_0 = original.case_item_counter_0;
		clone.case_item_counter_3 = original.case_item_counter_3;
		clone.case_item_counter_2 = original.case_item_counter_2;
		clone.case_item_counter_5 = original.case_item_counter_5;
		clone.case_item_counter_4 = original.case_item_counter_4;
		clone.case_item_counter_7 = original.case_item_counter_7;
		clone.case_item_counter_6 = original.case_item_counter_6;
		clone.case_item_5 = original.case_item_5;
		clone.case_item_6 = original.case_item_6;
		clone.case_item_7 = original.case_item_7;
		clone.case_item_8 = original.case_item_8;
		clone.case_item_0 = original.case_item_0;
		clone.case_item_1 = original.case_item_1;
		clone.case_item_2 = original.case_item_2;
		clone.case_item_3 = original.case_item_3;
		clone.case_item_4 = original.case_item_4;
		if (!event.isWasDeath()) {
			clone.DSA_NightVision = original.DSA_NightVision;
			clone.DSA_Jetpack = original.DSA_Jetpack;
			clone.DSA_Speed = original.DSA_Speed;
			clone.DSA_SlowFalling = original.DSA_SlowFalling;
			clone.DSA_DolphinGrace = original.DSA_DolphinGrace;
			clone.DSA_PlasmaShoot = original.DSA_PlasmaShoot;
			clone.DSA_WaterBreathe = original.DSA_WaterBreathe;
			clone.is_player_in_dimension1 = original.is_player_in_dimension1;
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.hub_caterium_save = message.data.hub_caterium_save;
					variables.DSA_NightVision = message.data.DSA_NightVision;
					variables.DSA_Jetpack = message.data.DSA_Jetpack;
					variables.DSA_Speed = message.data.DSA_Speed;
					variables.DSA_SlowFalling = message.data.DSA_SlowFalling;
					variables.DSA_DolphinGrace = message.data.DSA_DolphinGrace;
					variables.DSA_PlasmaShoot = message.data.DSA_PlasmaShoot;
					variables.DSA_WaterBreathe = message.data.DSA_WaterBreathe;
					variables.placed_hub = message.data.placed_hub;
					variables.hub_energy_save = message.data.hub_energy_save;
					variables.hub_z = message.data.hub_z;
					variables.hub_x = message.data.hub_x;
					variables.hub_y = message.data.hub_y;
					variables.player_back_y = message.data.player_back_y;
					variables.player_home_z = message.data.player_home_z;
					variables.player_back_x = message.data.player_back_x;
					variables.player_home_y = message.data.player_home_y;
					variables.hub_copper_save = message.data.hub_copper_save;
					variables.player_home_x = message.data.player_home_x;
					variables.player_back_z = message.data.player_back_z;
					variables.hub_coal_save = message.data.hub_coal_save;
					variables.hub_iron_save = message.data.hub_iron_save;
					variables.diamondsteel_itembuffer = message.data.diamondsteel_itembuffer;
					variables.player_minnig_level = message.data.player_minnig_level;
					variables.miners_level = message.data.miners_level;
					variables.hub_sandstone_save = message.data.hub_sandstone_save;
					variables.buildgun_material = message.data.buildgun_material;
					variables.build_gun_pos_num = message.data.build_gun_pos_num;
					variables.builder_pos2_z = message.data.builder_pos2_z;
					variables.builder_pos1_z = message.data.builder_pos1_z;
					variables.builder_pos2_y = message.data.builder_pos2_y;
					variables.builder_pos1_y = message.data.builder_pos1_y;
					variables.builder_pos2_x = message.data.builder_pos2_x;
					variables.builder_pos1_x = message.data.builder_pos1_x;
					variables.DSA_energy = message.data.DSA_energy;
					variables.DSA_PlasmaShoot_Cooldown = message.data.DSA_PlasmaShoot_Cooldown;
					variables.player_home_dimension = message.data.player_home_dimension;
					variables.player_number_of_land = message.data.player_number_of_land;
					variables.player_money = message.data.player_money;
					variables.players_city_name = message.data.players_city_name;
					variables.belong_to_city = message.data.belong_to_city;
					variables.admin_editor = message.data.admin_editor;
					variables.player_back_dimension = message.data.player_back_dimension;
					variables.energy_required_for_next_level = message.data.energy_required_for_next_level;
					variables.hub_energy_for_upgrade_save = message.data.hub_energy_for_upgrade_save;
					variables.case_item_counter_8 = message.data.case_item_counter_8;
					variables.player_rolling_case = message.data.player_rolling_case;
					variables.case_item_counter_1 = message.data.case_item_counter_1;
					variables.case_item_counter_0 = message.data.case_item_counter_0;
					variables.case_item_counter_3 = message.data.case_item_counter_3;
					variables.case_item_counter_2 = message.data.case_item_counter_2;
					variables.case_item_counter_5 = message.data.case_item_counter_5;
					variables.case_item_counter_4 = message.data.case_item_counter_4;
					variables.case_item_counter_7 = message.data.case_item_counter_7;
					variables.case_item_counter_6 = message.data.case_item_counter_6;
					variables.case_item_5 = message.data.case_item_5;
					variables.case_item_6 = message.data.case_item_6;
					variables.case_item_7 = message.data.case_item_7;
					variables.case_item_8 = message.data.case_item_8;
					variables.case_item_0 = message.data.case_item_0;
					variables.case_item_1 = message.data.case_item_1;
					variables.is_player_in_dimension1 = message.data.is_player_in_dimension1;
					variables.case_item_2 = message.data.case_item_2;
					variables.case_item_3 = message.data.case_item_3;
					variables.case_item_4 = message.data.case_item_4;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
