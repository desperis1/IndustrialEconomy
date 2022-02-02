
package industrialeconomy.gui;

import industrialeconomy.IndustrialEconomyMod;

@OnlyIn(Dist.CLIENT)
public class MAMGUIMAINGuiWindow extends ContainerScreen<MAMGUIMAINGui.GuiContainerMod> {

	private World world;
	private int x, y, z;
	private PlayerEntity entity;

	private final static HashMap guistate = MAMGUIMAINGui.guistate;

	public MAMGUIMAINGuiWindow(MAMGUIMAINGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 293;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("industrial_economy:textures/mamguimain.png");

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

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguimoney.png"));
		this.blit(ms, this.guiLeft + 144, this.guiTop + 143, 0, 0, 16, 16, 16, 16);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("industrial_economy:textures/mamguienergy.png"));
		this.blit(ms, this.guiLeft + 9, this.guiTop + 142, 0, 0, 16, 16, 16, 16);

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
		if (

		TimetoCompleteShowConditionProcedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)))
			this.font.drawString(ms, "Time to complete research: " + (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return 0;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "TimeToComplete")) + "", 3, 130, -12829636);
		this.font.drawString(ms, "Energy: " + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Energy")) + "", 26, 146, -12829636);
		this.font.drawString(ms, "Money: " + (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return 0;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Money")) + "", 165, 146, -12829636);
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

		this.addButton(new Button(this.guiLeft + 9, this.guiTop + 8, 72, 20, new StringTextComponent("Materials"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MAMGUIMAINGui.ButtonPressedMessage(0, x, y, z));
				MAMGUIMAINGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 9, this.guiTop + 31, 82, 20, new StringTextComponent("Electronics"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MAMGUIMAINGui.ButtonPressedMessage(1, x, y, z));
				MAMGUIMAINGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 9, this.guiTop + 54, 77, 20, new StringTextComponent("Technology"), e -> {
			if (true) {
				IndustrialEconomyMod.PACKET_HANDLER.sendToServer(new MAMGUIMAINGui.ButtonPressedMessage(2, x, y, z));
				MAMGUIMAINGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}

}
