package com.maximumg9.shadow.util;

import net.minecraft.command.permission.LeveledPermissionPredicate;
import net.minecraft.command.permission.Permission;
import net.minecraft.command.permission.PermissionLevel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

public abstract class PermissionUtil {
    public static boolean hasPermissionLevel(ServerCommandSource source, int level) {
        return source.getPermissions().hasPermission(new Permission.Level(PermissionLevel.fromLevel(level)));
    }

    public static boolean hasPermissionLevel(PlayerEntity player, int level) {
        return player.getPermissions().hasPermission(new Permission.Level(PermissionLevel.fromLevel(level)));
    }

    public static boolean hasPermissionLevel(LeveledPermissionPredicate predicate, int level) {
        return predicate.hasPermission(new Permission.Level(PermissionLevel.fromLevel(level)));
    }

    private PermissionUtil() {
    }
}
