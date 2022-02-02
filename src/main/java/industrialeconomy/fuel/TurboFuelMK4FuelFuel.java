
package industrialeconomy.fuel;

@Mod.EventBusSubscriber
public class TurboFuelMK4FuelFuel {

	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == TurboFuelMK4Item.block)
			event.setBurnTime(3345600);
	}

}
