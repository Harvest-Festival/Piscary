package uk.joshiejack.piscary.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class StingRayEntity extends FloordwellingFishEntity {
    public StingRayEntity(EntityType<? extends StingRayEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if (random.nextFloat() < 0.1F && !player.hasEffect(Effects.POISON))
            player.addEffect(new EffectInstance(Effects.POISON, 300));
    }
}
