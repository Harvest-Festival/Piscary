package uk.joshiejack.piscary.crafting;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HatcheryEntry {
    public static final HatcheryEntry DEFAULT = new HatcheryEntry(3, Items.AIR, Items.AIR);
    private static Method getBucketItemStack = null;
    private final int cycles;
    private final Item fishBucket;
    private final Item emptyBucket;

    public HatcheryEntry(int cycles, Item emptyBucket, Item bucket) {
        this.cycles = cycles;
        this.emptyBucket = emptyBucket;
        this.fishBucket = bucket;
    }

    public int getCycles() {
        return cycles;
    }

    public boolean isAir() {
        return fishBucket == Items.AIR;
    }

    public Item getFishBucket() {
        return fishBucket;
    }

    public ItemStack getFishBucket(World world, EntityType<?> entityType) {
        if (fishBucket != Items.AIR) return new ItemStack(fishBucket);
        try {
            if (getBucketItemStack == null)
                getBucketItemStack = ObfuscationReflectionHelper.findMethod(AbstractFishEntity.class, "func_203707_dx");
            return entityType == null ? ItemStack.EMPTY : (ItemStack) getBucketItemStack.invoke(entityType.create(world));
        } catch (InvocationTargetException | IllegalAccessException ignored) {
            return ItemStack.EMPTY;
        }
    }

    public Item getWaterBucket() {
        return emptyBucket;
    }
}
