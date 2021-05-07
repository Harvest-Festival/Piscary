package uk.joshiejack.piscary.tile;

import uk.joshiejack.penguinlib.tile.machine.TileMachineRegistry;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;

public class RecyclerTileEntity extends TileMachineRegistry {
    public RecyclerTileEntity() {
        super(PiscaryTileEntities.RECYCLER.get(), PiscaryRegistries.RECYCLER, "hour");
    }
}
