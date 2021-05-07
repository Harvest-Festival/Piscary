package uk.joshiejack.piscary.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ElectricRayEntity extends FloordwellingFishEntity {
    public ElectricRayEntity(EntityType<? extends ElectricRayEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if (random.nextFloat() < 0.05F)
            player.hurt(DamageSource.LIGHTNING_BOLT, 3F);
    }
}
