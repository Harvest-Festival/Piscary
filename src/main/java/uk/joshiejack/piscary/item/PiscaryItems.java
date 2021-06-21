package uk.joshiejack.piscary.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;

@SuppressWarnings("unused")
public class PiscaryItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Piscary.MODID);
    //Bait
    public static final RegistryObject<Item> BAIT = ITEMS.register("bait", BaitItem::new);
    //Fish
    public static final RegistryObject<Item> ANCHOVY = ITEMS.register("anchovy", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> ANGELFISH = ITEMS.register("angelfish", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> ANGLERFISH = ITEMS.register("anglerfish", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> BASS = ITEMS.register("bass", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> BLUE_TANG = ITEMS.register("blue_tang", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> BOWFIN = ITEMS.register("bowfin", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> BUTTERFLYFISH = ITEMS.register("butterflyfish", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> CARP = ITEMS.register("carp", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> CATFISH = ITEMS.register("catfish", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> CHUB = ITEMS.register("chub", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> DAMSELFISH = ITEMS.register("damselfish", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> ELECTRIC_RAY = ITEMS.register("electric_ray", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> GOLDFISH = ITEMS.register("goldfish", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> KOI = ITEMS.register("koi", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> LAMPREY = ITEMS.register("lamprey", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> LUNGFISH = ITEMS.register("lungfish", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> MANTA_RAY = ITEMS.register("manta_ray", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> MINNOW = ITEMS.register("minnow", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> NEON_TETRA = ITEMS.register("neon_tetra", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> NORTHERN_PIKE = ITEMS.register("northern_pike", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> PERCH = ITEMS.register("perch", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> PICKEREL = ITEMS.register("pickerel", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> PIRANHA = ITEMS.register("piranha", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> PUPFISH = ITEMS.register("pupfish", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> SARDINE = ITEMS.register("sardine", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> SIAMESE_FIGHTING_FISH = ITEMS.register("siamese_fighting_fish", () -> new Item(new Item.Properties().food(PiscaryFoods.SMALL_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> STINGRAY = ITEMS.register("stingray", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> SILVER_STRIPE_BLAASOP = ITEMS.register("silver_stripe_blaasop", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> TROUT = ITEMS.register("trout", () -> new Item(new Item.Properties().food(PiscaryFoods.MEDIUM_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> TUNA = ITEMS.register("tuna", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> WALLEYE = ITEMS.register("walleye", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));
    public static final RegistryObject<Item> WHITEMARGIN_STARGAZER = ITEMS.register("whitemargin_stargazer", () -> new Item(new Item.Properties().food(PiscaryFoods.LARGE_FISH).tab(Piscary.TAB)));

    //Loot
    public static final RegistryObject<Item> FISH_BONES = ITEMS.register("fish_bones", () -> new Item(new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> FISH_FOSSIL = ITEMS.register("fish_fossil", () -> new Item(new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> OLD_BOOT = ITEMS.register("old_boot", () -> new Item(new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> EMPTY_CAN = ITEMS.register("empty_can", () -> new Item(new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> PIRATE_TREASURE = ITEMS.register("pirate_treasure", () -> new TreasureItem(new Item.Properties().tab(Piscary.TAB)));
    //Meals
    public static final RegistryObject<Item> FISH_FINGERS = ITEMS.register("fish_fingers", () -> new Item(new Item.Properties().food(PiscaryFoods.FISH_FINGERS).tab(Piscary.TAB)));
    public static final RegistryObject<Item> SASHIMI = ITEMS.register("sashimi", () -> new Item(new Item.Properties().food(PiscaryFoods.SASHIMI).tab(Piscary.TAB)));
    public static final RegistryObject<Item> FISH_STEW = ITEMS.register("fish_stew", () -> new Item(new Item.Properties().food(PiscaryFoods.FISH_STEW).tab(Piscary.TAB)));
    public static final RegistryObject<Item> FISH_TRAP = ITEMS.register("fish_trap", () -> new FishTrapItem(PiscaryBlocks.FISH_TRAP.get(), new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> HATCHERY = ITEMS.register("hatchery", () -> new HatcheryItem(PiscaryBlocks.HATCHERY.get(), new Item.Properties().tab(Piscary.TAB)));
    public static final RegistryObject<Item> RECYCLER = ITEMS.register("recycler", () -> new BlockItem(PiscaryBlocks.RECYCLER.get(), new Item.Properties().tab(Piscary.TAB)));
}
