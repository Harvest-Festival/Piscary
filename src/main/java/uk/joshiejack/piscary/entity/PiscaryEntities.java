package uk.joshiejack.piscary.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import uk.joshiejack.piscary.Piscary;
import uk.joshiejack.piscary.entity.shoaling.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Piscary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiscaryEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Piscary.MODID);
    public static final DeferredRegister<PaintingType> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Piscary.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Piscary.MODID);
    private static List<Pair<String, Supplier<SpawnEggItem>>> SPAWN_EGGS = new ArrayList<>(); //Temporary Pairings, to ensure all buckets and eggs appear in order
    public static final RegistryObject<EntityType<?>> ANCHOVY = register("anchovy", AnchovyEntity::new, 0.5F, 0.3F, 0xDBE4DF, 0x507853);
    public static final RegistryObject<EntityType<?>> ANGELFISH = register("angelfish", AngelfishEntity::new, 0.5F, 0.5F, 0xF2F2F2, 0xECE43E);
    public static final RegistryObject<EntityType<?>> ANGLERFISH = register("anglerfish", AnglerfishEntity::new, 0.7F, 0.6F, 0x5A4229, 0x271C0F);
    public static final RegistryObject<EntityType<?>> BASS = register("bass", BassEntity::new, 0.7F, 0.5F, 0xA39F6F, 0x3E3E1C);
    public static final RegistryObject<EntityType<?>> BLUE_TANG = register("blue_tang", BlueTangEntity::new, 0.5F, 0.3F, 0x5070D4, 0xFFFF10);
    public static final RegistryObject<EntityType<?>> BOWFIN = register("bowfin", BowfinEntity::new, 0.9F, 0.3F, 0x8B7B40, 0x343523);
    public static final RegistryObject<EntityType<?>> BUTTERFLYFISH = register("butterflyfish", ButterflyfishEntity::new, 0.5F, 0.3F, 0xF2A905, 0x282828);
    public static final RegistryObject<EntityType<?>> CARP = register("carp", CarpEntity::new, 0.7F, 0.5F, 0xB99366, 0x5A2C1D);
    public static final RegistryObject<EntityType<?>> CATFISH = register("catfish", SolitaryFishEntity::new, 0.9F, 0.3F, 0xCCCC99, 0x524941);
    public static final RegistryObject<EntityType<?>> CHUB = register("chub", ChubEntity::new, 0.7F, 0.4F, 0xE9BE89, 0x4E5F93);
    public static final RegistryObject<EntityType<?>> DAMSELFISH = register("damselfish", DamselfishEntity::new, 0.5F, 0.5F, 0xA8DAF4, 0x0F1338);
    public static final RegistryObject<EntityType<?>> ELECTRIC_RAY = register("electric_ray", ElectricRayEntity::new, 0.7F, 0.4F, 0xDBAD70, 0x8A6D56);
    public static final RegistryObject<EntityType<?>> GOLDFISH = register("goldfish", SolitaryFishEntity::new, 0.5F, 0.3F, 0xFDB609, 0xE37802);
    public static final RegistryObject<EntityType<?>> KOI = register("koi", SolitaryFishEntity::new, 0.7F, 0.5F, 0xE2DDDA, 0xEB4132);
    public static final RegistryObject<EntityType<?>> LAMPREY = register("lamprey", SolitaryFishEntity::new, 1F, 0.2F, 0x84736F, 0x262120);
    public static final RegistryObject<EntityType<?>> LUNGFISH = register("lungfish", SolitaryFishEntity::new, 0.7F, 0.4F, 0x8A8168, 0x564739);
    public static final RegistryObject<EntityType<?>> MANTA_RAY = register("manta_ray", MantaRayEntity::new, 3.5F, 0.5F, 0x314563, 0xD2DAE7);
    public static final RegistryObject<EntityType<?>> MINNOW = register("minnow", MinnowEntity::new, 0.5F, 0.3F, 0xA4A750, 0xD8BC5D);
    public static final RegistryObject<EntityType<?>> NEON_TETRA = register("neon_tetra", NeonTetraEntity::new, 0.5F, 0.3F, 0x14CED5, 0xA41904);
    public static final RegistryObject<EntityType<?>> NORTHERN_PIKE = register("northern_pike", NorthernPikeEntity::new, 0.7F, 0.4F, 0xA4A07D, 0x5A513A);
    public static final RegistryObject<EntityType<?>> PERCH = register("perch", PerchEntity::new, 0.7F, 0.5F, 0x342822, 0xCEB14A);
    public static final RegistryObject<EntityType<?>> PICKEREL = register("pickerel", PickerelEntity::new, 0.7F, 0.5F, 0x7B8240, 0xB96F4A);
    public static final RegistryObject<EntityType<?>> PIRANHA = register("piranha", PiranhaEntity::new, 0.7F, 0.4F, 0x20323E, 0x9E1B00);
    public static final RegistryObject<EntityType<?>> PUPFISH = register("pupfish", PupfishEntity::new, 0.5F, 0.3F, 0x87A3CF, 0x7454A0);
    public static final RegistryObject<EntityType<?>> SARDINE = register("sardine", SardineEntity::new, 0.5F, 0.3F, 0xE0DDE1, 0x2C345A);
    public static final RegistryObject<EntityType<?>> SIAMESE_FIGHTING_FISH = register("siamese_fighting_fish", SolitaryFishEntity::new, 0.5F, 0.3F, 0x593E83, 0x266FCB);
    public static final RegistryObject<EntityType<?>> WHITEMARGIN_STARGAZER = register("whitemargin_stargazer", FloordwellingFishEntity::new, 0.7F, 0.5F, 0x72513D, 0x4382A9);
    public static final RegistryObject<EntityType<?>> STINGRAY = register("stingray", StingRayEntity::new, 0.7F, 0.4F, 0x779974, 0x1B281F);
    public static final RegistryObject<EntityType<?>> SILVER_STRIPE_BLAASOP = register("silver_stripe_blaasop", SolitaryFishEntity::new, 0.7F, 0.4F, 0x727E6A, 0x2F3D40);
    public static final RegistryObject<EntityType<?>> TROUT = register("trout", TroutEntity::new, 0.7F, 0.4F, 0xC5929D, 0x6D5635);
    public static final RegistryObject<EntityType<?>> TUNA = register("tuna", TunaEntity::new, 0.7F, 0.5F, 0x93A097, 0x4A576F);
    public static final RegistryObject<EntityType<?>> WALLEYE = register("walleye", WalleyeEntity::new, 0.7F, 0.4F, 0xE2BD65, 0x363932);
    public static final RegistryObject<PaintingType> ALBATROSS = PAINTINGS.register("albatross", () -> new PaintingType(16, 16));
    public static final RegistryObject<PaintingType> BOATS = PAINTINGS.register("boats", () -> new PaintingType(16, 16));
    public static final RegistryObject<PaintingType> LIGHTHOUSE = PAINTINGS.register("lighthouse", () -> new PaintingType(32, 16));
    public static final RegistryObject<PaintingType> SUNSET = PAINTINGS.register("sunset", () -> new PaintingType(16, 16));
    public static final RegistryObject<PaintingType> WINDOW = PAINTINGS.register("window", () -> new PaintingType(32, 16));

    private static <T extends Entity> RegistryObject<EntityType<?>> register(String name, EntityType.IFactory<T> factory, float width, float height, int colorPrimary, int colorSecondary) {
        EntityType<?> type = EntityType.Builder.of(factory,
                name.equals("piranha") ? EntityClassification.MONSTER : name.equals("manta_ray") ? EntityClassification.WATER_CREATURE : EntityClassification.WATER_AMBIENT)
                .sized(width, height).clientTrackingRange(4).build(Piscary.MODID + ":" + name);
        ITEMS.register(name + "_bucket", () -> new FishBucketItem(() -> type, () -> Fluids.WATER, new Item.Properties().stacksTo(1).tab(Piscary.TAB)));
        SPAWN_EGGS.add(Pair.of(name + "_spawn_egg", () -> new SpawnEggItem(type, colorPrimary, colorSecondary, new Item.Properties().tab(Piscary.TAB))));
        if (name.equals("walleye")) {
            SPAWN_EGGS.forEach(pair -> ITEMS.register(pair.getLeft(), pair.getRight()));
            SPAWN_EGGS = null; //Clear out the registry, no longer needed!
        }

        return ENTITIES.register(name, () -> type);
    }

    @SuppressWarnings("unchecked, rawtypes")
    @SubscribeEvent
    public static void addFishEntity0Attributes(EntityAttributeCreationEvent event) {
        Map<String, Supplier<AttributeModifierMap.MutableAttribute>> map = new HashMap<>();
        put(map, () -> MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.5F), "stingray");
        put(map, () ->  MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.5F), "electric_ray");
        put(map, () -> MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.MOVEMENT_SPEED, 0.4F), "manta_ray");
        put(map, () -> MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8D).add(Attributes.MOVEMENT_SPEED, 1.2F).add(Attributes.ATTACK_DAMAGE, 4.0D), "piranha");
        put(map, () -> MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D), "bass", "carp", "koi", "perch", "stargazer");
        put(map, () -> MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D), "anglerfish", "tuna", "bowfin", "lungfish", "catfish");

        ENTITIES.getEntries().forEach(fish -> {
            if (map.containsKey(fish.get().getRegistryName().getPath()))
                event.put((EntityType<? extends LivingEntity>)fish.get(), map.get(fish.get().getRegistryName().getPath()).get().build());
            else
                event.put((EntityType<? extends LivingEntity>) fish.get(), AbstractFishEntity.createAttributes().build());

            EntitySpawnPlacementRegistry.register((EntityType) fish.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);
        });
    }

    private static void put(Map<String, Supplier<AttributeModifierMap.MutableAttribute>> map, Supplier<AttributeModifierMap.MutableAttribute> supplier, String... obj) {
        for (String str: obj)
            map.put(str, supplier);
    }
}
