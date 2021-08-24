
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

import industrialeconomy.procedures.DSAplasmalabelProcedure;
import industrialeconomy.procedures.DSAoverlaywaterbreatheProcedure;
import industrialeconomy.procedures.DSAoverlayspeedProcedure;
import industrialeconomy.procedures.DSAoverlayslowfallingProcedure;
import industrialeconomy.procedures.DSAoverlaynightvisionProcedure;
import industrialeconomy.procedures.DSAoverlayjetpackProcedure;
import industrialeconomy.procedures.DSAoverlaydolphingraceProcedure;

import industrialeconomy.IndustrialEconomyModVariables;

import industrialeconomy.IndustrialEconomyMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@OnlyIn(Dist.CLIENT)
public class DiamondSteelArmorGUIGuiWindow extends ContainerScreen<DiamondSteelArmorGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = DiamondSteelArmorGUIGui.guistate;
	public DiamondSteelArmorGUIGuiWindow(DiamondSteelArmorGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 248;
		this.ySize = 218;
	}
	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/diamond_steel_armor_gui.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/2.png"));
		this.blit(ms, this.guiLeft + 114, this.guiTop + 87, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/1.png"));
		this.blit(ms, this.guiLeft + 114, this.guiTop + 60, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/3.png"));
		this.blit(ms, this.guiLeft + 114, this.guiTop + 33, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/4.png"));
		this.blit(ms, this.guiLeft + 114, this.guiTop + 114, 0, 0, 16, 16, 16, 16);
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
		this.font.drawString(ms, "Armor Panel", 4, 4, -12829636);
		if (DSAoverlayspeedProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Speed", 166, 197, -16724992);
		if (DSAoverlayslowfallingProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Slow Falling", 149, 184, -13382656);
		if (DSAoverlayjetpackProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "JetPack", 160, 170, -16724992);
		if (DSAoverlaynightvisionProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Night Vision", 150, 156, -16724992);
		if (DSAoverlaywaterbreatheProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Water Breathe", 14, 157, -13382656);
		if (DSAoverlaydolphingraceProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Fast Swim", 22, 184, -16724992);
		this.font.drawString(ms, "Energy: " + (int) ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_energy) + "", 78, 3, -16750849);
		this.font.drawString(ms, "Active Functions", 80, 143, -12829636);
		if (DSAplasmalabelProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
			this.font.drawString(ms, "Plasma Shoot", 16, 170, -16724992);
		this.font.drawString(ms,
				"Plasma Shoot CoolDown: " + (int) ((entity.getCapability(IndustrialEconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new IndustrialEconomyModVariables.PlayerVariables())).DSA_PlasmaShoot_Cooldown) + "",
				4, 17, -12829636);
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
		this.addButton(new Button(this.guiLeft + 150, this.guiTop + 33, 85, 20, new StringTextComponent("Night Vision"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(0, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 168, this.guiTop + 60, 60, 20, new StringTextComponent("Jetpack"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(1, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 168, this.guiTop + 114, 50, 20, new StringTextComponent("Speed"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(2, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 33, 90, 20, new StringTextComponent("Water Breathe"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(3, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 150, this.guiTop + 87, 85, 20, new StringTextComponent("Slow Falling"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(4, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 87, 90, 20, new StringTextComponent("Fast Swim"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(5, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 24, this.guiTop + 114, 30, 20, new StringTextComponent("X"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(6, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 10, this.guiTop + 59, 85, 20, new StringTextComponent("Plasma Shoot"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new DiamondSteelArmorGUIGui.ButtonPressedMessage(7, x, y, z));
				DiamondSteelArmorGUIGui.handleButtonAction(entity, 7, x, y, z);
			}
		}));
	}
}
