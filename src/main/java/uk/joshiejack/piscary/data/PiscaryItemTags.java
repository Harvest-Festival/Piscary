package uk.joshiejack.piscary.data;

import com.google.common.collect.Lists;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.penguinlib.data.PenguinTags;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.item.PiscaryItems;

import javax.annotation.Nullable;
import java.util.List;

public class PiscaryItemTags extends ItemTagsProvider {
    public PiscaryItemTags(DataGenerator generator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagProvider, Piscary.MODID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(PenguinTags.BREAD).add(Items.BREAD);
        List<Item> fishes = Lists.newArrayList(PiscaryItems.ANCHOVY.get(), PiscaryItems.ANGELFISH.get(), PiscaryItems.ANGLERFISH.get(), PiscaryItems.BASS.get()
                , PiscaryItems.BLUE_TANG.get(), PiscaryItems.BOWFIN.get(), PiscaryItems.BUTTERFLYFISH.get(), PiscaryItems.CARP.get()
                , PiscaryItems.CATFISH.get(), PiscaryItems.CHUB.get()
                , PiscaryItems.DAMSELFISH.get(), PiscaryItems.ELECTRIC_RAY.get(), PiscaryItems.GOLDFISH.get()
                , PiscaryItems.KOI.get(), PiscaryItems.LAMPREY.get(), PiscaryItems.LUNGFISH.get(), PiscaryItems.MANTA_RAY.get()
                , PiscaryItems.MINNOW.get(), PiscaryItems.NEON_TETRA.get(), PiscaryItems.NORTHERN_PIKE.get(), PiscaryItems.PERCH.get()
                , PiscaryItems.PICKEREL.get(), PiscaryItems.PIRANHA.get(), PiscaryItems.PUPFISH.get()
                , PiscaryItems.SARDINE.get(), PiscaryItems.SIAMESE_FIGHTING_FISH.get(), PiscaryItems.STARGAZER.get()
                , PiscaryItems.STINGRAY.get(), PiscaryItems.SILVER_STRIPE_BLAASOP.get(), PiscaryItems.TROUT.get(), PiscaryItems.TUNA.get()
                , PiscaryItems.WALLEYE.get(), Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);
        TagsProvider.Builder<Item> rawTag = tag(PenguinTags.RAW_FISHES);
        TagsProvider.Builder<Item> allTag = tag(ItemTags.FISHES);


        fishes.forEach(fish -> {
            rawTag.add(fish);
            allTag.add(fish);
        });

        tag(ItemTags.PIGLIN_LOVED).add(PiscaryItems.GOLDFISH.get());
    }
}
