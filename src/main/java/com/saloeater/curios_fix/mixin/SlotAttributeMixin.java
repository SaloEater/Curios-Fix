package com.saloeater.curios_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.mixin.CuriosImplMixinHooks;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(SlotAttribute.class)
public abstract class SlotAttributeMixin {
    @Shadow
    private static Map<String, SlotAttribute> SLOT_ATTRIBUTES;

    @Inject(
            method = "<clinit>",
            at = @At("RETURN")
    )
    private static void clinit$curios_fix(CallbackInfo ci) {
        SLOT_ATTRIBUTES = new ConcurrentHashMap<String, SlotAttribute>();
    }
}
