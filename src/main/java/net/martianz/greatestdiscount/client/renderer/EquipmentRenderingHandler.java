package net.martianz.greatestdiscount.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface EquipmentRenderingHandler {

    void registerCurioRenderer(Item item, Supplier<CurioRenderer> rendererSupplier);

    @Nullable
    CurioRenderer getCurioRenderer(Item item);

    void renderArm(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, HumanoidArm side);

}
