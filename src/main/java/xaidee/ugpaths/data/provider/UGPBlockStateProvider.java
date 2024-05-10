package xaidee.ugpaths.data.provider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.registry.UGBlocks;
import xaidee.ugpaths.UGPaths;

import java.util.function.Supplier;

public abstract class UGPBlockStateProvider extends BlockStateProvider {

    public UGPBlockStateProvider(PackOutput packOutput, ExistingFileHelper fileHelper) {
        super(packOutput, UGPaths.MOD_ID, fileHelper);
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected String name(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    public ModelFile pathBlockModel(Supplier<? extends Block> block) {
        String blockName = name(block);
        return models().withExistingParent(blockName, "block/dirt_path")
                .texture("particle", this.blockTexture(UGBlocks.DEEPSOIL.get()))
                .texture("top", texture(blockName + "_top"))
                .texture("side", texture(blockName + "_side"))
                .texture("bottom", this.blockTexture(UGBlocks.DEEPSOIL.get()));
    }

    public void block(Supplier<? extends Block> block) {
        this.simpleBlock(block.get());
    }

    public void pathBlock(Supplier<? extends Block> path) {
        this.getVariantBuilder(path.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(pathBlockModel(path)).build());
    }
}
