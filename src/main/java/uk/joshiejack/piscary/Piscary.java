package uk.joshiejack.piscary;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.crafting.PiscaryRegistries;
import uk.joshiejack.piscary.data.*;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;
import uk.joshiejack.piscary.loot.BiomeTypeLocationCheck;
import uk.joshiejack.piscary.tileentity.PiscaryTileEntities;

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

        @OnlyIn(Dist.CLIENT)
        public void fillItemList(@Nonnull NonNullList<ItemStack> list) {
            NonNullList<ItemStack> spawnEggs = NonNullList.create();
            Registry.ITEM.forEach(item -> {
                if (item instanceof SpawnEggItem) item.fillItemCategory(this, spawnEggs);
                else item.fillItemCategory(this, list);
            });

            list.addAll(spawnEggs);
        }
    };

    public Piscary() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        PiscarySounds.SOUNDS.register(eventBus);
        PiscaryBlocks.BLOCKS.register(eventBus);
        PiscaryItems.ITEMS.register(eventBus);
        PiscaryEntities.ENTITIES.register(eventBus);
        PiscaryEntities.ITEMS.register(eventBus);
        PiscaryEntities.PAINTINGS.register(eventBus);
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
            generator.addProvider(new PiscaryBlockStates(generator, event.getExistingFileHelper()));
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

    public static class PiscarySounds {
        public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Piscary.MODID);
        public static final RegistryObject<SoundEvent> RECYCLER = createSoundEvent("recycler");

        private static RegistryObject<SoundEvent> createSoundEvent(@Nonnull String name) {
            return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Piscary.MODID, name)));
        }
    }
}
