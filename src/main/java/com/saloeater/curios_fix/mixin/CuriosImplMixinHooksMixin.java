package com.saloeater.curios_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.mixin.CuriosImplMixinHooks;

import java.util.Map;
import java.util.UUID;

@Mixin(CuriosImplMixinHooks.class)
public abstract class CuriosImplMixinHooksMixin {
    @Shadow
    private static Map<String, UUID> UUIDS;

    @Inject(
            method = "<clinit>",
            at = @At("RETURN")
    )
    private static void clinit$curios_fix(CallbackInfo ci) {
        UUIDS = new java.util.concurrent.ConcurrentHashMap<String, UUID>();
    }
}
