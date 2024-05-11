package xaidee.ugpaths.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import quek.undergarden.data.provider.UGBlockLootTableProvider;
import quek.undergarden.registry.UGBlocks;
import xaidee.ugpaths.UGPRegistry;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UGPLootTables {

    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider){
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)
        ), lookupProvider);
    }

    public static class Blocks extends UGBlockLootTableProvider {
        public Blocks() {
        }

        protected void addTables() {
            dropOther(UGPRegistry.DEEPSOIL_PATH, UGBlocks.DEEPSOIL.get().asItem());
            dropOther(UGPRegistry.ASHEN_DEEPTURF_PATH, UGBlocks.DEEPSOIL.get().asItem());
            dropOther(UGPRegistry.FROZEN_DEEPTURF_PATH, UGBlocks.DEEPSOIL.get().asItem());
        }

        @Override
        protected void generate() {
            addTables();
        }

        protected Iterable<Block> getKnownBlocks() {
            return UGPRegistry.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
