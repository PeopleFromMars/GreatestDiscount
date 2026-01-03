package net.martianz.greatestdiscount.registry;

import net.martianz.greatestdiscount.client.renderer.CurioRenderer;
import net.martianz.greatestdiscount.client.renderer.EquipmentRenderingManager;
import net.martianz.greatestdiscount.client.renderer.GenericCurioRenderer;
import net.martianz.greatestdiscount.client.model.ScarfModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModRendererRegistry {

    public static void register(){
        register(ModItemRegistry.SCARF_OF_GREATEST_MANA_REGEN.get(), () -> new GenericCurioRenderer("scarf_of_greatest_mana_regen", new ScarfModel(bakeLayer(CurioLayerRegistry.SCARF_OF_GREATEST_MANA_REGEN_LAYER), RenderType::entityCutoutNoCull)));
    }

    public static ModelPart bakeLayer(ModelLayerLocation layerLocation) {
        return Minecraft.getInstance().getEntityModels().bakeLayer(layerLocation);
    }

    public static void register(Item item, Supplier<CurioRenderer> rendererFactory) {
        EquipmentRenderingManager.registerCurioRenderer(item, rendererFactory);
    }
}
