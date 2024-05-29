package xaidee.ugpaths.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class UGPBlockTags extends BlockTagsProvider {

    public UGPBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper helper) {
        super(packOutput, lookupProvider, UGPaths.MOD_ID, helper);
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                UGPRegistry.DEEPSOIL_PATH.get(),
                UGPRegistry.ASHEN_DEEPTURF_PATH.get(),
                UGPRegistry.FROZEN_DEEPTURF_PATH.get()
        );
    }
}
