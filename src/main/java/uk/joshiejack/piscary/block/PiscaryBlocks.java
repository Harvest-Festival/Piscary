package uk.joshiejack.piscary.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;

@SuppressWarnings("unused")
public class PiscaryBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Piscary.MODID);
    public static final RegistryObject<Block> FISH_TRAP = BLOCKS.register("fish_trap", FishTrapBlock::new);
    public static final RegistryObject<Block> HATCHERY = BLOCKS.register("hatchery", HatcheryBlock::new);
    public static final RegistryObject<Block> RECYCLER = BLOCKS.register("recycler", RecyclerBlock::new);
}
