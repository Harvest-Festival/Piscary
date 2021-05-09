package uk.joshiejack.piscary.tile;

import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;

public class RecyclerTileEntity extends AbstractIRecipeMachine {
    public RecyclerTileEntity() {
        super(PiscaryTileEntities.RECYCLER.get(), PiscaryRegistries.RECYCLER, "hour");
    }
}
