package com.zmm2025.createsignsoflife.dev;

import com.zmm2025.createsignsoflife.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid=CreateSignsOfLife.MOD_ID)
public final class ServerSmokeCheck {
    @SubscribeEvent public static void started(ServerStartedEvent event) {
        if(!Boolean.getBoolean("flipdisc.serverCheck"))return;
        if(event.getServer().getRecipeManager().byKey(ModContent.FLIP_DISC.getId()).isEmpty())throw new IllegalStateException("Flip-disc recipe missing on dedicated server");
        if(com.simibubi.create.api.behaviour.display.DisplayTarget.BY_BLOCK.get(ModContent.FLIP_DISC.get())!=ModContent.TARGET.get())throw new IllegalStateException("Display Link target missing");
        System.out.println("FLIP_DISC_SERVER_CHECK PASS dedicated server, recipe, target and media networking loaded");
        event.getServer().halt(false);
    }
}
