package com.maximumg9.shadow.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.tick.ChunkTickScheduler;
import org.jetbrains.annotations.Nullable;

public class FakeChunk extends WorldChunk {
    private final Chunk backing;

    public FakeChunk(Chunk backingChunk) {
        super(
            ((WorldChunk) backingChunk).getWorld(),
            backingChunk.getPos(),
            backingChunk.getUpgradeData(),
            new ChunkTickScheduler<>(),
            new ChunkTickScheduler<>(),
            backingChunk.getInhabitedTime(),
            backingChunk.getSectionArray(),
            (wc) -> {},
            backingChunk.getBlendingData()
        );
        this.backing = backingChunk;
    }

    @Nullable
    @Override
    public BlockState setBlockState(BlockPos pos, BlockState state, int flags) {
        return state;
    }

    @Override
    public void setBlockEntity(BlockEntity blockEntity) {
    }

    @Override
    public void addEntity(Entity entity) {
    }

    @Override
    public ChunkStatus getStatus() {
        return this.backing.getStatus();
    }

    @Override
    public void removeBlockEntity(BlockPos pos) {
    }

    @Nullable
    @Override
    public NbtCompound getPackedBlockEntityNbt(BlockPos pos, RegistryWrapper.WrapperLookup registryLookup) {
        return new NbtCompound();
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        return Fluids.EMPTY.getDefaultState();
    }
}
