package xaidee.ugpaths;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGCreativeModeTabs;
import xaidee.ugpaths.block.DeepsoilPathBlock;

import java.util.function.Supplier;

public class UGPRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UGPaths.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UGPaths.MOD_ID);

    public static final RegistryObject<Block> DEEPSOIL_PATH = register("deepsoil_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.copy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));
    public static final RegistryObject<Block> ASHEN_DEEPTURF_PATH = register("ashen_deepturf_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.copy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));
    public static final RegistryObject<Block> FROZEN_DEEPTURF_PATH = register("frozen_deepturf_path", () -> new DeepsoilPathBlock(BlockBehaviour.Properties.copy(UGBlocks.DEEPTURF_BLOCK.get()).strength(0.65F).isViewBlocking((state, world, pos) -> true).isSuffocating((state, world, pos) -> true)));

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends B> block) {
        RegistryObject<B> blocks = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties()));
        return blocks;
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == UGCreativeModeTabs.TAB.getKey()) {
            event.accept(DEEPSOIL_PATH);
            event.accept(ASHEN_DEEPTURF_PATH);
            event.accept(FROZEN_DEEPTURF_PATH);
        }
    }
}
