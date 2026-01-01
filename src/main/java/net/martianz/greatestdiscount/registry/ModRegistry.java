package net.martianz.greatestdiscount.registry;

import com.hollingsworth.arsnouveau.common.items.curios.DiscountRing;
import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import net.martianz.greatestdiscount.Config;
import net.martianz.greatestdiscount.GreatestDiscount;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(GreatestDiscount.MODID);

    public static final ItemRegistryWrapper<DiscountRing> RING_OF_GREATER_DISCOUNT = register("ring_of_greatest_discount", () -> new DiscountRing() {
        @Override
        public int getManaDiscount() {
            return Config.GREATEST_DISCOUNT_VALUE.getAsInt();
        }
    });

    public static <T extends Item> ItemRegistryWrapper<T> register(String name, Supplier<T> item) {
        return new ItemRegistryWrapper<>(ITEMS.register(name, item));
    }

    public static void registerRegistries(IEventBus bus) {
        ITEMS.register(bus);
    }
}
