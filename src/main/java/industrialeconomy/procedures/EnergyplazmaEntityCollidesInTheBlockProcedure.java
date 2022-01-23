package industrialeconomy.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Map;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

public class EnergyplazmaEntityCollidesInTheBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency world for procedure EnergyplazmaEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency x for procedure EnergyplazmaEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency y for procedure EnergyplazmaEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency z for procedure EnergyplazmaEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				IndustrialEconomyMod.LOGGER.warn("Failed to load dependency entity for procedure EnergyplazmaEntityCollidesInTheBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		String owner = "";
		double players_hub_x = 0;
		double players_hub_y = 0;
		double players_hub_z = 0;
		owner = (new Object() {
			public String getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "owner"));
		players_hub_x = (new Object() {
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
				(owner + "hub_X")));
		players_hub_y = (new Object() {
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
				(owner + "hub_Y")));
		players_hub_z = (new Object() {
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
				(owner + "hub_Z")));
		if ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) players_hub_x, (int) players_hub_y, (int) players_hub_z),
				"Energy") > 100) == (entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_energy < 15000) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) players_hub_x, (int) players_hub_y, (int) players_hub_z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Energy", ((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) players_hub_x, (int) players_hub_y, (int) players_hub_z), "Energy")) - 50));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			{
				double _setval = ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_energy + 1);
				entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.DSA_energy = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("industrial_economy:plazma")),
						SoundCategory.NEUTRAL, (float) 0.01, (float) 0.01);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("industrial_economy:plazma")),
						SoundCategory.NEUTRAL, (float) 0.01, (float) 0.01, false);
			}
			world.addParticle(ParticleTypes.ENCHANTED_HIT, (x + 0.5), y, (z + 0.5), 0, 0.5, 0);
			world.addParticle(ParticleTypes.ENCHANTED_HIT, (x + 0.5), y, (z + 0.5), 0.7, 0.5, 0);
			world.addParticle(ParticleTypes.ENCHANTED_HIT, (x + 0.5), y, (z + 0.5), (-0.7), 0.5, 0);
			world.addParticle(ParticleTypes.ENCHANTED_HIT, (x + 0.5), y, (z + 0.5), 0, 0.5, (-0.7));
			world.addParticle(ParticleTypes.ENCHANTED_HIT, (x + 0.5), y, (z + 0.5), 0, 0.5, 0.7);
		}
	}
}
