package com.zmm2025.createsignsoflife.flipdisc;

import java.util.List;
import com.simibubi.create.api.behaviour.display.DisplayTarget;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

public class FlipDiscDisplayTarget extends DisplayTarget {
    @Override public void acceptText(int line, List<MutableComponent> text, DisplayLinkContext context) {
        FlipDiscAssembly group = FlipDiscAssembly.find(context.level(), context.getTargetPos());
        if (group == null) return;
        for (int i = 0; i < text.size() && line + i < group.height(); i++) {
            if (i > 0 && isReserved(line + i, group.controller(), context)) break;
            if (i == 0) reserve(line, group.controller(), context);
            group.controller().setLine(group, line + i, text.get(i).getString());
        }
    }
    @Override public DisplayTargetStats provideStats(DisplayLinkContext context) {
        FlipDiscAssembly group = FlipDiscAssembly.find(context.level(), context.getTargetPos());
        return new DisplayTargetStats(group == null ? 1 : group.height(), group == null ? 1 : Math.max(1, group.width() * 8 / 6), this);
    }
    @Override public AABB getMultiblockBounds(LevelAccessor level, BlockPos pos) {
        FlipDiscAssembly group = FlipDiscAssembly.find(level, pos);
        AABB result = new AABB(pos);
        if (group != null) for (FlipDiscBlockEntity tile : group.tiles()) result = result.minmax(new AABB(tile.getBlockPos()));
        return result;
    }
}
