
package industrialeconomy.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import industrialeconomy.procedures.GeneratorWorkingLabelProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class FoundryGUIGuiWindow extends ContainerScreen<FoundryGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = FoundryGUIGui.guistate;

	public FoundryGUIGuiWindow(FoundryGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 233;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/foundry_gui.png");

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
		if (GeneratorWorkingLabelProcedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
			this.blit(ms, this.guiLeft + 144, this.guiTop + 59, 0, 0, 16, 16, 16, 16);
		}

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/ironingot.png"));
		this.blit(ms, this.guiLeft + 15, this.guiTop + 6, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/coal.png"));
		this.blit(ms, this.guiLeft + 52, this.guiTop + 6, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/steelingot.png"));
		this.blit(ms, this.guiLeft + 86, this.guiTop + 6, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/diamondsteelingot.png"));
		this.blit(ms, this.guiLeft + 86, this.guiTop + 23, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/industrial.png"));
		this.blit(ms, this.guiLeft + 52, this.guiTop + 23, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/steelingot.png"));
		this.blit(ms, this.guiLeft + 18, this.guiTop + 22, 0, 0, 16, 16, 16, 16);

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
		this.font.drawString(ms, "Foundry", 119, 3, -65536);
		if (GeneratorWorkingLabelProcedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)))
			this.font.drawString(ms, "Working", 167, 63, -16738048);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "RecipePower")) + "", 169, 4, -16763956);
		this.font.drawString(ms, "+", 36, 9, -12829636);
		this.font.drawString(ms, "=", 72, 9, -12829636);
		this.font.drawString(ms, "6", 8, 9, -12829636);
		this.font.drawString(ms, "3", 46, 9, -12829636);
		this.font.drawString(ms, "1", 79, 9, -12829636);
		this.font.drawString(ms, "=", 72, 27, -12829636);
		this.font.drawString(ms, "1", 79, 26, -12829636);
		this.font.drawString(ms, "16", 6, 26, -12829636);
		this.font.drawString(ms, "+", 37, 25, -12829636);
		this.font.drawString(ms, "4", 46, 26, -12829636);
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
	}
}
