package com.saloeater.curios_fix.mixin;

import com.aizistral.enigmaticlegacy.helpers.ItemLoreHelper;
import com.saloeater.curios_fix.legacy.LegacyHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(
            method="getTooltipLines",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V", shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void curios_fix$appendCuriosTooltipLines(Player p_41652_, TooltipFlag p_41653_, CallbackInfoReturnable<List<Component>> cir, List<Component> list, MutableComponent mutablecomponent, int j) {
        var stack = (ItemStack)(Object)this;
        boolean isCursedItem = LegacyHandler.isCursedItem(stack);
        if (!isCursedItem) {
            return;
        }

        boolean shouldBeWorthy = LegacyHandler.requiresWorthy(stack);
        if (!shouldBeWorthy) {
            ItemLoreHelper.indicateCursedOnesOnly(list);
            return;
        }

        ItemLoreHelper.indicateWorthyOnesOnly(list);
    }
}
