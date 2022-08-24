package xaidee.ugpaths.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import javax.annotation.Nullable;

public class UGPBlockTags extends BlockTagsProvider {

    public UGPBlockTags(DataGenerator generator, @Nullable ExistingFileHelper helper) {
        super(generator, UGPaths.MOD_ID, helper);
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Block Tags";
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                UGPRegistry.DEEPSOIL_PATH.get(),
                UGPRegistry.ASHEN_DEEPTURF_PATH.get(),
                UGPRegistry.FROZEN_DEEPTURF_PATH.get()
        );
    }
}
