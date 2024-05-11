package xaidee.ugpaths.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.event.level.BlockEvent;
import quek.undergarden.registry.UGBlocks;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

@EventBusSubscriber(modid = UGPaths.MOD_ID)
public class UGPBlockEvents {

    @SubscribeEvent
    public static void blockToolInteractions(BlockEvent.BlockToolModificationEvent event) {
        ToolAction action = event.getToolAction();
        BlockState state = event.getState();
        UseOnContext ctx = event.getContext();
        if (!event.isSimulated()) {
            if (action == ToolActions.SHOVEL_FLATTEN) {
                if (state.is(UGBlocks.DEEPTURF_BLOCK.get()) || state.is(UGBlocks.DEEPSOIL.get()) || state.is(UGBlocks.COARSE_DEEPSOIL.get()))
                    event.setFinalState(UGPRegistry.DEEPSOIL_PATH.get().defaultBlockState());

                if (state.is(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()))
                    event.setFinalState(UGPRegistry.ASHEN_DEEPTURF_PATH.get().defaultBlockState());

                if (state.is(UGBlocks.FROZEN_DEEPTURF_BLOCK.get()))
                    event.setFinalState(UGPRegistry.FROZEN_DEEPTURF_PATH.get().defaultBlockState());
            }
        }
    }
}
