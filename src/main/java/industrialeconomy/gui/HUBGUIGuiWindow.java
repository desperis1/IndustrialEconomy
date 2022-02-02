
package industrialeconomy.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import industrialeconomy.IndustrialEconomyMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class HUBGUIGuiWindow extends ContainerScreen<HUBGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = HUBGUIGui.guistate;

	public HUBGUIGuiWindow(HUBGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 380;
		this.ySize = 219;
	}

	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/hubgui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 8, this.guiTop + 112, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/up_green_arrow.png"));
		this.blit(ms, this.guiLeft + 5, this.guiTop + 130, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/down_red_arrow.png"));
		this.blit(ms, this.guiLeft + 5, this.guiTop + 148, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 5, this.guiTop + 167, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/pickaxe_miner.png"));
		this.blit(ms, this.guiLeft + 5, this.guiTop + 190, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Energy:", 24, 116, -16724788);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Energy")) + "", 63, 117, -12829636);
		this.font.drawString(ms, "Caterium Mined", 10, 18, -12829636);
		this.font.drawString(ms, "Coal Mined", 20, 35, -12829636);
		this.font.drawString(ms, "Iron Mined", 20, 54, -12829636);
		this.font.drawString(ms, "Copper Mined", 14, 72, -12829636);
		this.font.drawString(ms, "SandStone Mined", 7, 90, -12829636);
		this.font.drawString(ms, "HUB", 190, 5, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Caterium")) + "", 124, 18, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Coal")) + "", 124, 35, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Iron")) + "", 124, 54, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Copper")) + "", 124, 72, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "SandStone")) + "", 124, 90, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Edown")) + "", 75, 153, -12829636);
		this.font.drawString(ms, "Produced:", 24, 133, -16764160);
		this.font.drawString(ms, "Consumed:", 24, 152, -13434880);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Eup")) + "", 74, 134, -12829636);
		this.font.drawString(ms, "Money:", 25, 170, -6711040);
		this.font.drawString(ms, "Miner Levels:", 25, 187, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Money")) + "", 61, 171, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "minerLevels")) + "", 97, 188, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyForMinerUpgrade")) + "", 102, 201, -12829636);
		this.font.drawString(ms, "Upgrade Price:", 25, 200, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 270, this.guiTop + 110, 98, 20, new StringTextComponent("Upgrade Miners"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new HUBGUIGui.ButtonPressedMessage(0, x, y, z));
				HUBGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
