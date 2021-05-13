package uk.joshiejack.piscary.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import uk.joshiejack.penguinlib.data.generators.builders.SimplePenguinRecipeBuilder;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;
import uk.joshiejack.piscary.block.PiscaryBlocks;

import javax.annotation.Nonnull;

public class RecyclerRecipe extends SimplePenguinRecipe {
    public RecyclerRecipe(ResourceLocation resource, Ingredient ingredient, ItemStack output) {
        super(PiscaryRegistries.RECYCLER, PiscaryRegistries.RECYCLER_SERIALIZER.get(), resource, ingredient, output);
    }

    @Override
    public boolean matches(IInventory inventory, @Nonnull World world) {
        return this.ingredient.test(inventory.getItem(0));
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
