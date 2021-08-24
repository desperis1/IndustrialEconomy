
package industrialeconomy.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import industrialeconomy.item.Turbofuelitem2Item;

@Mod.EventBusSubscriber
public class Turbofuelmk2Fuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == Turbofuelitem2Item.block)
			event.setBurnTime(57600);
	}
}
