package xaidee.ugpaths.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;
import quek.undergarden.registry.UGBlocks;

public class DeepsoilPathBlock extends DirtPathBlock {

    public DeepsoilPathBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return !this.defaultBlockState().canSurvive(ctx.getLevel(), ctx.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), UGBlocks.DEEPSOIL.get().defaultBlockState(), ctx.getLevel(), ctx.getClickedPos()) : super.getStateForPlacement(ctx);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(world, pos))
            turnToDeepsoil(state, world, pos);
    }

    public static void turnToDeepsoil(BlockState state, Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, pushEntitiesUp(state, UGBlocks.DEEPSOIL.get().defaultBlockState(), world, pos));
    }
}
