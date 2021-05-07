package uk.joshiejack.piscary.item;

import net.minecraft.item.Food;

public class PiscaryFoods {
    public static final Food SMALL_FISH = new Food.Builder().nutrition(1).saturationMod(0.1F).build();
    public static final Food MEDIUM_FISH = new Food.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final Food LARGE_FISH = new Food.Builder().nutrition(3).saturationMod(0.1F).build();
    public static final Food FISH_FINGERS = new Food.Builder().nutrition(5).saturationMod(0.3F).fast().build();
    public static final Food SASHIMI = new Food.Builder().nutrition(4).saturationMod(0.4F).build();
    public static final Food FISH_STEW = new Food.Builder().nutrition(7).saturationMod(0.6F).build();
}
