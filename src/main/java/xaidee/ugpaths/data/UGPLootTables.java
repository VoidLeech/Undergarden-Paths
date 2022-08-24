package xaidee.ugpaths.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import quek.undergarden.data.provider.UGBlockLootTableProvider;
import quek.undergarden.registry.UGBlocks;
import xaidee.ugpaths.UGPRegistry;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UGPLootTables extends LootTableProvider {

    public UGPLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Loot Tables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {}

    public static class Blocks extends UGBlockLootTableProvider {
        public Blocks() {
        }

        @Override
        protected void addTables() {
            dropOther(UGPRegistry.DEEPSOIL_PATH, UGBlocks.DEEPSOIL.get().asItem());
            dropOther(UGPRegistry.ASHEN_DEEPTURF_PATH, UGBlocks.DEEPSOIL.get().asItem());
            dropOther(UGPRegistry.FROZEN_DEEPTURF_PATH, UGBlocks.DEEPSOIL.get().asItem());
        }

        protected Iterable<Block> getKnownBlocks() {
            return UGPRegistry.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
