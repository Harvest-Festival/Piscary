package uk.joshiejack.piscary.client;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;
import uk.joshiejack.penguinlib.events.DatabaseLoadedEvent;
import uk.joshiejack.piscary.Piscary;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Piscary.MODID)
public class HatcheryClient {
    private static final Object2FloatMap<EntityType<?>> HATCHERY_RENDERERS = new Object2FloatOpenHashMap<>();

    public static float getRotation(EntityType<?> type) {
        return HATCHERY_RENDERERS.getFloat(type);
    }

    public static boolean rotates(EntityType<?> type) {
        return HATCHERY_RENDERERS.containsKey(type);
    }

    @SubscribeEvent
    public static void onDatabaseLoaded(DatabaseLoadedEvent event) {
        HATCHERY_RENDERERS.clear();
        event.table("hatchery_renderers").rows().stream()
                .map(row -> Pair.of(row.entity(), row.getAsFloat("rotation")))
                .filter(pair -> pair.getKey() != null)
                .forEach(pair -> HATCHERY_RENDERERS.put(pair.getLeft(), (float) pair.getRight()));
    }
}
