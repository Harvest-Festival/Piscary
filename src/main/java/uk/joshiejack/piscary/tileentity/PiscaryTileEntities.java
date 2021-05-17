package uk.joshiejack.piscary.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;

@SuppressWarnings("ConstantConditions")
public class PiscaryTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Piscary.MODID);
    public static final RegistryObject<TileEntityType<FishTrapTileEntity>> FISH_TRAP = TILE_ENTITIES.register("fish_trap", () -> TileEntityType.Builder.of(FishTrapTileEntity::new, PiscaryBlocks.FISH_TRAP.get()).build(null));
    public static final RegistryObject<TileEntityType<HatcheryTileEntity>> HATCHERY = TILE_ENTITIES.register("hatchery", () -> TileEntityType.Builder.of(HatcheryTileEntity::new, PiscaryBlocks.HATCHERY.get()).build(null));
    public static final RegistryObject<TileEntityType<RecyclerTileEntity>> RECYCLER = TILE_ENTITIES.register("recycler", () -> TileEntityType.Builder.of(RecyclerTileEntity::new, PiscaryBlocks.RECYCLER.get()).build(null));
}
