
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
public class MamGUIElectronicsOnClickGuiWindow extends ContainerScreen<MamGUIElectronicsOnClickGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = MamGUIElectronicsOnClickGui.guistate;

	public MamGUIElectronicsOnClickGuiWindow(MamGUIElectronicsOnClickGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 293;
		this.ySize = 201;
	}

	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/mam_gui_electronics.png");

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
		this.blit(ms, this.guiLeft + 9, this.guiTop + 176, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 152, this.guiTop + 177, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 97, this.guiTop + 10, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 164, this.guiTop + 10, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 162, this.guiTop + 32, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 98, this.guiTop + 32, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 191, this.guiTop + 54, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 96, this.guiTop + 54, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 104, this.guiTop + 76, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 104, this.guiTop + 98, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 170, this.guiTop + 76, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 169, this.guiTop + 98, 0, 0, 16, 16, 16, 16);

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
		this.font.drawString(ms, "Energy: " + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Energy")) + "", 20, 180, -12829636);
		this.font.drawString(ms, "Money: " + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Money")) + "", 171, 180, -12829636);
		this.font.drawString(ms, "1 E8 MW", 114, 35, -12829636);
		this.font.drawString(ms, "150 000 coins", 187, 35, -12829636);
		this.font.drawString(ms, "2.5 E7 MW", 112, 14, -12829636);
		this.font.drawString(ms, "25 000 coins", 189, 13, -12829636);
		this.font.drawString(ms, "350 milions MW", 109, 57, -12829636);
		this.font.drawString(ms, "150 000 coins", 211, 57, -12829636);
		this.font.drawString(ms, "1 E9 MW", 119, 79, -12829636);
		this.font.drawString(ms, "2 E9 MW", 119, 101, -12829636);
		this.font.drawString(ms, "150 000 coins", 193, 79, -12829636);
		this.font.drawString(ms, "150 000 coins", 195, 100, -12829636);
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
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 8, 82, 20, new StringTextComponent("2x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(0, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 30, 82, 20, new StringTextComponent("4x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(1, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 52, 82, 20, new StringTextComponent("8x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(2, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 74, 87, 20, new StringTextComponent("16x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(3, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 96, 87, 20, new StringTextComponent("32x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(4, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 118, 87, 20, new StringTextComponent("64x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(5, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 140, 92, 20, new StringTextComponent("128x Core CPU"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MamGUIElectronicsOnClickGui.ButtonPressedMessage(6, x, y, z));
				MamGUIElectronicsOnClickGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
	}
}
