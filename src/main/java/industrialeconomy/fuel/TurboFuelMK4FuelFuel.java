
package industrialeconomy.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import industrialeconomy.item.TurboFuelMK4Item;

@Mod.EventBusSubscriber
public class TurboFuelMK4FuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == TurboFuelMK4Item.block)
			event.setBurnTime(3345600);
	}
}
