package uk.joshiejack.piscary.tile;

import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.crafting.RecyclerRecipe;

public class RecyclerTileEntity extends AbstractIRecipeMachine<RecyclerRecipe> {
    public RecyclerTileEntity() {
        super(PiscaryTileEntities.RECYCLER.get(), PiscaryRegistries.RECYCLER);
    }
}
