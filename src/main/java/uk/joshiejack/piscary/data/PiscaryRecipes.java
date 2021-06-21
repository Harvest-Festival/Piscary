package uk.joshiejack.piscary.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import uk.joshiejack.penguinlib.item.PenguinItems;
import uk.joshiejack.penguinlib.util.PenguinTags;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.crafting.RecyclerRecipe;
import uk.joshiejack.piscary.item.PiscaryItems;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class PiscaryRecipes extends RecipeProvider {
    public PiscaryRecipes(DataGenerator generator) {
        super(generator);
    }

    private ResourceLocation rl (String name) {
        return new ResourceLocation(Piscary.MODID, name);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        //Food
        ShapelessRecipeBuilder.shapeless(PiscaryItems.FISH_FINGERS::get).requires(PenguinTags.RAW_FISHES).requires(PenguinTags.BREADS).requires(PenguinItems.PLATE.get()).unlockedBy("has_fishes", has(ItemTags.FISHES)).unlockedBy("has_bread", has(PenguinTags.BREADS)).save(consumer, rl("fish_fingers"));
        ShapelessRecipeBuilder.shapeless(PiscaryItems.SASHIMI::get).requires(PenguinTags.RAW_FISHES).requires(PenguinItems.PLATE.get()).unlockedBy("has_fishes", has(ItemTags.FISHES)).save(consumer, rl("sashimi"));
        ShapelessRecipeBuilder.shapeless(PiscaryItems.FISH_STEW::get).requires(PenguinTags.RAW_FISHES).requires(Tags.Items.CROPS_CARROT).requires(PenguinItems.DEEP_BOWL.get()).unlockedBy("has_fishes", has(ItemTags.FISHES)).unlockedBy("has_carrot", has(Tags.Items.CROPS_CARROT)).save(consumer, rl("fish_stew"));
        //Bait
        ShapelessRecipeBuilder.shapeless(PiscaryItems.BAIT::get, 64).requires(Items.BEEF).unlockedBy("has_beef", has(Items.BEEF)).save(consumer, rl("bait"));
        //Machines
        ShapedRecipeBuilder.shaped(PiscaryItems.FISH_TRAP::get).define('W', ItemTags.LOGS).define('S', Tags.Items.STRING).pattern("WSW").pattern("S S").pattern("WSW").unlockedBy("has_string", has(Tags.Items.STRING)).save(consumer, rl("fish_trap"));
        ShapedRecipeBuilder.shaped(PiscaryItems.HATCHERY::get).define('F', ItemTags.WOODEN_FENCES).define('S', ItemTags.WOODEN_SLABS).pattern("F F").pattern("F F").pattern("SSS").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(consumer, rl("hatchery"));
        ShapedRecipeBuilder.shaped(PiscaryItems.RECYCLER::get).define('S', Tags.Items.STONE).define('W', Tags.Items.RODS_WOODEN).define('P', Blocks.PISTON).define('L', Blocks.LEVER).pattern("SWS").pattern("SPS").pattern("SLS").unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE)).save(consumer, rl("recycler"));
        //Recycler
        RecyclerRecipe.recycler(Ingredient.of(PiscaryItems.FISH_BONES.get()), Items.BONE_MEAL, 2).unlocks("has_recycler", has(PiscaryBlocks.RECYCLER.get())).save(consumer, rl("fish_bones"));
        RecyclerRecipe.recycler(Ingredient.of(PiscaryItems.OLD_BOOT.get()), Items.LEATHER, 1).unlocks("has_recycler", has(PiscaryBlocks.RECYCLER.get())).save(consumer, rl("old_boot"));
        RecyclerRecipe.recycler(Ingredient.of(PiscaryItems.EMPTY_CAN.get()), Items.IRON_NUGGET, 3).unlocks("has_recycler", has(PiscaryBlocks.RECYCLER.get())).save(consumer, rl("tin_can"));
        RecyclerRecipe.recycler(Ingredient.of(PiscaryItems.FISH_FOSSIL.get()), Items.COAL, 1).unlocks("has_recycler", has(PiscaryBlocks.RECYCLER.get())).save(consumer, rl("fossil"));
        RecyclerRecipe.recycler(Ingredient.of(ItemTags.WOOL), Items.STRING, 3).unlocks("has_recycler", has(PiscaryBlocks.RECYCLER.get())).save(consumer, rl("wool"));
    }
}
