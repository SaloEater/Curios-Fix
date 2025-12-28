package com.saloeater.curios_fix.events;

import com.saloeater.curios_fix.legacy.LegacyHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.event.CurioEquipEvent;

public class CuriosServerEvents {
    @SubscribeEvent
    public static void onCuriosEquip(CurioEquipEvent event) {
        var canEquip = LegacyHandler.canEquip(event.getEntity(), event.getStack());
        if (!canEquip) {
            event.setResult(Event.Result.DENY);
        }
    }
}
