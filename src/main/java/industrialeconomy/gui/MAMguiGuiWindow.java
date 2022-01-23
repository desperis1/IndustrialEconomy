
package industrialeconomy.gui;

import industrialeconomy.IndustrialEconomyMod;

@OnlyIn(Dist.CLIENT)
public class MAMguiGuiWindow extends ContainerScreen<MAMguiGui.GuiContainerMod> {

	private World world;
	private int x, y, z;
	private PlayerEntity entity;

	private final static HashMap guistate = MAMguiGui.guistate;

	public MAMguiGuiWindow(MAMguiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 350;
		this.ySize = 200;
	}

	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/ma_mgui.png");

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
		this.blit(ms, this.guiLeft + 8, this.guiTop + 173, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 214, this.guiTop + 173, 0, 0, 16, 16, 16, 16);

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
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Energy")) + "", 26, 175, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Money")) + "", 237, 175, -12829636);
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

		this.addButton(new Button(this.guiLeft + 40, this.guiTop + 12, 93, 20, new StringTextComponent("Steel Upgrade"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MAMguiGui.ButtonPressedMessage(0, x, y, z));
				MAMguiGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 40, this.guiTop + 32, 134, 20, new StringTextComponent("DIamond Steel Upgrade"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MAMguiGui.ButtonPressedMessage(1, x, y, z));
				MAMguiGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}

}
