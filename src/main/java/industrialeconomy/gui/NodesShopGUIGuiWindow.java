
package industrialeconomy.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class NodesShopGUIGuiWindow extends ContainerScreen<NodesShopGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = NodesShopGUIGui.guistate;
	public NodesShopGUIGuiWindow(NodesShopGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 281;
		this.ySize = 185;
	}
	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/nodes_shop_gui.png");
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
		this.font.drawString(ms, "Nodes Shop", 5, 4, -12829636);
		this.font.drawString(ms, "Your money" + ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).player_money) + " \u20AC", 78, 4, -13382656);
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
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 16, 70, 20, new StringTextComponent("Iron Node"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new NodesShopGUIGui.ButtonPressedMessage(0, x, y, z));
				NodesShopGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 149, this.guiTop + 16, 80, 20, new StringTextComponent("Copper Node"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new NodesShopGUIGui.ButtonPressedMessage(1, x, y, z));
				NodesShopGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 43, 90, 20, new StringTextComponent("Caterium Node"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new NodesShopGUIGui.ButtonPressedMessage(2, x, y, z));
				NodesShopGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 149, this.guiTop + 43, 70, 20, new StringTextComponent("Coal Node"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new NodesShopGUIGui.ButtonPressedMessage(3, x, y, z));
				NodesShopGUIGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 70, 95, 20, new StringTextComponent("SandStone Node"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new NodesShopGUIGui.ButtonPressedMessage(4, x, y, z));
				NodesShopGUIGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
	}
}
