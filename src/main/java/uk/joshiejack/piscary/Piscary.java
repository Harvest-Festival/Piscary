package uk.joshiejack.piscary;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.data.PiscaryItemModels;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.data.*;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;
import uk.joshiejack.piscary.loot.BiomeTypeLocationCheck;
import uk.joshiejack.piscary.tile.PiscaryTileEntities;

import javax.annotation.Nonnull;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Piscary.MODID)
public class Piscary {
    public static final String MODID = "piscary";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup TAB = new ItemGroup(MODID) {
        @Nonnull
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(PiscaryItems.PUPFISH.get());
        }
    };

    public Piscary() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        PiscaryBlocks.BLOCKS.register(eventBus);
        PiscaryItems.ITEMS.register(eventBus);
        PiscaryEntities.ENTITIES.register(eventBus);
        PiscaryEntities.ITEMS.register(eventBus);
        PiscaryRegistries.SERIALIZERS.register(eventBus);
        PiscaryTileEntities.TILE_ENTITIES.register(eventBus);
    }

    @SubscribeEvent
    public static void onDataGathering(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new PiscaryLootTables(generator));
            BlockTagsProvider blockTags = new PiscaryBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new PiscaryItemTags(generator, blockTags, event.getExistingFileHelper()));
            generator.addProvider(new PiscaryRecipes(generator));
            generator.addProvider(new PiscaryDatabase(generator));
        }

        if (event.includeClient()) {
            generator.addProvider(new PiscaryLanguage(generator));
            generator.addProvider(new PiscaryItemModels(generator, event.getExistingFileHelper()));
        }
    }

    @SubscribeEvent
    public static void registerLootData(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MODID, "biome_type"), BiomeTypeLocationCheck.BIOME_TYPE);
    }
}
