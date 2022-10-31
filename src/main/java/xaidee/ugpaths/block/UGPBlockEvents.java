package xaidee.ugpaths.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGBlocks;
import xaidee.ugpaths.UGPRegistry;
import xaidee.ugpaths.UGPaths;

@Mod.EventBusSubscriber(modid = UGPaths.MOD_ID)
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
