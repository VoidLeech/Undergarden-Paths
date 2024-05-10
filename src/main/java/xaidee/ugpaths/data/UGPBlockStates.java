package xaidee.ugpaths.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.data.provider.UGPBlockStateProvider;

public class UGPBlockStates extends UGPBlockStateProvider {


    public UGPBlockStates(PackOutput packOutput, ExistingFileHelper fileHelper) {
        super(packOutput, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        pathBlock(UGPRegistry.DEEPSOIL_PATH);
        pathBlock(UGPRegistry.ASHEN_DEEPTURF_PATH);
        pathBlock(UGPRegistry.FROZEN_DEEPTURF_PATH);
    }
}
