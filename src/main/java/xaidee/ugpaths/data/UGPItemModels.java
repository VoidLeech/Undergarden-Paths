package xaidee.ugpaths.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import java.util.function.Supplier;

public class UGPItemModels extends ItemModelProvider {

    public UGPItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, UGPaths.MOD_ID, helper);
    }

    @Override
    public String getName() {
        return "Undergarden Paths' Item Models";
    }

    @Override
    public void registerModels() {
        block(UGPRegistry.DEEPSOIL_PATH);
        block(UGPRegistry.ASHEN_DEEPTURF_PATH);
        block(UGPRegistry.FROZEN_DEEPTURF_PATH);
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block.get(), block.get().getRegistryName().getPath());
    }

    public ItemModelBuilder block(Block block, String name) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/" + name));
    }
}
