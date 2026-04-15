package com.maximumg9.shadow.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@FunctionalInterface
public interface ItemUseCallback {
    ActionResult use(World world, PlayerEntity user, Hand hand);
}
