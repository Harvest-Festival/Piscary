package uk.joshiejack.piscary.entity;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.piscary.Piscary;

import javax.annotation.Nonnull;

public class SolitaryFishEntity extends AbstractFishEntity {
    public SolitaryFishEntity(EntityType<? extends SolitaryFishEntity> entity, World world) {
        super(entity, world);
    }

    public static AttributeModifierMap.MutableAttribute createSpecialAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D);
    }

    @Nonnull
    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Piscary.MODID, getType().getRegistryName().getPath() + "_bucket")));
    }

    @Nonnull
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Override
    public EntitySize getDimensions(Pose p_213305_1_) {
        return EntitySize.scalable(0.9F, 0.3F);
    }
}
