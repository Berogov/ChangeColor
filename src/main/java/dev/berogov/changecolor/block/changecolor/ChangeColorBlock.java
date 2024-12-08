package dev.berogov.changecolor.block.changecolor;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
public class ChangeColorBlock extends Block {

    public static final EnumProperty<ColorState> NORTH = EnumProperty.create("north", ColorState.class);
    public static final EnumProperty<ColorState> SOUTH = EnumProperty.create("south", ColorState.class);
    public static final EnumProperty<ColorState> EAST = EnumProperty.create("east", ColorState.class);
    public static final EnumProperty<ColorState> WEST = EnumProperty.create("west", ColorState.class);
    public static final EnumProperty<ColorState> UP = EnumProperty.create("up", ColorState.class);
    public static final EnumProperty<ColorState> DOWN = EnumProperty.create("down", ColorState.class);

    public ChangeColorBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(1.5F, 6.0F)
                .setRequiresTool());

        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, ColorState.WHITE)
                .with(SOUTH, ColorState.WHITE)
                .with(EAST, ColorState.WHITE)
                .with(WEST, ColorState.WHITE)
                .with(UP, ColorState.WHITE)
                .with(DOWN, ColorState.WHITE));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote || handIn != Hand.MAIN_HAND) {
            return ActionResultType.SUCCESS;
        }

        EnumProperty<ColorState> targetSide = getSideProperty(hit);
        if (targetSide != null) {
            ColorState currentColor = state.get(targetSide);
            ColorState newColor = (currentColor == ColorState.WHITE) ? ColorState.BLACK : ColorState.WHITE;

            worldIn.setBlockState(pos, state.with(targetSide, newColor), 3);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    private EnumProperty<ColorState> getSideProperty(BlockRayTraceResult hit) {
        switch (hit.getFace()) {
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            default:
                return null;
        }
    }
}