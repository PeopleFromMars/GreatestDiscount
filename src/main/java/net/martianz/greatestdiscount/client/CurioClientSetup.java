package net.martianz.greatestdiscount.client;

import net.martianz.greatestdiscount.GreatestDiscount;
import net.martianz.greatestdiscount.client.renderer.CuriosRenderingHandler;
import net.martianz.greatestdiscount.client.renderer.EquipmentRenderingManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import top.theillusivec4.curios.client.render.CuriosLayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CurioClientSetup {

    public static final Map<EntityType<?>, ResourceKey<LootTable>> ENTITY_EQUIPMENT;
    static {
        ENTITY_EQUIPMENT = new HashMap<>();
        List.of(
                EntityType.ZOMBIE,
                EntityType.HUSK,
                EntityType.DROWNED,
                EntityType.SKELETON,
                EntityType.STRAY,
                EntityType.WITHER_SKELETON,
                EntityType.PIGLIN,
                EntityType.PIGLIN_BRUTE,
                EntityType.ZOMBIFIED_PIGLIN,
                EntityType.GHAST
        ).forEach(type -> ENTITY_EQUIPMENT.put(type, entityEquipmentLootTable(type)));
    }

    public static ResourceKey<LootTable> entityEquipmentLootTable(EntityType<?> entityType) {
        return ResourceKey.create(Registries.LOOT_TABLE, GreatestDiscount.id("entity_equipment/%s", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath()));
    }

    public static void setup(IEventBus modBus) {
        EquipmentRenderingManager.register(new CuriosRenderingHandler());
        modBus.addListener(CurioClientSetup::onAddLayers);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        Set<EntityType<?>> entities = ENTITY_EQUIPMENT.keySet();
        loop:
        for (EntityType<?> entity : entities) {
            EntityRenderer<?> renderer = event.getRenderer(entity);
            if (renderer == null) {
                continue;
            }
            LivingEntityRenderer livingEntityRenderer = (LivingEntityRenderer<?, ?>) renderer;
            livingEntityRenderer.addLayer(new CuriosLayer<>(livingEntityRenderer));
        }
    }
}
