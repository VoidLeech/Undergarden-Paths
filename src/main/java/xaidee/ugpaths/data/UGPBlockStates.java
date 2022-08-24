package xaidee.ugpaths.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.data.provider.UGPBlockStateProvider;

public class UGPBlockStates extends UGPBlockStateProvider {


    public UGPBlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        pathBlock(UGPRegistry.DEEPSOIL_PATH);
        pathBlock(UGPRegistry.ASHEN_DEEPTURF_PATH);
        pathBlock(UGPRegistry.FROZEN_DEEPTURF_PATH);
    }
}
