package com.zmm2025.createsignsoflife;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import com.simibubi.create.api.behaviour.display.DisplayTarget;

@Mod(CreateSignsOfLife.MOD_ID)
public final class CreateSignsOfLife
{
    public static final String MOD_ID = "create_signs_of_life";

    public CreateSignsOfLife(IEventBus bus, net.neoforged.fml.ModContainer container) {
        ModContent.register(bus);
        com.zmm2025.createsignsoflife.media.MediaNetwork.registerLifecycle();
        container.registerConfig(net.neoforged.fml.config.ModConfig.Type.SERVER,com.zmm2025.createsignsoflife.media.MediaLimits.SPEC);
        bus.addListener(this::setup);
        bus.addListener(com.zmm2025.createsignsoflife.media.MediaNetwork::register);
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DisplayTarget.BY_BLOCK.register(ModContent.FLIP_DISC.get(), ModContent.TARGET.get());
            com.simibubi.create.api.stress.BlockStressValues.IMPACTS.register(ModContent.FLIP_DISC.get(), () -> 1);
        });
    }
}
