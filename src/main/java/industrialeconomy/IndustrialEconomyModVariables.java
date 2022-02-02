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
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	public static File prices = new File("");
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
			nbt.putBoolean("DSA_NightVision", instance.DSA_NightVision);
			nbt.putBoolean("DSA_Jetpack", instance.DSA_Jetpack);
			nbt.putBoolean("DSA_Speed", instance.DSA_Speed);
			nbt.putBoolean("DSA_SlowFalling", instance.DSA_SlowFalling);
			nbt.putBoolean("DSA_DolphinGrace", instance.DSA_DolphinGrace);
			nbt.putBoolean("DSA_PlasmaShoot", instance.DSA_PlasmaShoot);
			nbt.putBoolean("DSA_WaterBreathe", instance.DSA_WaterBreathe);
			nbt.putBoolean("diamondsteel_itembuffer", instance.diamondsteel_itembuffer);
			nbt.putDouble("player_minnig_level", instance.player_minnig_level);
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
			nbt.putString("placetool_machine", instance.placetool_machine);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.DSA_NightVision = nbt.getBoolean("DSA_NightVision");
			instance.DSA_Jetpack = nbt.getBoolean("DSA_Jetpack");
			instance.DSA_Speed = nbt.getBoolean("DSA_Speed");
			instance.DSA_SlowFalling = nbt.getBoolean("DSA_SlowFalling");
			instance.DSA_DolphinGrace = nbt.getBoolean("DSA_DolphinGrace");
			instance.DSA_PlasmaShoot = nbt.getBoolean("DSA_PlasmaShoot");
			instance.DSA_WaterBreathe = nbt.getBoolean("DSA_WaterBreathe");
			instance.diamondsteel_itembuffer = nbt.getBoolean("diamondsteel_itembuffer");
			instance.player_minnig_level = nbt.getDouble("player_minnig_level");
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
			instance.placetool_machine = nbt.getString("placetool_machine");
		}
	}

	public static class PlayerVariables {
		public boolean DSA_NightVision = false;
		public boolean DSA_Jetpack = false;
		public boolean DSA_Speed = false;
		public boolean DSA_SlowFalling = false;
		public boolean DSA_DolphinGrace = false;
		public boolean DSA_PlasmaShoot = false;
		public boolean DSA_WaterBreathe = false;
		public boolean diamondsteel_itembuffer = false;
		public double player_minnig_level = 1.0;
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
		public String placetool_machine = "\"\"";

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
		clone.diamondsteel_itembuffer = original.diamondsteel_itembuffer;
		clone.player_minnig_level = original.player_minnig_level;
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
		clone.placetool_machine = original.placetool_machine;
		if (!event.isWasDeath()) {
			clone.DSA_NightVision = original.DSA_NightVision;
			clone.DSA_Jetpack = original.DSA_Jetpack;
			clone.DSA_Speed = original.DSA_Speed;
			clone.DSA_SlowFalling = original.DSA_SlowFalling;
			clone.DSA_DolphinGrace = original.DSA_DolphinGrace;
			clone.DSA_PlasmaShoot = original.DSA_PlasmaShoot;
			clone.DSA_WaterBreathe = original.DSA_WaterBreathe;
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
					variables.DSA_NightVision = message.data.DSA_NightVision;
					variables.DSA_Jetpack = message.data.DSA_Jetpack;
					variables.DSA_Speed = message.data.DSA_Speed;
					variables.DSA_SlowFalling = message.data.DSA_SlowFalling;
					variables.DSA_DolphinGrace = message.data.DSA_DolphinGrace;
					variables.DSA_PlasmaShoot = message.data.DSA_PlasmaShoot;
					variables.DSA_WaterBreathe = message.data.DSA_WaterBreathe;
					variables.diamondsteel_itembuffer = message.data.diamondsteel_itembuffer;
					variables.player_minnig_level = message.data.player_minnig_level;
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
					variables.placetool_machine = message.data.placetool_machine;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
