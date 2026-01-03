package net.martianz.greatestdiscount;

import net.martianz.greatestdiscount.client.CurioClientSetup;
import net.martianz.greatestdiscount.registry.CurioLayerRegistry;
import net.martianz.greatestdiscount.registry.ModRendererRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = GreatestDiscount.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = GreatestDiscount.MODID, value = Dist.CLIENT)
public class GreatestDiscountClient {

    public GreatestDiscountClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        CurioClientSetup.setup(container.getEventBus());
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {ModRendererRegistry.register();});
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        CurioLayerRegistry.register(event::registerLayerDefinition);
    }
}
