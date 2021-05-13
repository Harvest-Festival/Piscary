package uk.joshiejack.piscary.client.renderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uk.joshiejack.piscary.tile.HatcheryTileEntity;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("ConstantConditions")
@OnlyIn(Dist.CLIENT)
public class HatcheryFishRender {
    private final HatcheryTileEntity tile;
    private final long[] rotations = new long[10];
    private final boolean[] clockwise = new boolean[10];
    private final int[] speed = new int[10];
    private final float[] height = new float[10];
    private Entity displayEntity = null;

    public HatcheryFishRender(HatcheryTileEntity tile) {
        this.tile = tile;
    }

    public void reloadFish(EntityType<?> type, int count, Random random) {
        displayEntity = getOrCreateDisplayEntity(type);
        for (int i = 0; i < count; i++) {
            rotations[i] = random.nextInt(720000);
            clockwise[i] = random.nextBoolean();
            speed[i] = 1 + random.nextInt(5);
            height[i] = i * 1F;
        }
    }

    public void updateFish() {
        if (displayEntity == null) reloadFish(tile.getEntityType(), tile.getCount(), tile.getLevel().random);
        for (int i = 0; i < tile.getCount(); i++) {
            rotations[i] = rotations[i] + (clockwise[i] ? -speed[i] * 10L : speed[i] * 10L);
        }


        displayEntity.tickCount++;
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(EntityType<?> type) {
        if (displayEntity == null)
            displayEntity = tile.getEntityType() == null ? null : type.create(Objects.requireNonNull(tile.getLevel()));
        return displayEntity;
    }

    public float getRotation(int i) {
        return (float) (720.0 * (rotations[i] & 0x3FFFL) / 0x3FFFL);
    }

    public float getHeight(int i) {
        return height[i];
    }

    public boolean isClockwise(int i) {
        return clockwise[i];
    }
}
