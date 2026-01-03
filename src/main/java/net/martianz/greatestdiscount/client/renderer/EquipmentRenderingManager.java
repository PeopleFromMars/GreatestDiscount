package net.martianz.greatestdiscount.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

public class EquipmentRenderingManager {

    private static final Set<EquipmentRenderingHandler> RENDERING_HANDLERS = new LinkedHashSet<>();

    public static void register(EquipmentRenderingHandler integration) {
        RENDERING_HANDLERS.add(integration);
    }

    public static void registerCurioRenderer(Item item, Supplier<CurioRenderer> rendererFactory) {
        for (EquipmentRenderingHandler handler : RENDERING_HANDLERS) {
            handler.registerCurioRenderer(item, rendererFactory);
        }
    }

    @Nullable
    public static CurioRenderer getCurioRenderer(Item item) {
        for (EquipmentRenderingHandler handler : RENDERING_HANDLERS) {
            CurioRenderer renderer = handler.getCurioRenderer(item);

            if (renderer != null) {
                return renderer;
            }
        }

        return null;
    }

    public static void renderArm(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, HumanoidArm side) {
        for (EquipmentRenderingHandler handler : RENDERING_HANDLERS) {
            handler.renderArm(matrixStack, buffer, light, player, side);
        }
    }
}
