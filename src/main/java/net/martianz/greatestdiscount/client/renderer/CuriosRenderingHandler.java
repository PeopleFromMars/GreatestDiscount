package net.martianz.greatestdiscount.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Optional;
import java.util.function.Supplier;

public class CuriosRenderingHandler implements EquipmentRenderingHandler {

    @Override
    public void registerCurioRenderer(Item item, Supplier<net.martianz.greatestdiscount.client.renderer.CurioRenderer> rendererSupplier) {
        CuriosRendererRegistry.register(item, () -> new CurioRenderer(rendererSupplier.get()));
    }

    @Override
    public @Nullable net.martianz.greatestdiscount.client.renderer.CurioRenderer getCurioRenderer(Item item) {
        Optional<ICurioRenderer> renderer = CuriosRendererRegistry.getRenderer(item);
        if (renderer.isPresent() && renderer.get() instanceof CurioRenderer curioRenderer) {
            return curioRenderer.renderer();
        }
        return null;
    }

    @Override
    public void renderArm(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, HumanoidArm side) {
        InteractionHand hand = side == player.getMainArm() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getCurios().values().forEach(stacksHandler -> {
            if (stacksHandler != null) {
                IDynamicStackHandler stacks = stacksHandler.getStacks();
                IDynamicStackHandler cosmeticStacks = stacksHandler.getCosmeticStacks();

                for (int slot = hand == InteractionHand.MAIN_HAND ? 0 : 1; slot < stacks.getSlots(); slot += 2) {
                    ItemStack stack = cosmeticStacks.getStackInSlot(slot);
                    if (stack.isEmpty() && stacksHandler.getRenders().get(slot)) {
                        stack = stacks.getStackInSlot(slot);
                    }
                }
            }
        }));
    }

    public record CurioRenderer(net.martianz.greatestdiscount.client.renderer.CurioRenderer renderer) implements ICurioRenderer {

        @Override
        public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            renderer.renderVisible(stack, slotContext.entity(), slotContext.index(), poseStack, multiBufferSource, light, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        }
    }
}
