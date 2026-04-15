package com.maximumg9.shadow.config;

import com.maximumg9.shadow.saving.Saveable;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class MaxCooldownManager implements Saveable {
    
    private final Object2LongOpenHashMap<Identifier> cooldownMap = new Object2LongOpenHashMap<>();
    
    public MaxCooldownManager() { }
    
    public void readNBT(NbtCompound nbt) {
        for (String key : nbt.getKeys()) {
            Identifier id = Identifier.tryParse(key);
            if (id == null) continue;
            nbt.getLong(key).ifPresent(value -> cooldownMap.put(id, value));
        }
    }
    
    public long getMaxCooldown(Identifier id, long defaultMaxCooldown) {
        return cooldownMap.putIfAbsent(id, defaultMaxCooldown);
    }
    
    public NbtCompound writeNBT(NbtCompound nbt) {
        cooldownMap.object2LongEntrySet().fastForEach((entry) ->
            nbt.putLong(entry.getKey().toString(), entry.getLongValue())
        );
        return nbt;
    }
}
