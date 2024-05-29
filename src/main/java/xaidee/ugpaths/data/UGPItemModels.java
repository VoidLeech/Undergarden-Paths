package xaidee.ugpaths.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

import java.util.function.Supplier;

public class UGPItemModels extends ItemModelProvider {

    public UGPItemModels(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, UGPaths.MOD_ID, helper);
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

    private String blockName(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block.get(), blockName(block));
    }

    public ItemModelBuilder block(Block block, String name) {
        return withExistingParent(blockName(() -> block), modLoc("block/" + name));
    }
}
