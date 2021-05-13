package uk.joshiejack.piscary.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.data.database.CSVUtils;
import uk.joshiejack.penguinlib.data.generators.AbstractDatabaseProvider;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;
import uk.joshiejack.piscary.tile.PiscaryTileEntities;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static uk.joshiejack.piscary.data.PiscaryDatabase.SpawnType.EXCLUDE;
import static uk.joshiejack.piscary.data.PiscaryDatabase.SpawnType.REQUIRE;

public class PiscaryDatabase extends AbstractDatabaseProvider {
    public PiscaryDatabase(DataGenerator gen) {
        super(gen, Piscary.MODID);
    }

    @Override
    protected void addDatabaseEntries() {
        addTimeUnitForMachine(PiscaryTileEntities.FISH_TRAP.get(), TimeUnitRegistry.Defaults.HALF_HOUR);
        addTimeUnitForMachine(PiscaryTileEntities.RECYCLER.get(), TimeUnitRegistry.Defaults.HOUR);
        addTimeUnitForMachine(PiscaryTileEntities.HATCHERY.get(), TimeUnitRegistry.Defaults.DAY);
        addLootTableMerge(LootTables.FISHING_FISH);
        addLootTableMerge(LootTables.FISHING_JUNK);
        addLootTableMerge(LootTables.FISHING_TREASURE);
        addBait(PiscaryItems.BAIT, LootTables.FISHING_FISH, 1, 0);
        addAquacultureBait("worm", LootTables.FISHING_FISH, 1, 0);
        //################# FISH HATCHERY DATA ##################//
        addHatcheryEntry(() -> EntityType.COD, 3);
        addHatcheryEntry(() -> EntityType.SALMON, 3);
        addHatcheryEntry(() -> EntityType.PUFFERFISH, 5);
        addHatcheryEntry(() -> EntityType.TROPICAL_FISH, 5);
        addHatcheryEntry(PiscaryEntities.ANCHOVY, 3);
        addHatcheryEntry(PiscaryEntities.ANGELFISH, 5);
        addHatcheryEntry(PiscaryEntities.ANGLERFISH, 7);
        addHatcheryEntry(PiscaryEntities.BASS, 3);
        addHatcheryEntry(PiscaryEntities.BLUE_TANG, 5);
        addHatcheryEntry(PiscaryEntities.BOWFIN, 5);
        addHatcheryEntry(PiscaryEntities.BUTTERFLYFISH, 5);
        addHatcheryEntry(PiscaryEntities.CARP, 3);
        addHatcheryEntry(PiscaryEntities.CATFISH, 5);
        addHatcheryEntry(PiscaryEntities.CHUB, 3);
        addHatcheryEntry(PiscaryEntities.DAMSELFISH, 5);
        addHatcheryEntry(PiscaryEntities.ELECTRIC_RAY, 7);
        addHatcheryEntry(PiscaryEntities.GOLDFISH, 3);
        addHatcheryEntry(PiscaryEntities.KOI, 5);
        addHatcheryEntry(PiscaryEntities.LAMPREY, 5);
        addHatcheryEntry(PiscaryEntities.LUNGFISH, 7);
        addHatcheryEntry(PiscaryEntities.MANTA_RAY, 7);
        addHatcheryEntry(PiscaryEntities.NEON_TETRA, 3);
        addHatcheryEntry(PiscaryEntities.NORTHERN_PIKE, 5);
        addHatcheryEntry(PiscaryEntities.PERCH, 3);
        addHatcheryEntry(PiscaryEntities.PICKEREL, 5);
        addHatcheryEntry(PiscaryEntities.PIRANHA, 7);
        addHatcheryEntry(PiscaryEntities.PUPFISH, 5);
        addHatcheryEntry(PiscaryEntities.SARDINE, 3);
        addHatcheryEntry(PiscaryEntities.SIAMESE_FIGHTING_FISH, 5);
        addHatcheryEntry(PiscaryEntities.SILVER_STRIPE_BLAASOP, 7);
        addHatcheryEntry(PiscaryEntities.STARGAZER, 5);
        addHatcheryEntry(PiscaryEntities.STINGRAY, 7);
        addHatcheryEntry(PiscaryEntities.TROUT, 3);
        addHatcheryEntry(PiscaryEntities.TUNA, 5);
        addHatcheryEntry(PiscaryEntities.WALLEYE, 3);
        //################# AQUACULTURE HATCHERY ##################//
        addAquacultureHatcheryEntry("atlantic_cod", 6);
        addAquacultureHatcheryEntry("blackfish", 5);
        addAquacultureHatcheryEntry("pacific_halibut", 7);
        addAquacultureHatcheryEntry("atlantic_halibut", 7);
        addAquacultureHatcheryEntry("atlantic_herring", 3);
        addAquacultureHatcheryEntry("pink_salmon", 6);
        addAquacultureHatcheryEntry("pollock", 5);
        addAquacultureHatcheryEntry("rainbow_trout", 6);
        addAquacultureHatcheryEntry("bayad", 6);
        addAquacultureHatcheryEntry("boulti", 5);
        addAquacultureHatcheryEntry("capitaine", 7);
        addAquacultureHatcheryEntry("synodontis", 3);
        addAquacultureHatcheryEntry("smallmouth_bass", 5);
        addAquacultureHatcheryEntry("bluegill", 3);
        addAquacultureHatcheryEntry("brown_trout", 5);
        addAquacultureHatcheryEntry("carp", 5);
        addAquacultureHatcheryEntry("catfish", 5);
        addAquacultureHatcheryEntry("gar", 6);
        addAquacultureHatcheryEntry("minnow", 4);
        addAquacultureHatcheryEntry("muskellunge", 7);
        addAquacultureHatcheryEntry("perch", 3);
        addAquacultureHatcheryEntry("arapaima", 7);
        addAquacultureHatcheryEntry("piranha", 7);
        addAquacultureHatcheryEntry("tambaqui", 6);
        addAquacultureHatcheryEntry("brown_shrooma", 2);
        addAquacultureHatcheryEntry("red_shrooma", 2);
        addAquacultureHatcheryEntry("jellyfish", 5);
        addAquacultureHatcheryEntry("red_grouper", 6);
        addAquacultureHatcheryEntry("tuna", 7);
        //################# FISH SPAWN SETTINGS ##################//
        addFishSpawnSettings(PiscaryEntities.ANCHOVY, 22, 8, 12);
        addFishSpawnSettings(PiscaryEntities.ANGELFISH, 3, 3, 5);
        addFishSpawnSettings(PiscaryEntities.ANGLERFISH, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.BASS, 12, 3, 5);
        addFishSpawnSettings(PiscaryEntities.BLUE_TANG, 1, 2, 3);
        addFishSpawnSettings(PiscaryEntities.BOWFIN, 3, 3, 5);
        addFishSpawnSettings(PiscaryEntities.BUTTERFLYFISH, 1, 3, 5);
        addFishSpawnSettings(PiscaryEntities.CARP, 2, 4, 6);
        addFishSpawnSettings(PiscaryEntities.CATFISH, 3, 1, 2);
        addFishSpawnSettings(PiscaryEntities.CHUB, 24, 5, 7);
        addFishSpawnSettings(PiscaryEntities.DAMSELFISH, 3, 2, 3);
        addFishSpawnSettings(PiscaryEntities.ELECTRIC_RAY, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.GOLDFISH, 17, 1, 2);
        addFishSpawnSettings(PiscaryEntities.KOI, 1, 1, 2);
        addFishSpawnSettings(PiscaryEntities.LAMPREY, 4, 1, 1);
        addFishSpawnSettings(PiscaryEntities.LUNGFISH, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.MANTA_RAY, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.MINNOW, 22, 5, 8);
        addFishSpawnSettings(PiscaryEntities.NEON_TETRA, 2, 5, 8);
        addFishSpawnSettings(PiscaryEntities.NORTHERN_PIKE, 1, 1, 2);
        addFishSpawnSettings(PiscaryEntities.PERCH, 21, 3, 5);
        addFishSpawnSettings(PiscaryEntities.PICKEREL, 2, 2, 3);
        addFishSpawnSettings(PiscaryEntities.PIRANHA, 1, 3, 5);
        addFishSpawnSettings(PiscaryEntities.PUPFISH, 4, 3, 5);
        addFishSpawnSettings(PiscaryEntities.SARDINE, 20, 7, 12);
        addFishSpawnSettings(PiscaryEntities.SIAMESE_FIGHTING_FISH, 2, 1, 1);
        addFishSpawnSettings(PiscaryEntities.SILVER_STRIPE_BLAASOP, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.STARGAZER, 2, 1, 1);
        addFishSpawnSettings(PiscaryEntities.STINGRAY, 1, 1, 1);
        addFishSpawnSettings(PiscaryEntities.TROUT, 14, 4, 6);
        addFishSpawnSettings(PiscaryEntities.TUNA, 2, 3, 5);
        addFishSpawnSettings(PiscaryEntities.WALLEYE, 8, 2, 3);
        //################# FISH BIOMES DATA ##################//
        addFishSpawns(PiscaryEntities.ANCHOVY, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.ANGELFISH, REQUIRE, JUNGLE);
        addFishSpawns(PiscaryEntities.ANGLERFISH, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.ANGLERFISH, REQUIRE, SNOWY);
        addFishSpawns(PiscaryEntities.BASS, EXCLUDE, COLD);
        addFishSpawns(PiscaryEntities.BASS, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.BLUE_TANG, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.BLUE_TANG, REQUIRE, HOT);
        addFishSpawns(PiscaryEntities.BOWFIN, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.BOWFIN, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.BUTTERFLYFISH, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.BUTTERFLYFISH, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.CARP, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.CARP, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.CATFISH, REQUIRE, RIVER);
        addFishSpawns(PiscaryEntities.CHUB, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.CHUB, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.DAMSELFISH, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.DAMSELFISH, REQUIRE, HOT);
        addFishSpawns(PiscaryEntities.ELECTRIC_RAY, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.GOLDFISH, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.GOLDFISH, EXCLUDE, WET);
        addFishSpawns(PiscaryEntities.KOI, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.KOI, EXCLUDE, WET);
        addFishSpawns(PiscaryEntities.LAMPREY, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.LAMPREY, EXCLUDE, COLD);
        addFishSpawns(PiscaryEntities.LUNGFISH, EXCLUDE, COLD);
        addFishSpawns(PiscaryEntities.LUNGFISH, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.MANTA_RAY, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.MANTA_RAY, EXCLUDE, SNOWY);
        addFishSpawns(PiscaryEntities.MINNOW, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.NEON_TETRA, REQUIRE, JUNGLE);
        addFishSpawns(PiscaryEntities.NORTHERN_PIKE, REQUIRE, SNOWY);
        addFishSpawns(PiscaryEntities.NORTHERN_PIKE, REQUIRE, RIVER);
        addFishSpawns(PiscaryEntities.PERCH, REQUIRE, RIVER);
        addFishSpawns(PiscaryEntities.PICKEREL, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.PICKEREL, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.PIRANHA, REQUIRE, JUNGLE);
        addFishSpawns(PiscaryEntities.PUPFISH, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.PUPFISH, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.SARDINE, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.SARDINE, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.SIAMESE_FIGHTING_FISH, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.SIAMESE_FIGHTING_FISH, EXCLUDE, OCEAN);
        addFishSpawns(PiscaryEntities.SILVER_STRIPE_BLAASOP, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.SILVER_STRIPE_BLAASOP, REQUIRE, SNOWY);
        addFishSpawns(PiscaryEntities.STARGAZER, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.STARGAZER, REQUIRE, HOT);
        addFishSpawns(PiscaryEntities.STINGRAY, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.TROUT, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.TUNA, REQUIRE, OCEAN);
        addFishSpawns(PiscaryEntities.TUNA, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.WALLEYE, EXCLUDE, HOT);
        addFishSpawns(PiscaryEntities.WALLEYE, EXCLUDE, COLD);
    }

