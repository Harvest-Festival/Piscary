package uk.joshiejack.piscary.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import uk.joshiejack.penguinlib.data.generators.builders.SimplePenguinRecipeBuilder;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;
import uk.joshiejack.piscary.block.PiscaryBlocks;

import javax.annotation.Nonnull;

public class RecyclerRecipe extends SimplePenguinRecipe {
    public RecyclerRecipe(ResourceLocation resource, Ingredient ingredient, ItemStack output) {
        super(PiscaryRegistries.RECYCLER, PiscaryRegistries.RECYCLER_SERIALIZER.get(), resource, ingredient, output);
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(PiscaryBlocks.RECYCLER.get());
    }

    public static SimplePenguinRecipeBuilder recycler(Ingredient input, IItemProvider output, int amount) {
        return new SimplePenguinRecipeBuilder(PiscaryRegistries.RECYCLER_SERIALIZER.get(), input, output, amount);
    }
}
