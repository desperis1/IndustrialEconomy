
package industrialeconomy.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import industrialeconomy.item.Turbofuelitem3Item;

@Mod.EventBusSubscriber
public class Turbofuelmk3Fuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == Turbofuelitem3Item.block)
			event.setBurnTime(345600);
	}
}
