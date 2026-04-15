package com.maximumg9.shadow.util;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Shared item component values for API migrations (e.g. former {@code HIDE_ADDITIONAL_TOOLTIP}).
 */
public abstract class ItemComponents {
    private static final Set<String> TOOLTIP_ALWAYS_VISIBLE = Set.of(
        "DEFAULT_ITEM_COMPONENTS",
        "TOOLTIP_DISPLAY",
        "TOOLTIP_STYLE",
        "ITEM_NAME",
        "CUSTOM_NAME",
        "LORE",
        "RARITY",
        "CUSTOM_DATA",
        "MAX_STACK_SIZE",
        "MAX_DAMAGE",
        "DAMAGE",
        "ENCHANTMENT_GLINT_OVERRIDE",
        "CREATIVE_SLOT_LOCK"
    );

    public static final TooltipDisplayComponent HIDE_ADDITIONAL_TOOLTIP = buildHideAdditionalTooltip();

    private static TooltipDisplayComponent buildHideAdditionalTooltip() {
        TooltipDisplayComponent display = TooltipDisplayComponent.DEFAULT;
        for (Field field : DataComponentTypes.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers()) || field.getType() != ComponentType.class) {
                continue;
            }
            if (TOOLTIP_ALWAYS_VISIBLE.contains(field.getName())) {
                continue;
            }
            try {
                @SuppressWarnings("unchecked")
                ComponentType<?> type = (ComponentType<?>) field.get(null);
                display = display.with(type, true);
            } catch (ReflectiveOperationException ignored) {
            }
        }
        return display;
    }

    private ItemComponents() {
    }
}
