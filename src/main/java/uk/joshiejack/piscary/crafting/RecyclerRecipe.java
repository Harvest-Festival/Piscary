package uk.joshiejack.piscary.crafting;

import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import uk.joshiejack.penguinlib.item.crafting.PenguinSingleItemRecipe;
import uk.joshiejack.piscary.block.PiscaryBlocks;

import javax.annotation.Nonnull;

public class RecyclerRecipe extends PenguinSingleItemRecipe {
    public RecyclerRecipe(ResourceLocation resource, String group, Ingredient ingredient, ItemStack output) {
        super(PiscaryRegistries.RECYCLER, PiscaryRegistries.RECYCLER_SERIALIZER.get(), resource, group, ingredient, output);
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

    public static SingleItemRecipeBuilder recycler(Ingredient input, IItemProvider output, int amount) {
        return new SingleItemRecipeBuilder(PiscaryRegistries.RECYCLER_SERIALIZER.get(), input, output, amount);
    }
}
