package com.saloeater.curios_fix;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.saloeater.curios_fix.CuriosFixMod.*;

public class Tags {
    public static final TagKey<Item> CURSED_SEVEN_CURSES = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "seven_curses"));
    public static final TagKey<Item> CURSED_WORTHY = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "seven_curses_worthy"));
}
