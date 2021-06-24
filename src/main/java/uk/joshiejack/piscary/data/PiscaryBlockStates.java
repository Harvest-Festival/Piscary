package uk.joshiejack.piscary.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.block.RecyclerBlock;

public class PiscaryBlockStates extends BlockStateProvider {
    public PiscaryBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Piscary.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile upper = models().getExistingFile(new ResourceLocation(Piscary.MODID, "recycler_upper"));
        ModelFile lower = models().getExistingFile(new ResourceLocation(Piscary.MODID, "recycler_lower"));

        VariantBlockStateBuilder builder = getVariantBuilder(PiscaryBlocks.RECYCLER.get());
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.UPPER).with(RecyclerBlock.FACING, Direction.WEST).modelForState().modelFile(upper).rotationY(90).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.UPPER).with(RecyclerBlock.FACING, Direction.EAST).modelForState().modelFile(upper).rotationY(270).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.UPPER).with(RecyclerBlock.FACING, Direction.SOUTH).modelForState().modelFile(upper).rotationY(0).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.UPPER).with(RecyclerBlock.FACING, Direction.NORTH).modelForState().modelFile(upper).rotationY(180).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.LOWER).with(RecyclerBlock.FACING, Direction.WEST).modelForState().modelFile(lower).rotationY(90).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.LOWER).with(RecyclerBlock.FACING, Direction.EAST).modelForState().modelFile(lower).rotationY(270).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.LOWER).with(RecyclerBlock.FACING, Direction.SOUTH).modelForState().modelFile(lower).rotationY(0).addModel();
        builder.partialState().with(RecyclerBlock.HALF, DoubleBlockHalf.LOWER).with(RecyclerBlock.FACING, Direction.NORTH).modelForState().modelFile(lower).rotationY(180).addModel();
    }
}
