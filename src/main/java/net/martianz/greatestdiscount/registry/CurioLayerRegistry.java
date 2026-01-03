package net.martianz.greatestdiscount.registry;

import net.martianz.greatestdiscount.GreatestDiscount;
import net.martianz.greatestdiscount.client.model.ScarfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CurioLayerRegistry {

    public static final ModelLayerLocation SCARF_OF_GREATEST_MANA_REGEN_LAYER = createLayerLocation("scarf_of_greatest_mana_regen");

    public static ModelLayerLocation createLayerLocation(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GreatestDiscount.MODID, name), name);
    }

    private static Supplier<LayerDefinition> layer(Supplier<MeshDefinition> mesh, int textureWidth, int textureHeight) {
        return () -> LayerDefinition.create(mesh.get(), textureWidth, textureHeight);
    }

    public static void register(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registration) {
        registration.accept(SCARF_OF_GREATEST_MANA_REGEN_LAYER, layer(ScarfModel::createScarf, 64, 32));
    }
}
