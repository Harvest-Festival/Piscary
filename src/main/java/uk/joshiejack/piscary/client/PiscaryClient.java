package uk.joshiejack.piscary.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SalmonModel;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.block.PiscaryBlocks;
import uk.joshiejack.piscary.client.model.*;
import uk.joshiejack.piscary.client.renderer.*;
import uk.joshiejack.piscary.entity.PiscaryEntities;
import uk.joshiejack.piscary.item.PiscaryItems;
import uk.joshiejack.piscary.tileentity.PiscaryTileEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Piscary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiscaryClient {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ClientRegistry.bindTileEntityRenderer(PiscaryTileEntities.FISH_TRAP.get(), (FishTrapTileEntityRenderer::new));
        ClientRegistry.bindTileEntityRenderer(PiscaryTileEntities.HATCHERY.get(), (HatcheryTileEntityRenderer::new));
        ClientRegistry.bindTileEntityRenderer(PiscaryTileEntities.RECYCLER.get(), (RecyclerTileEntityRenderer::new));
    }

    @SuppressWarnings("unchecked, rawtypes")
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        Map<String, EntityModel<AbstractFishEntity>> map = new HashMap<>();
        put(map, new AngelfishModel(), "angelfish", "butterflyfish");
        put(map, new AnglerfishModel(), "anglerfish");
        put(map, new ElectricRayModel(), "electric_ray");
        put(map, new FatFishModel(), "bass", "carp", "koi", "perch", "piranha", "whitemargin_stargazer", "tuna");
        put(map, new LampreyModel(), "lamprey");
        put(map, new LongFishModel(), "bowfin", "lungfish", "catfish");
        put(map, new MantaRayModel(), "manta_ray");
        put(map, new PikeModel(), "pickerel", "northern_pike");
        put(map, new RayModel(), "stingray");
        put(map, new SalmonModel<>(), "chub", "silver_stripe_blaasop", "trout", "walleye");
        put(map, new SiameseFightingFishModel(), "siamese_fighting_fish");
        put(map, new SmallFishModel(), "anchovy", "goldfish", "minnow", "neon_tetra", "pupfish", "sardine");
        put(map, new TallFishModel(), "blue_tang", "damselfish");
        PiscaryEntities.ENTITIES.getEntries().forEach(fish -> {
            EntityModel m = map.get(fish.getId().getPath());
            Piscary.LOGGER.info("Registering: " + fish.getId() + " as " + m);
            RenderingRegistry.registerEntityRenderingHandler(fish.get(),
                    (manager) -> (EntityRenderer) (
                            fish.getId().getPath().contains("ray") ? new RayRenderer(manager, map.get(fish.getId().getPath()), fish.getId().getPath())
                            : new FishRenderer(manager, map.get(fish.getId().getPath()), fish.getId().getPath())));
        });

        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(PiscaryBlocks.FISH_TRAP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(PiscaryBlocks.HATCHERY.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(PiscaryBlocks.RECYCLER.get(), RenderType.cutout());
            ItemModelsProperties.register(PiscaryItems.FISHING_ROD.get(), new ResourceLocation("cast"),
                    Objects.requireNonNull(ItemModelsProperties.getProperty(Items.FISHING_ROD, new ResourceLocation("cast"))));
        });
    }

    private static void put(Map<String, EntityModel<AbstractFishEntity>> map, EntityModel<AbstractFishEntity> model, String... obj) {
        for (String str: obj)
            map.put(str, model);
    }
}
