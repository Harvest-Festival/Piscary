package uk.joshiejack.piscary.crafting;

import joptsimple.internal.Strings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uk.joshiejack.penguinlib.PenguinLib;
import uk.joshiejack.penguinlib.data.holder.HolderRegistry;
import uk.joshiejack.penguinlib.events.DatabaseLoadedEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = PenguinLib.MODID)
public class BaitRegistry {
    public static final HolderRegistry<BaitData> INSTANCE = new HolderRegistry<>(BaitData.EMPTY);

    @SubscribeEvent
    public static void onDatabaseLoaded(DatabaseLoadedEvent event) {
        INSTANCE.clear();
        event.table("bait").rows().forEach(row -> {
            Item item = row.item();
            if (item != null)
                INSTANCE.register(item, new BaitData(row.get("loot table"), row.getColor("speed"), row.getAsInt("luck")));
        });
    }

    public static boolean isBait(ItemStack stack) {
        return INSTANCE.getValue(stack) != BaitData.EMPTY;
    }

    public static class BaitData {
        public static final BaitData EMPTY = new BaitData(Strings.EMPTY, 0, 0);
        private final ResourceLocation lootTable;
        private final int speed, luck;

        BaitData(@Nonnull String lootTable, int speed, int luck) {
            this.lootTable = new ResourceLocation(lootTable);
            this.speed = speed;
            this.luck = luck;
        }

        @Nullable
        public ResourceLocation getLootTable() {
            return lootTable;
        }

        public int getSpeed() {
            return speed;
        }

        public int getLuck() {
            return luck;
        }
    }
}
