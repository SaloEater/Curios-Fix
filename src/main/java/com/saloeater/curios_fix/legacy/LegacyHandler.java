package com.saloeater.curios_fix.legacy;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class LegacyHandler {
    public static boolean canEquip(LivingEntity entity, ItemStack stack) {
        if (!(entity instanceof Player player)) {
            return true;
        }

        if (!isCursedItem(stack)) return true;

        boolean isCursedOne = SuperpositionHandler.isTheCursedOne(player);
        if (!isCursedOne) {
            return false;
        }

        if (!requiresWorthy(stack)) {
            return true;
        }

        boolean isWorthy = SuperpositionHandler.isTheWorthyOne(player);
        return isWorthy;
    }

    public static boolean requiresWorthy(ItemStack stack) {
        return stack.is(com.saloeater.curios_fix.Tags.CURSED_WORTHY);
    }

    public static boolean isCursedItem(ItemStack stack) {
        return stack.is(com.saloeater.curios_fix.Tags.CURSED_SEVEN_CURSES);
    }
}
