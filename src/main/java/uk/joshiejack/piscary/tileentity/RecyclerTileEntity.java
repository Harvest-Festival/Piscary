package uk.joshiejack.piscary.tileentity;

import net.minecraft.util.SoundCategory;
import uk.joshiejack.penguinlib.tile.machine.AbstractIRecipeMachine;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.crafting.RecyclerRecipe;

public class RecyclerTileEntity extends AbstractIRecipeMachine<RecyclerRecipe> {

    public RecyclerTileEntity() {
        super(PiscaryTileEntities.RECYCLER.get(), PiscaryRegistries.RECYCLER);
    }
    @Override
    public void tick() {
        super.tick();
        assert this.level != null;
        if (this.level.getGameTime() % 50L == 1L) {
            if (isActive()) {
                double d0 = (double) this.worldPosition.getX() + 0.5D;
                double d1 = (double) this.worldPosition.getY() + 0.5D;
                double d2 = (double) this.worldPosition.getZ() + 0.5D;
                level.playSound(null, d0, d1, d2, Piscary.PiscarySounds.RECYCLER.get(), SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

}
