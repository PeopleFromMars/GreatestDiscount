package net.martianz.greatestdiscount;

import net.martianz.greatestdiscount.registry.ModItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(GreatestDiscount.MODID)
public class GreatestDiscount {

    public static final String MODID = "greatest_discount";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GreatestDiscount(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModItemRegistry.registerRegistries(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(ModItemRegistry::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public static ResourceLocation id(String path, String... args) {
        return ResourceLocation.fromNamespaceAndPath(MODID, String.format(path, (Object[]) args));
    }
}
