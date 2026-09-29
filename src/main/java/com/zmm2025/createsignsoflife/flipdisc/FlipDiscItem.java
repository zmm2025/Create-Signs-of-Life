package com.zmm2025.createsignsoflife.flipdisc;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public final class FlipDiscItem extends BlockItem {
    public FlipDiscItem(Block block, Properties properties) { super(block, properties); }
    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.create_signs_of_life.flip_disc_display").withStyle(ChatFormatting.GRAY));
    }
}
