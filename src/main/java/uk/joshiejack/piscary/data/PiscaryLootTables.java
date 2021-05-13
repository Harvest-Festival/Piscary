package uk.joshiejack.piscary.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.data.loot.FishingLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.Alternative;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.Inverted;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;
import uk.joshiejack.piscary.loot.BiomeTypeLocationCheck;
import uk.joshiejack.piscary.loot.BiomeTypePredicate;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PiscaryLootTables extends LootTableProvider {
    public PiscaryLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
        map.forEach((name, table) -> LootTableManager.validate(validationtracker, name, table));
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Fishing::new, LootParameterSets.FISHING), Pair.of(Entities::new, LootParameterSets.ENTITY));
    }

    public static class Entities extends EntityLootTables {
        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITIES.getValues().stream()
                    .filter((block) -> Piscary.MODID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                    .collect(Collectors.toList());
        }

        @Override
        protected void addTables() {
            this.addFishLootTable(PiscaryEntities.ANCHOVY, PiscaryItems.ANCHOVY, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.ANGELFISH, PiscaryItems.ANGELFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.ANGLERFISH, PiscaryItems.ANGLERFISH, Items.GLOWSTONE_DUST);
            this.addFishLootTable(PiscaryEntities.BASS, PiscaryItems.BASS, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.BLUE_TANG, PiscaryItems.BLUE_TANG, Items.BLUE_DYE);
            this.addFishLootTable(PiscaryEntities.BOWFIN, PiscaryItems.BOWFIN, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.BUTTERFLYFISH, PiscaryItems.BUTTERFLYFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.CARP, PiscaryItems.CARP, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.CATFISH, PiscaryItems.CATFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.CHUB, PiscaryItems.CHUB, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.DAMSELFISH, PiscaryItems.DAMSELFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.ELECTRIC_RAY, PiscaryItems.ELECTRIC_RAY, Items.REDSTONE);
            this.addFishLootTable(PiscaryEntities.GOLDFISH, PiscaryItems.GOLDFISH, Items.GOLD_NUGGET);
            this.addFishLootTable(PiscaryEntities.KOI, PiscaryItems.KOI, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.LAMPREY, PiscaryItems.LAMPREY, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.LUNGFISH, PiscaryItems.LUNGFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.MANTA_RAY, PiscaryItems.MANTA_RAY, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.MINNOW, PiscaryItems.MINNOW, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.NEON_TETRA, PiscaryItems.NEON_TETRA, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.NORTHERN_PIKE, PiscaryItems.NORTHERN_PIKE, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.PERCH, PiscaryItems.PERCH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.PICKEREL, PiscaryItems.PICKEREL, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.PIRANHA, PiscaryItems.PIRANHA, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.PUPFISH, PiscaryItems.PUPFISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.SARDINE, PiscaryItems.SARDINE, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.SIAMESE_FIGHTING_FISH, PiscaryItems.SIAMESE_FIGHTING_FISH, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.STARGAZER, PiscaryItems.STARGAZER, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.STINGRAY, PiscaryItems.STINGRAY, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.SILVER_STRIPE_BLAASOP, PiscaryItems.SILVER_STRIPE_BLAASOP, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.TROUT, PiscaryItems.TROUT, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.TUNA, PiscaryItems.TUNA, Items.BONE_MEAL);
            this.addFishLootTable(PiscaryEntities.WALLEYE, PiscaryItems.WALLEYE, Items.BONE_MEAL);
        }

        private void addFishLootTable(RegistryObject<EntityType<?>> type, RegistryObject<Item> fish, Item bonus) {
            this.add(type.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(fish.get()).apply(SetCount.setCount(ConstantRange.exactly(1)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(bonus)).when(RandomChance.randomChance(0.05F))));
        }
    }

    public static class Blocks extends BlockLootTables {
        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream()
                    .filter((block) -> Piscary.MODID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                    .collect(Collectors.toList());
        }

        @Override
        protected void addTables() {
            dropSelf(PiscaryBlocks.RECYCLER.get());
            dropSelf(PiscaryBlocks.HATCHERY.get());
            dropSelf(PiscaryBlocks.FISH_TRAP.get());
        }
    }

    public static class Fishing extends FishingLootTables {
        public static final ILootCondition.IBuilder IN_OCEAN_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.OCEAN));
        public static final ILootCondition.IBuilder IN_COLD_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.COLD));
        public static final ILootCondition.IBuilder IN_HOT_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.HOT));
        public static final ILootCondition.IBuilder IN_SNOWY_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.SNOWY));
        public static final ILootCondition.IBuilder IN_RIVER_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.RIVER));
        public static final ILootCondition.IBuilder IN_SWAMPY_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.WET));
        public static final ILootCondition.IBuilder IN_JUNGLE_BIOME = BiomeTypeLocationCheck.checkBiomeType(BiomeTypePredicate.Builder.type().setBiomeType(BiomeDictionary.Type.JUNGLE));

        @Override
        public void accept(@Nonnull BiConsumer<ResourceLocation, LootTable.Builder> builder) {
            builder.accept(new ResourceLocation(Piscary.MODID, "gameplay/fishing/fish"), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.ANCHOVY.get()).setWeight(32).when(IN_OCEAN_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.ANGELFISH.get()).setWeight(3).when(IN_JUNGLE_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.ANGLERFISH.get()).setWeight(1).when(IN_OCEAN_BIOME).when(IN_SNOWY_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.BASS.get()).setWeight(12).when(Inverted.invert(Alternative.alternative(IN_COLD_BIOME, IN_HOT_BIOME))))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.BLUE_TANG.get()).setWeight(1).when(IN_OCEAN_BIOME).when(IN_HOT_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.BOWFIN.get()).setWeight(3).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.BUTTERFLYFISH.get()).setWeight(1).when(IN_OCEAN_BIOME).when(IN_HOT_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.CARP.get()).setWeight(2).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.CATFISH.get()).setWeight(3).when(IN_RIVER_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.CHUB.get()).setWeight(54).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.DAMSELFISH.get()).setWeight(3).when(IN_OCEAN_BIOME).when(IN_HOT_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.ELECTRIC_RAY.get()).setWeight(1).when(IN_OCEAN_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.GOLDFISH.get()).setWeight(37).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_SWAMPY_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.KOI.get()).setWeight(1).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_SWAMPY_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.LAMPREY.get()).setWeight(4).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_COLD_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.LUNGFISH.get()).setWeight(1).when(Inverted.invert(IN_COLD_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.MANTA_RAY.get()).setWeight(1).when(IN_OCEAN_BIOME).when(Inverted.invert(IN_SNOWY_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.MINNOW.get()).setWeight(102).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.NEON_TETRA.get()).setWeight(2).when(IN_JUNGLE_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.NORTHERN_PIKE.get()).setWeight(1).when(IN_SNOWY_BIOME).when(IN_RIVER_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.PERCH.get()).setWeight(21).when(IN_RIVER_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.PICKEREL.get()).setWeight(2).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.PIRANHA.get()).setWeight(1).when(IN_JUNGLE_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.PUPFISH.get()).setWeight(4).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.SARDINE.get()).setWeight(30).when(IN_OCEAN_BIOME).when(Inverted.invert(IN_HOT_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.SIAMESE_FIGHTING_FISH.get()).setWeight(2).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_OCEAN_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.STARGAZER.get()).setWeight(2).when(IN_HOT_BIOME).when(IN_OCEAN_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.STINGRAY.get()).setWeight(1).when(IN_OCEAN_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.SILVER_STRIPE_BLAASOP.get()).setWeight(1).when(IN_OCEAN_BIOME).when(IN_SNOWY_BIOME))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.TROUT.get()).setWeight(14).when(Inverted.invert(IN_HOT_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.TUNA.get()).setWeight(2).when(IN_OCEAN_BIOME).when(Inverted.invert(IN_HOT_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.WALLEYE.get()).setWeight(8).when(Inverted.invert(IN_HOT_BIOME)).when(Inverted.invert(IN_COLD_BIOME)))
            ));

            builder.accept(new ResourceLocation(Piscary.MODID, "gameplay/fishing/junk"), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.FISH_BONES.get()).setWeight(17))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.OLD_BOOT.get()).setWeight(14))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.TIN_CAN.get()).setWeight(16))
            ));

            builder.accept(new ResourceLocation(Piscary.MODID, "gameplay/fishing/treasure"), LootTable.lootTable().withPool(LootPool.lootPool()
                    .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(5))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.FOSSIL.get()).setWeight(10).when(IN_OCEAN_BIOME).when(Inverted.invert(IN_SNOWY_BIOME)))
                    .add(ItemLootEntry.lootTableItem(PiscaryItems.TREASURE.get()).setWeight(1).when(IN_OCEAN_BIOME).when(Inverted.invert(IN_SNOWY_BIOME)))
            ));
        }
    }
}
