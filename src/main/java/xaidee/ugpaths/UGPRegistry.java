package xaidee.ugpaths;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGCreativeModeTabs;
import xaidee.ugpaths.block.DeepsoilPathBlock;

import java.util.function.Supplier;

public class UGPRegistry {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(UGPaths.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UGPaths.MOD_ID);

    public static final Supplier<Block> DEEPSOIL_PATH = register("deepsoil_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.ofFullCopy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));
    public static final Supplier<Block> ASHEN_DEEPTURF_PATH = register("ashen_deepturf_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.ofFullCopy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));
    public static final Supplier<Block> FROZEN_DEEPTURF_PATH = register("frozen_deepturf_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.ofFullCopy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));

    private static <B extends Block> Supplier<B> register(String name, Supplier<? extends B> block) {
        Supplier<B> blocks = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties()));
        return blocks;
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == UGCreativeModeTabs.TAB.getKey()) {
            event.accept(DEEPSOIL_PATH.get());
            event.accept(ASHEN_DEEPTURF_PATH.get());
            event.accept(FROZEN_DEEPTURF_PATH.get());
        }
    }
}
