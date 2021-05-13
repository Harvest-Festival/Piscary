package uk.joshiejack.piscary.crafting;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.EntityType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import uk.joshiejack.penguinlib.events.DatabaseLoadedEvent;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;
import uk.joshiejack.piscary.Piscary;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Piscary.MODID)
public class PiscaryRegistries {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Piscary.MODID);
    public static final RegistryObject<IRecipeSerializer<RecyclerRecipe>> RECYCLER_SERIALIZER = SERIALIZERS.register("recycler", () -> new SimplePenguinRecipe.Serializer<>(RecyclerRecipe::new));
    private static final Object2IntMap<EntityType<?>> HATCHERY = new Object2IntOpenHashMap<>();
    public static final IRecipeType<RecyclerRecipe> RECYCLER = IRecipeType.register(Piscary.MODID + ":recycler");

    @SubscribeEvent
    public static void onDatabaseLoaded(DatabaseLoadedEvent event) {
        HATCHERY.clear(); //Reset all the data in the hatchery :)
        event.table("hatchery").rows().stream()
                .map(row -> Pair.of(row.entity(), row.getAsInt("cycles")))
                .filter(pair -> pair.getKey() != null)
                .forEach(pair -> HATCHERY.put(pair.getKey(), (int) pair.getValue()));
    }

    public static int getValue(EntityType<?> type) {
        return HATCHERY.getInt(type);
    }
}
