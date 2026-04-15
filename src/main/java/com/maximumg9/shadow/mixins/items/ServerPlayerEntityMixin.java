package com.maximumg9.shadow.mixins.items;

import com.maximumg9.shadow.Shadow;
import com.maximumg9.shadow.items.Eye;
import com.maximumg9.shadow.util.NBTUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.maximumg9.shadow.util.MiscUtil.getShadow;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @org.spongepowered.asm.mixin.Shadow
    @Final
    private MinecraftServer server;

    @Inject(method = "sendPickup", at = @At("HEAD"))
    private void pickupEnderEye(Entity item, int count, CallbackInfo ci) {
        Shadow shadow = getShadow(Objects.requireNonNull(item.getEntityWorld().getServer()));
        
        if (item instanceof ItemEntity) {
            List<Eye> eyesCopy = new ArrayList<>(shadow.state.eyes);
            for (Eye eye : eyesCopy) {
                if (eye.item().equals(item.getUuid())) {
                    eye.destroy(shadow);
                    shadow.state.eyes.remove(eye);
                }
            }
        }
    }
    
    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    public void dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (NBTUtil.getCustomData(stack).getBoolean(NBTUtil.RESTRICT_MOVEMENT_KEY).orElse(false)) {
            cir.setReturnValue(null);
            cir.cancel();
            ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
            if (self.getInventory().insertStack(stack)) return;
            self.getInventory().setStack(0, stack);
        }
    }
}
