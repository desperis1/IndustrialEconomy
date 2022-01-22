
package industrialeconomy.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import industrialeconomy.item.TurbofuelitemItem;

@Mod.EventBusSubscriber
public class Turbofuelmk1Fuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == TurbofuelitemItem.block)
			event.setBurnTime(9600);
	}
}
