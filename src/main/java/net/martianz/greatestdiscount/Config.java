package net.martianz.greatestdiscount;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue GREATEST_DISCOUNT_VALUE = BUILDER
            .comment("Amount of reduction given by the Greatest Discount Ring")
            .defineInRange("greatestDiscount", 40, 20, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue GREATER_REGEN_VALUE = BUILDER
            .comment("Amount of mana regeneration given by the Greater Regen Amulet")
            .defineInRange("greaterRegen", 6, 3, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue GREATEST_REGEN_VALUE = BUILDER
            .comment("Amount of mana regeneration given by the Greatest Regen Scarf")
            .defineInRange("greatestRegen", 12, 3, Integer.MAX_VALUE);


    static final ModConfigSpec SPEC = BUILDER.build();
}
