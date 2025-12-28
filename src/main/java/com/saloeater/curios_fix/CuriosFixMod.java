package com.saloeater.curios_fix;

import com.saloeater.curios_fix.events.CuriosServerEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CuriosFixMod.MODID)
public class CuriosFixMod
{
    public static final String MODID = "saloeater_curios_fix";

    public CuriosFixMod()
    {
        var forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.register(this);
        forgeBus.register(CuriosServerEvents.class);
    }
}
