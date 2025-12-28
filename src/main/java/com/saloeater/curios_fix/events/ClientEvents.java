package com.saloeater.curios_fix.events;

import com.aizistral.enigmaticlegacy.helpers.ItemLoreHelper;
import com.saloeater.curios_fix.legacy.LegacyHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {

    }
}
