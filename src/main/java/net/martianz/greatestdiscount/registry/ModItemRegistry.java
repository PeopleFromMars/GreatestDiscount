package net.martianz.greatestdiscount.registry;

import com.hollingsworth.arsnouveau.common.items.curios.AbstractManaCurio;
import com.hollingsworth.arsnouveau.common.items.curios.DiscountRing;
import com.hollingsworth.arsnouveau.setup.registry.CreativeTabRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import net.martianz.greatestdiscount.Config;
import net.martianz.greatestdiscount.GreatestDiscount;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(GreatestDiscount.MODID);

    public static final ItemRegistryWrapper<DiscountRing> RING_OF_GREATEST_DISCOUNT = register("ring_of_greatest_discount", () -> new DiscountRing() {
        @Override
        public int getManaDiscount() {
            return Config.GREATEST_DISCOUNT_VALUE.getAsInt();
        }
    });

    public static final ItemRegistryWrapper<AbstractManaCurio> AMULET_OF_GREATER_MANA_REGEN = register("amulet_of_greater_mana_regen", () -> new AbstractManaCurio() {
        @Override
        public int getManaRegenBonus(ItemStack i) {
            return Config.GREATER_REGEN_VALUE.getAsInt();
        }
    });

    public static final ItemRegistryWrapper<AbstractManaCurio> SCARF_OF_GREATEST_MANA_REGEN = register("scarf_of_greatest_mana_regen", () -> new AbstractManaCurio() {
        @Override
        public int getManaRegenBonus(ItemStack i) {
            return Config.GREATEST_REGEN_VALUE.getAsInt();
        }
    });

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeTabRegistry.BLOCKS.getKey()) {
            event.accept(ModItemRegistry.RING_OF_GREATEST_DISCOUNT.get());
            event.accept(ModItemRegistry.AMULET_OF_GREATER_MANA_REGEN.get());
            event.accept(ModItemRegistry.SCARF_OF_GREATEST_MANA_REGEN.get());
        }
    }

    public static <T extends Item> ItemRegistryWrapper<T> register(String name, Supplier<T> item) {
        return new ItemRegistryWrapper<>(ITEMS.register(name, item));
    }

    public static void registerRegistries(IEventBus bus) {
        ITEMS.register(bus);
    }
}
