package uk.joshiejack.piscary.item;

import net.minecraft.item.Item;
import uk.joshiejack.penguinlib.util.PenguinLoader;
import uk.joshiejack.penguinlib.util.interfaces.IModPlugin;
import uk.joshiejack.piscary.Piscary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

@PenguinLoader("aquaculture")
public class AquacultureSupport implements IModPlugin {
    @Override
    public void setup() {
        try {
            Class<?> aquaAPI = Class.forName("com.teammetallurgy.aquaculture.api.AquacultureAPI");
            Object fishData = aquaAPI.getField("FISH_DATA").get(null);
            Method addWeight = fishData.getClass().getMethod("addWeight", Item.class, double.class, double.class);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.ANCHOVY, 0.72, 2.57);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.ANGELFISH, 0.45, 0.9);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.ANGLERFISH, 12, 32);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.BASS, 0.82, 6.6);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.BLUE_TANG, 0.4, 0.6);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.BOWFIN, 3, 9.75);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.BUTTERFLYFISH, 0.02, 0.08);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.CARP, 2, 25);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.CATFISH, 18, 23);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.CHUB, 7, 8);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.DAMSELFISH, 0.03, 40.21);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.ELECTRIC_RAY, 1.6, 3);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.GOLDFISH, 0.22, 4.5);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.KOI, 10, 40);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.LAMPREY, 70, 90);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.LUNGFISH, 7, 10);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.MANTA_RAY, 1500, 3000);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.MINNOW, 0.2, 10);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.NEON_TETRA, 0.005, 0.015);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.NORTHERN_PIKE, 14, 18);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.PERCH, 1, 2.6);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.PICKEREL, 14, 18);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.PIRANHA, 1, 3.2);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.PUPFISH, 0.01, 0.05);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.SARDINE, 0.1, 1);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.SIAMESE_FIGHTING_FISH, 0.02, 0.07);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.STARGAZER, 5, 9);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.STINGRAY, 7.5, 75);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.SILVER_STRIPE_BLAASOP, 0.03, 0.3);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.TROUT, 3, 18);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.TUNA, 5, 250);
            registerFishKGtoPounds(addWeight, fishData, PiscaryItems.WALLEYE, 8, 12);
        } catch (ClassNotFoundException| NoSuchFieldException| NoSuchMethodException |InvocationTargetException | IllegalAccessException ex) {
            Piscary.LOGGER.error("Piscary failed to load the plugin support for Aquaculture, please report this to the mod author");
            ex.printStackTrace(); //Throw the error
        }
    }

    private static void registerFishKGtoPounds(Method method, Object data, Supplier<Item> fish, double min, double max) throws InvocationTargetException, IllegalAccessException {
        method.invoke(data, fish.get(), min * 2.20462, max * 2.20462);
    }
}
