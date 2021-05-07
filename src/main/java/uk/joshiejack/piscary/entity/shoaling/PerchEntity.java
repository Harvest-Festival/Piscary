package uk.joshiejack.piscary.entity.shoaling;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PerchEntity extends ShoalingFishEntity {
    public PerchEntity(EntityType<? extends ShoalingFishEntity> entity, World world) {
        super(entity, world);
    }
}
