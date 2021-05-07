package uk.joshiejack.piscary.entity.shoaling;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nonnull;

public class ShoalingFishEntity extends AbstractGroupFishEntity {
    public ShoalingFishEntity(EntityType<? extends ShoalingFishEntity> entity, World world) {
        super(entity, world);
    }

    @Nonnull
    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Piscary.MODID, getType().getRegistryName().getPath() + "_bucket")));
    }

    @Nonnull
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    @Override
    public EntitySize getDimensions(Pose p_213305_1_) {
        return EntitySize.scalable(0.7F, 0.4F);
    }
}
