package com.saloeater.curios_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.inventory.CurioStacksHandler;

@Mixin(CurioStacksHandler.class)
public interface CurioStacksHandlerAccessor {
    @Accessor("stackHandler")
    IDynamicStackHandler getStackHandler();
}
