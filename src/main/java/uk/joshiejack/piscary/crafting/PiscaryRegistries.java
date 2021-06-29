package uk.joshiejack.piscary.crafting;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.entity.EntityType;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import uk.joshiejack.penguinlib.events.DatabaseLoadedEvent;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Piscary.MODID)
public class PiscaryRegistries {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Piscary.MODID);
    public static final RegistryObject<IRecipeSerializer<RecyclerRecipe>> RECYCLER_SERIALIZER = SERIALIZERS.register("recycler", () -> new SimplePenguinRecipe.Serializer<>(RecyclerRecipe::new));
    private static final Map<EntityType<?>, HatcheryEntry> HATCHERY = new Object2ObjectOpenHashMap<>();
    private static final Map<Item, EntityType<?>> BUCKET_TO_FISH = new Object2ObjectOpenHashMap<>();
    public static final IRecipeType<RecyclerRecipe> RECYCLER = IRecipeType.register(Piscary.MODID + ":recycler");

    @SubscribeEvent
    public static void onDatabaseLoaded(DatabaseLoadedEvent event) {
        HATCHERY.clear(); //Reset all the data in the hatchery :)
        event.table("hatchery").rows().stream()
                .map(row -> Pair.of(row.entity(), row.getAsInt("cycles")))
                .filter(pair -> pair.getKey() != null)
                .forEach(pair -> HATCHERY.put(pair.getKey(), new HatcheryEntry(pair.getValue(), Items.WATER_BUCKET, Items.AIR)));
        //Create a new table so the old one still functions
        event.table("hatchery_advanced").rows().stream()
                .map(row -> Pair.of(row.entity(), new HatcheryEntry(row.getAsInt("cycles"), row.item("water bucket"), row.item("fish bucket"))))
                .filter(pair -> pair.getKey() != null && !pair.getValue().isAir())
                .forEach(pair -> {
                    HATCHERY.put(pair.getKey(), pair.getValue());
                    BUCKET_TO_FISH.put(pair.getValue().getFishBucket(), pair.getKey());
                });
    }

    public static boolean containsFish(ItemStack stack) {
        return stack.getItem() instanceof FishBucketItem || BUCKET_TO_FISH.containsKey(stack.getItem());
    }

    public static ItemStack getFishBucket(World world, EntityType<?> type) {
        return HATCHERY.containsKey(type) ? HATCHERY.get(type).getFishBucket(world, type) : ItemStack.EMPTY;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static EntityType<?> getFishFromItem(ItemStack stack) {
        if (stack.getItem() instanceof FishBucketItem)
            return ((Supplier<EntityType<?>>) Objects.requireNonNull(ObfuscationReflectionHelper.getPrivateValue(FishBucketItem.class, (FishBucketItem) stack.getItem(), "fishTypeSupplier"))).get();
        return BUCKET_TO_FISH.getOrDefault(stack.getItem(), null);
    }

    public static int getCycles(EntityType<?> type) {
        return HATCHERY.getOrDefault(type, HatcheryEntry.DEFAULT).getCycles();
    }

    public static boolean canContainFish(ItemStack held, @Nullable EntityType<?> type) {
        return type != null && HATCHERY.containsKey(type) && HATCHERY.get(type).getWaterBucket() == held.getItem();
    }

    public static ItemStack getWaterBucket(World world, EntityType<?> type) {
        return HATCHERY.containsKey(type) ? new ItemStack(HATCHERY.get(type).getWaterBucket()) : ItemStack.EMPTY;
    }
}