    public enum SpawnType {
        REQUIRE, EXCLUDE
    }

    protected void addAquacultureBait(String name, ResourceLocation lootTable, int speed, int luck) {
        addBait(new ResourceLocation("aquaculture", name).toString(), lootTable.toString(), speed, luck);
    }

    protected void addAquacultureHatcheryEntry(String name, int days) {
        addHatcheryEntry(new ResourceLocation("aquaculture", name).toString(), days);
    }

    protected void addBait(Supplier<Item> item, ResourceLocation lootTable, int speed, int luck) {
        addBait(item.get().getRegistryName().toString(), lootTable.toString(), speed, luck);
    }

    protected void addBait(String itemRegistryName, String lootTable, int speed, int luck) {
        addEntry("bait", "Item,Loot Table,Speed,Luck", CSVUtils.join(itemRegistryName, lootTable, speed, luck));
    }

    protected void addHatcheryEntry(Supplier<EntityType<?>> entity, int cycles) {
        addEntry("hatchery", "Entity,Cycles", CSVUtils.join(entity.get().getRegistryName().toString(), cycles));
    }

    protected void addHatcheryEntry(String entityRegistryName, int days) {
        addEntry("hatchery", "Entity,Cycles", CSVUtils.join(entityRegistryName, days));
    }

    protected void addFishSpawns(Supplier<EntityType<?>> entity, SpawnType type, BiomeDictionary.Type biome) {
        addEntry("fish_spawn_biomes", "Entity,Type,Biome Type",
                CSVUtils.join(Objects.requireNonNull(entity.get().getRegistryName()).toString(), type.name().toLowerCase(Locale.ROOT), biome.getName().toLowerCase(Locale.ROOT)));
    }

    protected void addFishSpawnSettings(Supplier<EntityType<?>> entity, int weight, int min, int max) {
        addEntry("fish_spawn_settings", "Entity,Weight,Min,Max",
                CSVUtils.join(Objects.requireNonNull(entity.get().getRegistryName()).toString(), weight, min, max));
    }
}
