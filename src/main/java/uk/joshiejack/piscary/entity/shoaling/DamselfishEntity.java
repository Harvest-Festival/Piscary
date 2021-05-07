package uk.joshiejack.piscary.entity.shoaling;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DamselfishEntity extends ShoalingFishEntity {
    public DamselfishEntity(EntityType<? extends ShoalingFishEntity> entity, World world) {
        super(entity, world);
    }
}
