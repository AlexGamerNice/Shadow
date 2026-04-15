package com.maximumg9.shadow.modifiers;

import com.maximumg9.shadow.screens.ItemRepresentable;
import com.maximumg9.shadow.util.ItemComponents;
import com.maximumg9.shadow.util.NBTUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
public class AddModifier implements ItemRepresentable {
    private static final Style STYLE = Style.EMPTY.withColor(Formatting.GREEN);
    public String getRawName() {
        return "Add modifier";
    }
    public Style getStyle() { return STYLE; }
    @Override
    public ItemStack getAsItem(RegistryWrapper.WrapperLookup registries) {
        ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET);
        stack.set(
            DataComponentTypes.ITEM_NAME,
            Text.literal(getRawName()).setStyle(getStyle())
        );
        stack.set(
            DataComponentTypes.TOOLTIP_DISPLAY,
            ItemComponents.HIDE_ADDITIONAL_TOOLTIP
        );
        NBTUtil.removeAttributeModifiers(stack);
        return stack;
    }
}
