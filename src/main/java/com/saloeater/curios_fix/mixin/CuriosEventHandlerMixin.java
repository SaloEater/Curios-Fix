package com.saloeater.curios_fix.mixin;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.common.event.CuriosEventHandler;

import java.util.Map;
import java.util.UUID;

@Mixin(CuriosEventHandler.class)
public abstract class CuriosEventHandlerMixin {
    @Inject(
            method="lambda$tick$34",
            at= @At(value = "INVOKE", target = "Ltop/theillusivec4/curios/api/type/capability/ICuriosItemHandler;getCurios()Ljava/util/Map;")
    )
    private static void calculateSlotAttributes_lambda$tick$34(LivingEntity livingEntity, ICuriosItemHandler handler, CallbackInfo ci) {
//        if (livingEntity.level().isClientSide) {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                String identifier = entry.getKey();
//                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                IDynamicStackHandler stackHandler = ((CurioStacksHandlerAccessor) stacksHandler).getStackHandler();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (stack.isEmpty()) {
                        continue;
                    }
                    NonNullList<Boolean> renderStates = stacksHandler.getRenders();
                    SlotContext slotContext = new SlotContext(identifier, livingEntity, i, false,
                            renderStates.size() > i && renderStates.get(i));
                    UUID uuid = CuriosApi.getSlotUuid(slotContext);
                    Multimap<Attribute, AttributeModifier> map =
                            CuriosApi.getAttributeModifiers(slotContext, uuid, stack);
                    Multimap<String, AttributeModifier> slots = HashMultimap.create();
                    for (Attribute attribute : map.keySet()) {
                        if (attribute instanceof SlotAttribute wrapper) {
                            slots.putAll(wrapper.getIdentifier(), map.get(attribute));
                        }
                    }

                    livingEntity.getAttributes().addTransientAttributeModifiers(map);
                    handler.addTransientSlotModifiers(slots);
                }
            }
//        }
    }
}
